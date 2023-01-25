/**
 * Copyright (C) 2011-2022 Red Hat, Inc. (https://github.com/Commonjava/indy-repository-service)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.indy.service.tracking.data.cassandra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.commonjava.indy.service.tracking.audit.ChangeSummary;
import org.commonjava.indy.service.tracking.change.event.StoreEventDispatcher;
import org.commonjava.indy.service.tracking.data.AbstractStoreDataManager;
import org.commonjava.indy.service.tracking.data.annotations.ClusterStoreDataManager;
import org.commonjava.indy.service.tracking.data.infinispan.CacheHandle;
import org.commonjava.indy.service.tracking.data.infinispan.CacheProducer;
import org.commonjava.indy.service.tracking.model.AbstractRepository;
import org.commonjava.indy.service.tracking.model.ArtifactStore;
import org.commonjava.indy.service.tracking.model.Group;
import org.commonjava.indy.service.tracking.model.HostedRepository;
import org.commonjava.indy.service.tracking.model.PathStyle;
import org.commonjava.indy.service.tracking.model.RemoteRepository;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.commonjava.indy.service.tracking.data.StoreUpdateAction.STORE;
import static org.commonjava.indy.service.tracking.model.StoreType.group;
import static org.commonjava.indy.service.tracking.model.StoreType.remote;

@ApplicationScoped
@ClusterStoreDataManager
public class CassandraStoreDataManager
                extends AbstractStoreDataManager
{

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    private final String ARTIFACT_STORE = "artifact-store";

    private final Integer STORE_EXPIRATION_IN_MINS = 15;

    @Inject
    CassandraFoloRecord recordQuery;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    CacheProducer cacheProducer;

    @Inject
    StoreEventDispatcher eventDispatcher;

    @Inject
    @RemoteKojiStoreDataCache
    CacheHandle<StoreKey, ArtifactStore> remoteKojiStores;

    protected CassandraStoreDataManager()
    {
    }

    CassandraStoreDataManager( final CassandraFoloRecord storeQuery, final ObjectMapper objectMapper,
                               final CacheProducer cacheProducer )
    {
        this.recordQuery = storeQuery;
        this.objectMapper = objectMapper;
        this.cacheProducer = cacheProducer;
    }

    @Override
    protected StoreEventDispatcher getStoreEventDispatcher()
    {
        logger.trace( "Use {} as store event dispatcher", eventDispatcher.getClass() );
        return eventDispatcher;
    }

    @Override
    protected Optional<ArtifactStore> getArtifactStoreInternal( StoreKey key )
    {

        logger.trace( "Get artifact store: {}", key.toString() );

        if ( remote.equals( key.getType() ) && key.getName().startsWith( "koji-" ) )
        {
            ArtifactStore store = remoteKojiStores.get( key );
            if ( store != null )
            {
                return Optional.of( store );
            }
        }

        return Optional.ofNullable( computeIfAbsent( ARTIFACT_STORE, key, STORE_EXPIRATION_IN_MINS, Boolean.FALSE ) );
    }

    @Override
    protected ArtifactStore removeArtifactStoreInternal( StoreKey key )
    {

        logger.trace( "Remove artifact store: {}", key.toString() );

        DtxTrackingRecord dtxArtifactStore =
                        recordQuery.removeArtifactStore( key.getPackageType(), key.getType(), key.getName() );
        return toArtifactStore( dtxArtifactStore );
    }

    @Override
    public void clear( ChangeSummary summary )
    {

    }

    @Override
    //    @WithSpan
    public Set<ArtifactStore> getAllArtifactStores()
    {
        Set<DtxTrackingRecord> dtxArtifactStoreSet = recordQuery.getAllArtifactStores();
        Set<ArtifactStore> artifactStoreSet = new HashSet<>();
        dtxArtifactStoreSet.forEach( dtxArtifactStore -> artifactStoreSet.add( toArtifactStore( dtxArtifactStore ) ) );
        return artifactStoreSet;
    }

    @Override
    //    @WithSpan
    public Map<StoreKey, ArtifactStore> getArtifactStoresByKey()
    {
        Map<StoreKey, ArtifactStore> ret = new HashMap<>();
        Set<ArtifactStore> artifactStoreSet = getAllArtifactStores();
        artifactStoreSet.forEach( store -> ret.put( store.getKey(), store ) );
        return ret;
    }

    @Override
    //    @WithSpan
    public Set<StoreKey> getStoreKeysByPkg( final String pkg )
    {
        Set<StoreKey> storeKeySet = new HashSet<>();
        for ( StoreType storeType : StoreType.values() )
        {
            storeKeySet.addAll( getStoreKeysByPkgAndType( pkg, storeType ) );
        }
        return storeKeySet;
    }

    @Override
    //    @WithSpan
    public Set<StoreKey> getStoreKeysByPkgAndType( final String pkg, final StoreType type )
    {

        logger.trace( "Get storeKeys: {}/{}", pkg, type );

        Set<DtxTrackingRecord> dtxArtifactStoreSet = recordQuery.getArtifactStoresByPkgAndType( pkg, type );
        Set<StoreKey> storeKeySet = new HashSet<>();
        dtxArtifactStoreSet.forEach(
                        dtxArtifactStore -> storeKeySet.add( new StoreKey( pkg, type, dtxArtifactStore.getName() ) ) );
        return storeKeySet;
    }

    @Override
    public Set<ArtifactStore> getArtifactStoresByPkgAndType( final String pkg, final StoreType type )
    {

        logger.trace( "Get stores: {}/{}", pkg, type );

        Set<DtxTrackingRecord> dtxArtifactStoreSet = recordQuery.getArtifactStoresByPkgAndType( pkg, type );
        Set<ArtifactStore> storeSet = new HashSet<>();
        dtxArtifactStoreSet.forEach( dtxArtifactStore -> storeSet.add( toArtifactStore( dtxArtifactStore ) ) );
        return storeSet;
    }

    @Override
    public boolean hasArtifactStore( StoreKey key )
    {
        return getArtifactStoreInternal( key ).isPresent();
    }

    @Override
    public boolean isStarted()
    {
        return true;
    }

    @Override
    public boolean isEmpty()
    {
        return recordQuery.isEmpty();
    }

    public boolean isAffectedEmpty()
    {
        return recordQuery.isAffectedEmpty();
    }

    @Override
    public Stream<StoreKey> streamArtifactStoreKeys()
    {
        return getAllArtifactStores().stream().map( ArtifactStore::getKey ).collect( Collectors.toSet() ).stream();
    }

    @Override
    protected ArtifactStore putArtifactStoreInternal( StoreKey storeKey, ArtifactStore store )
    {
        DtxTrackingRecord dtxArtifactStore = toDtxTrackingRecord( storeKey, store );
        recordQuery.createDtxTrackingRecord( dtxArtifactStore );

        return computeIfAbsent( ARTIFACT_STORE, storeKey, STORE_EXPIRATION_IN_MINS, Boolean.TRUE );
    }

    @Override
    //    @WithSpan
    public Set<Group> affectedBy( Collection<StoreKey> keys )
    {

        final Set<Group> result = new HashSet<>();

        // use these to avoid recursion
        final Set<StoreKey> processed = new HashSet<>();
        final LinkedList<StoreKey> toProcess = new LinkedList<>( keys );

        while ( !toProcess.isEmpty() )
        {
            StoreKey key = toProcess.removeFirst();
            if ( key == null )
            {
                continue;
            }

            if ( processed.add( key ) )
            {
                DtxAffectedStore affectedStore = recordQuery.getAffectedStore( key );
                if ( affectedStore == null )
                {
                    processed.add( key );
                    continue;
                }
                Set<StoreKey> affected = affectedStore.getAffectedStoreKeys();
                if ( affected != null )
                {
                    logger.debug( "Get affectedByStores, key: {}, affected: {}", key, affected );
                    affected = affected.stream().filter( k -> k.getType() == group ).collect( Collectors.toSet() );
                    for ( StoreKey gKey : affected )
                    {
                        // avoid loading the ArtifactStore instance again and again
                        if ( !processed.contains( gKey ) && !toProcess.contains( gKey ) )
                        {
                            final Optional<ArtifactStore> storeOpt = getArtifactStoreInternal( gKey );

                            // if this group is disabled, we don't want to keep loading it again and again.
                            if ( storeOpt.isPresent() )
                            {
                                final ArtifactStore store = storeOpt.get();
                                if ( store.isDisabled() )
                                {
                                    processed.add( gKey );
                                }
                                else
                                {
                                    // add the group to the toProcess list so we can find any result that might include it in their own membership
                                    toProcess.addLast( gKey );
                                    result.add( (Group) store );
                                }
                            }
                            else
                            {
                                logger.warn( "Error: the group {} does not exist as affected by for store {}", gKey,
                                             key );
                                processed.add( gKey );
                            }
                        }
                    }
                }
            }
        }

        if ( logger.isTraceEnabled() )
        {
            logger.trace( "Groups affected by {} are: {}", keys,
                          result.stream().map( ArtifactStore::getKey ).collect( Collectors.toSet() ) );
        }
        return filterAffectedGroups( result );
    }

    @Override
    protected void removeAffectedStore( StoreKey key )
    {
        recordQuery.removeAffectedStore( key );
    }

    @Override
    protected void removeAffectedBy( StoreKey key, StoreKey affected )
    {
        recordQuery.removeAffectedBy( key, affected );
    }

    @Override
    protected void addAffectedBy( StoreKey key, StoreKey affected )
    {
        recordQuery.addAffectedBy( key, affected );
    }

    public void initAffectedBy()
    {
        final Set<ArtifactStore> allStores = getAllArtifactStores();
        allStores.stream().filter( s -> group == s.getType() ).forEach( s -> refreshAffectedBy( s, null, STORE ) );
    }

    private DtxTrackingRecord toDtxTrackingRecord( StoreKey storeKey, ArtifactStore store )
    {

        DtxTrackingRecord dtxArtifactStore = new DtxTrackingRecord();
        dtxArtifactStore.setTypeKey(
                        CassandraFoloUtil.getTypeKey( storeKey.getPackageType(), storeKey.getType().name() ) );
        dtxArtifactStore.setNameHashPrefix( CassandraFoloUtil.getHashPrefix( storeKey.getName() ) );
        dtxArtifactStore.setPackageType( storeKey.getPackageType() );
        dtxArtifactStore.setStoreType( storeKey.getType().name() );
        dtxArtifactStore.setName( storeKey.getName() );
        dtxArtifactStore.setDisableTimeout( store.getDisableTimeout() );
        dtxArtifactStore.setMetadata( store.getMetadata() );
        dtxArtifactStore.setDescription( store.getDescription() );
        dtxArtifactStore.setCreateTime( store.getCreateTime() );
        dtxArtifactStore.setAuthoritativeIndex( store.isAuthoritativeIndex() );
        dtxArtifactStore.setPathStyle( store.getPathStyle().name() );
        dtxArtifactStore.setRescanInProgress( store.isRescanInProgress() );
        dtxArtifactStore.setPathMaskPatterns( store.getPathMaskPatterns() );
        dtxArtifactStore.setDisabled( store.isDisabled() );
        dtxArtifactStore.setExtras( toExtra( store ) );

        return dtxArtifactStore;
    }

    private Map<String, String> toExtra( ArtifactStore store )
    {
        Map<String, String> extras = new HashMap<>();
        if ( store instanceof AbstractRepository )
        {
            AbstractRepository repository = (AbstractRepository) store;
            putValueIntoExtra( CassandraFoloUtil.ALLOW_SNAPSHOTS, repository.isAllowSnapshots(), extras );
            putValueIntoExtra( CassandraFoloUtil.ALLOW_RELEASES, repository.isAllowReleases(), extras );
        }
        if ( store instanceof HostedRepository )
        {
            HostedRepository hostedRepository = (HostedRepository) store;
            putValueIntoExtra( CassandraFoloUtil.STORAGE, hostedRepository.getStorage(), extras );
            putValueIntoExtra( CassandraFoloUtil.READONLY, hostedRepository.isReadonly(), extras );
            putValueIntoExtra( CassandraFoloUtil.SNAPSHOT_TIMEOUT_SECONDS, hostedRepository.getSnapshotTimeoutSeconds(),
                               extras );
        }
        if ( store instanceof RemoteRepository )
        {
            RemoteRepository remoteRepository = (RemoteRepository) store;
            putValueIntoExtra( CassandraFoloUtil.URL, remoteRepository.getUrl(), extras );
            putValueIntoExtra( CassandraFoloUtil.HOST, remoteRepository.getHost(), extras );
            putValueIntoExtra( CassandraFoloUtil.PORT, remoteRepository.getPort(), extras );
            putValueIntoExtra( CassandraFoloUtil.USER, remoteRepository.getUser(), extras );
            putValueIntoExtra( CassandraFoloUtil.PASSWORD, remoteRepository.getPassword(), extras );
            putValueIntoExtra( CassandraFoloUtil.PROXY_HOST, remoteRepository.getProxyHost(), extras );
            putValueIntoExtra( CassandraFoloUtil.PROXY_PORT, remoteRepository.getProxyPort(), extras );
            putValueIntoExtra( CassandraFoloUtil.PROXY_USER, remoteRepository.getProxyUser(), extras );
            putValueIntoExtra( CassandraFoloUtil.PROXY_PASSWORD, remoteRepository.getProxyPassword(), extras );
            putValueIntoExtra( CassandraFoloUtil.KEY_CERT_PEM, remoteRepository.getKeyCertPem(), extras );
            putValueIntoExtra( CassandraFoloUtil.KEY_PASSWORD, remoteRepository.getKeyPassword(), extras );
            putValueIntoExtra( CassandraFoloUtil.SERVER_CERT_PEM, remoteRepository.getServerCertPem(), extras );
            putValueIntoExtra( CassandraFoloUtil.PREFETCH_RESCAN_TIMESTAMP,
                               remoteRepository.getPrefetchRescanTimestamp(), extras );
            putValueIntoExtra( CassandraFoloUtil.METADATA_TIMEOUT_SECONDS, remoteRepository.getMetadataTimeoutSeconds(),
                               extras );
            putValueIntoExtra( CassandraFoloUtil.CACHE_TIMEOUT_SECONDS, remoteRepository.getCacheTimeoutSeconds(),
                               extras );
            putValueIntoExtra( CassandraFoloUtil.TIMEOUT_SECONDS, remoteRepository.getTimeoutSeconds(), extras );
            putValueIntoExtra( CassandraFoloUtil.MAX_CONNECTIONS, remoteRepository.getMaxConnections(), extras );
            putValueIntoExtra( CassandraFoloUtil.NFC_TIMEOUT_SECONDS, remoteRepository.getNfcTimeoutSeconds(), extras );
            putValueIntoExtra( CassandraFoloUtil.PASS_THROUGH, remoteRepository.isPassthrough(), extras );
            putValueIntoExtra( CassandraFoloUtil.PREFETCH_RESCAN, remoteRepository.isPrefetchRescan(), extras );
            putValueIntoExtra( CassandraFoloUtil.IGNORE_HOST_NAME_VERIFICATION,
                               remoteRepository.isIgnoreHostnameVerification(), extras );

        }
        if ( store instanceof Group )
        {
            Group group = (Group) store;
            putValueIntoExtra( CassandraFoloUtil.CONSTITUENTS, group.getConstituents(), extras );
            putValueIntoExtra( CassandraFoloUtil.PREPEND_CONSTITUENT, group.isPrependConstituent(), extras );
        }
        return extras;
    }

    private void putValueIntoExtra( String key, Object value, Map<String, String> extras )
    {
        if ( value != null )
        {
            try
            {
                extras.put( key, objectMapper.writeValueAsString( value ) );
            }
            catch ( JsonProcessingException e )
            {
                logger.warn( "Write value into extra error, key: {}", key, e );
            }
        }
    }

    private ArtifactStore toArtifactStore( final DtxTrackingRecord dtxArtifactStore )
    {
        if ( dtxArtifactStore == null )
        {
            return null;
        }
        ArtifactStore store = generateStore( dtxArtifactStore );

        if ( store != null )
        {
            store.setDisabled( dtxArtifactStore.isDisabled() );
            store.setMetadata( dtxArtifactStore.getMetadata() );
            store.setRescanInProgress( dtxArtifactStore.getRescanInProgress() );
            store.setDescription( dtxArtifactStore.getDescription() );
            store.setPathMaskPatterns( dtxArtifactStore.getPathMaskPatterns() );
            store.setDisableTimeout( dtxArtifactStore.getDisableTimeout() );
            store.setAuthoritativeIndex( dtxArtifactStore.getAuthoritativeIndex() );
            store.setPathStyle( PathStyle.valueOf( dtxArtifactStore.getPathStyle() ) );
        }
        return store;
    }

    @SuppressWarnings( "unchecked" )
    private ArtifactStore generateStore( DtxTrackingRecord dtxArtifactStore )
    {
        ArtifactStore store = null;
        if ( dtxArtifactStore.getExtras() != null && !dtxArtifactStore.getExtras().isEmpty() )
        {
            Map<String, String> extras = dtxArtifactStore.getExtras();
            if ( dtxArtifactStore.getStoreType().equals( StoreType.hosted.name() ) )
            {
                store = new HostedRepository( dtxArtifactStore.getPackageType(), dtxArtifactStore.getName() );
                ( (HostedRepository) store ).setReadonly(
                                readValueFromExtra( CassandraFoloUtil.READONLY, Boolean.class, extras ) );
                Integer snapshotTimeoutseconds =
                                readIntValueFromExtra( CassandraFoloUtil.SNAPSHOT_TIMEOUT_SECONDS, extras );
                if ( snapshotTimeoutseconds != null )
                {
                    ( (HostedRepository) store ).setSnapshotTimeoutSeconds( snapshotTimeoutseconds );
                }
                ( (HostedRepository) store ).setStorage( readStrValueFromExtra( CassandraFoloUtil.STORAGE, extras ) );
                Boolean allowReleases = readValueFromExtra( CassandraFoloUtil.ALLOW_RELEASES, Boolean.class, extras );
                if ( allowReleases != null )
                {
                    ( (HostedRepository) store ).setAllowReleases( allowReleases );
                }
                Boolean allowSnapshots = readValueFromExtra( CassandraFoloUtil.ALLOW_SNAPSHOTS, Boolean.class, extras );
                if ( allowSnapshots != null )
                {
                    ( (HostedRepository) store ).setAllowSnapshots( allowSnapshots );
                }
            }
            else if ( dtxArtifactStore.getStoreType().equals( remote.name() ) )
            {
                store = new RemoteRepository( dtxArtifactStore.getPackageType(), dtxArtifactStore.getName(),
                                              readStrValueFromExtra( CassandraFoloUtil.URL, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setUser,
                              readStrValueFromExtra( CassandraFoloUtil.USER, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setPassword,
                              readStrValueFromExtra( CassandraFoloUtil.PASSWORD, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setHost,
                              readStrValueFromExtra( CassandraFoloUtil.HOST, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setProxyHost,
                              readStrValueFromExtra( CassandraFoloUtil.PROXY_HOST, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setServerCertPem,
                              readStrValueFromExtra( CassandraFoloUtil.SERVER_CERT_PEM, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setKeyCertPem,
                              readStrValueFromExtra( CassandraFoloUtil.KEY_CERT_PEM, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setKeyPassword,
                              readStrValueFromExtra( CassandraFoloUtil.KEY_PASSWORD, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setProxyPassword,
                              readStrValueFromExtra( CassandraFoloUtil.PROXY_PASSWORD, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setProxyUser,
                              readStrValueFromExtra( CassandraFoloUtil.PROXY_USER, extras ) );
                setIfNotNull( ( (RemoteRepository) store )::setPrefetchRescanTimestamp,
                              readStrValueFromExtra( CassandraFoloUtil.PREFETCH_RESCAN_TIMESTAMP, extras ) );

                Integer timeoutSeconds = readIntValueFromExtra( CassandraFoloUtil.TIMEOUT_SECONDS, extras );
                if ( timeoutSeconds != null )
                {
                    ( (RemoteRepository) store ).setTimeoutSeconds( timeoutSeconds );
                }
                Integer metadataTimeoutSeconds =
                                readIntValueFromExtra( CassandraFoloUtil.METADATA_TIMEOUT_SECONDS, extras );
                if ( metadataTimeoutSeconds != null )
                {
                    ( (RemoteRepository) store ).setMetadataTimeoutSeconds( metadataTimeoutSeconds );
                }
                Integer cacheTimeoutSeconds = readIntValueFromExtra( CassandraFoloUtil.CACHE_TIMEOUT_SECONDS, extras );
                if ( cacheTimeoutSeconds != null )
                {
                    ( (RemoteRepository) store ).setCacheTimeoutSeconds( cacheTimeoutSeconds );
                }
                Integer nfcTimeoutSeconds = readIntValueFromExtra( CassandraFoloUtil.NFC_TIMEOUT_SECONDS, extras );
                if ( nfcTimeoutSeconds != null )
                {
                    ( (RemoteRepository) store ).setNfcTimeoutSeconds( nfcTimeoutSeconds );
                }
                Integer maxConnections = readIntValueFromExtra( CassandraFoloUtil.MAX_CONNECTIONS, extras );
                if ( maxConnections != null )
                {
                    ( (RemoteRepository) store ).setMaxConnections( maxConnections );
                }
                Integer port = readIntValueFromExtra( CassandraFoloUtil.PORT, extras );
                if ( port != null )
                {
                    ( (RemoteRepository) store ).setPort( port );
                }
                Integer proxyPort = readIntValueFromExtra( CassandraFoloUtil.PROXY_PORT, extras );
                if ( proxyPort != null )
                {
                    ( (RemoteRepository) store ).setProxyPort( proxyPort );
                }
                Boolean prefetchRescan = readValueFromExtra( CassandraFoloUtil.PREFETCH_RESCAN, Boolean.class, extras );
                if ( prefetchRescan != null )
                {
                    ( (RemoteRepository) store ).setPrefetchRescan( prefetchRescan );
                }
                Boolean passThrough = readValueFromExtra( CassandraFoloUtil.PASS_THROUGH, Boolean.class, extras );
                if ( passThrough != null )
                {
                    ( (RemoteRepository) store ).setPassthrough( passThrough );
                }
                Boolean ignoreHostnameVerification =
                                readValueFromExtra( CassandraFoloUtil.IGNORE_HOST_NAME_VERIFICATION, Boolean.class,
                                                    extras );
                if ( ignoreHostnameVerification != null )
                {
                    ( (RemoteRepository) store ).setIgnoreHostnameVerification( ignoreHostnameVerification );
                }
                Boolean allowReleases = readValueFromExtra( CassandraFoloUtil.ALLOW_RELEASES, Boolean.class, extras );
                if ( allowReleases != null )
                {
                    ( (RemoteRepository) store ).setAllowReleases( allowReleases );
                }
                Boolean allowSnapshots = readValueFromExtra( CassandraFoloUtil.ALLOW_SNAPSHOTS, Boolean.class, extras );
                if ( allowSnapshots != null )
                {
                    ( (RemoteRepository) store ).setAllowSnapshots( allowSnapshots );
                }
            }
            else if ( dtxArtifactStore.getStoreType().equals( StoreType.group.name() ) )
            {
                List<String> constituentStrList =
                                readValueFromExtra( CassandraFoloUtil.CONSTITUENTS, List.class, extras );
                List<StoreKey> constituentList =
                                constituentStrList.stream().map( StoreKey::fromString ).collect( Collectors.toList() );
                store = new Group( dtxArtifactStore.getPackageType(), dtxArtifactStore.getName(), constituentList );

                Boolean prependConstituent =
                                readValueFromExtra( CassandraFoloUtil.PREPEND_CONSTITUENT, Boolean.class, extras );
                if ( prependConstituent != null )
                {
                    ( (Group) store ).setPrependConstituent( prependConstituent );
                }
            }
        }
        return store;
    }

    private <T> void setIfNotNull( final Consumer<T> setter, final T value )
    {
        if ( value != null )
        {
            setter.accept( value );
        }
    }

    public Integer readIntValueFromExtra( String key, Map<String, String> extras )
    {
        return readValueFromExtra( key, Integer.class, extras );
    }

    public String readStrValueFromExtra( String key, Map<String, String> extras )
    {
        return readValueFromExtra( key, String.class, extras );
    }

    public <T> T readValueFromExtra( String key, Class<T> valueType, Map<String, String> extras )
    {
        try
        {
            if ( extras.get( key ) != null )
            {
                return objectMapper.readValue( extras.get( key ), valueType );
            }
        }
        catch ( JsonProcessingException e )
        {
            logger.warn( "Read value from extra error, key: {}.", key, e );
        }
        return null;
    }

    public void initRemoteStoresCache()
    {
        final Set<ArtifactStore> allStores = getAllArtifactStores();

        // cache the remote koji repo
        allStores.stream()
                 .filter( s -> remote == s.getType() && (
                                 "koji".equals( s.getMetadata( ArtifactStore.METADATA_ORIGIN ) )
                                                 || "koji-binary".equals(
                                                 s.getMetadata( ArtifactStore.METADATA_ORIGIN ) ) ) )
                 .forEach( s -> remoteKojiStores.put( s.getKey(), s ) );
    }

    private ArtifactStore computeIfAbsent( String name, StoreKey key, int expirationMins, boolean forceQuery )
    {
        logger.debug( "computeIfAbsent, cache: {}, key: {}", name, key );

        CacheHandle<StoreKey, ArtifactStore> cache = cacheProducer.getCache( name );
        ArtifactStore store = cache.get( key );
        if ( store == null || forceQuery )
        {
            logger.trace( "Entry not found, run put, expirationMins: {}", expirationMins );

            DtxTrackingRecord dtxArtifactStore =
                            recordQuery.getArtifactStore( key.getPackageType(), key.getType(), key.getName() );

            store = toArtifactStore( dtxArtifactStore );
            if ( store != null )
            {
                if ( expirationMins > 0 )
                {
                    cache.put( key, store, expirationMins, TimeUnit.MINUTES );
                }
                else
                {
                    cache.put( key, store );
                }
            }
        }

        logger.trace( "Return value, cache: {}, key: {}, ret: {}", name, key, store );
        return store;
    }

    @Override
    protected CacheProducer getCacheProducer()
    {
        return cacheProducer;
    }

}
