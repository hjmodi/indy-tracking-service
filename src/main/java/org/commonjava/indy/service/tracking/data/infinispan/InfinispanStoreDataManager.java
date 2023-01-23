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
package org.commonjava.indy.service.tracking.data.infinispan;

import org.commonjava.indy.service.tracking.audit.ChangeSummary;
import org.commonjava.indy.service.tracking.change.event.NoOpStoreEventDispatcher;
import org.commonjava.indy.service.tracking.change.event.StoreEventDispatcher;
import org.commonjava.indy.service.tracking.config.IndyTrackingConfiguration;
import org.commonjava.indy.service.tracking.data.AbstractStoreDataManager;
import org.commonjava.indy.service.tracking.data.annotations.StandaloneStoreDataManager;
import org.commonjava.indy.service.tracking.model.ArtifactStore;
import org.commonjava.indy.service.tracking.model.Group;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static org.commonjava.indy.service.tracking.data.StoreUpdateAction.STORE;
import static org.commonjava.indy.service.tracking.data.infinispan.StoreDataCacheProducer.AFFECTED_BY_STORE_CACHE;
import static org.commonjava.indy.service.tracking.data.infinispan.StoreDataCacheProducer.STORE_BY_PKG_CACHE;
import static org.commonjava.indy.service.tracking.data.infinispan.StoreDataCacheProducer.STORE_DATA_CACHE;
import static org.commonjava.indy.service.tracking.model.StoreType.group;

/**
 * @deprecated infinispan data store is disabled in indy cluster mode. Will use {@link org.commonjava.indy.service.tracking.data.cassandra.CassandraStoreDataManager} instead.
 */
@ApplicationScoped
@StandaloneStoreDataManager
@Deprecated
public class InfinispanStoreDataManager
                extends AbstractStoreDataManager
{
    private final Logger logger = LoggerFactory.getLogger( getClass() );

    @Inject
    @StoreDataCache
    CacheHandle<StoreKey, ArtifactStore> stores;

    @Inject
    @StoreByPkgCache
    CacheHandle<String, Map<StoreType, Set<StoreKey>>> storesByPkg;

    @Inject
    @AffectedByStoreCache
    CacheHandle<StoreKey, Set<StoreKey>> affectedByStores;

    @Inject
    StoreEventDispatcher dispatcher;

    @Inject
    IndyTrackingConfiguration indyConfiguration;

    @Inject
    CacheProducer producer;

    protected InfinispanStoreDataManager()
    {
    }

    public InfinispanStoreDataManager( final Cache<StoreKey, ArtifactStore> cache,
                                       final Cache<String, Map<StoreType, Set<StoreKey>>> storesByPkg,
                                       final Cache<StoreKey, Set<StoreKey>> affectedByStoresCache )
    {
        this.dispatcher = new NoOpStoreEventDispatcher();
        this.stores = new CacheHandle<>( STORE_DATA_CACHE, cache );
        this.storesByPkg = new CacheHandle<>( STORE_BY_PKG_CACHE, storesByPkg );
        this.affectedByStores = new CacheHandle<>( AFFECTED_BY_STORE_CACHE, affectedByStoresCache );
        logger.warn( "Constructor init: STARTUP ACTIONS MAY NOT RUN." );
    }

    @Override
    protected StoreEventDispatcher getStoreEventDispatcher()
    {
        return dispatcher;
    }

    @Override
    protected Optional<ArtifactStore> getArtifactStoreInternal( StoreKey key )
    {
        return ofNullable( stores.get( key ) );
    }

    @Override
    protected synchronized ArtifactStore removeArtifactStoreInternal( StoreKey key )
    {
        final ArtifactStore store = stores.remove( key );
        final Map<StoreType, Set<StoreKey>> typedKeys = storesByPkg.get( key.getPackageType() );
        if ( typedKeys != null )
        {
            final Set<StoreKey> keys = typedKeys.get( key.getType() );
            if ( keys != null )
            {
                keys.remove( key );
            }
        }
        return store;
    }

    @Override
    public void clear( final ChangeSummary summary )
    {
        //TODO: I'm really concern if we need this implementation as we don't know if ISPN will clean all persistent entries!!!
        stores.clear();
        storesByPkg.clear();
        affectedByStores.clear();
        storesByPkg.clear();
    }

    @Override
    public Set<ArtifactStore> getAllArtifactStores()
    {
        return stores.executeCache( c -> new HashSet<>( c.values() ), "getAllStores" );
    }

    @Override
    public Map<StoreKey, ArtifactStore> getArtifactStoresByKey()
    {
        return stores.executeCache( c -> {
            Map<StoreKey, ArtifactStore> ret = new HashMap<>();
            c.values().forEach( v -> ret.put( v.getKey(), v ) );
            return ret;

        }, "getAllStoresByKey" );
    }

    @Override
    public boolean hasArtifactStore( final StoreKey key )
    {
        return stores.containsKey( key );
    }

    @Override
    public boolean isStarted()
    {
        return true;
    }

    @Override
    public boolean isEmpty()
    {
        return stores.isEmpty();
    }

    @Override
    public Stream<StoreKey> streamArtifactStoreKeys()
    {
        return stores.executeCache( c -> c.keySet().stream() );
    }

    @Override
    protected synchronized ArtifactStore putArtifactStoreInternal( StoreKey storeKey, ArtifactStore store )
    {
        final ArtifactStore added = stores.put( storeKey, store );
        final Map<StoreType, Set<StoreKey>> typedKeys =
                        storesByPkg.computeIfAbsent( storeKey.getPackageType(), k -> new HashMap<>() );
        final Set<StoreKey> keys = typedKeys.computeIfAbsent( storeKey.getType(), k -> new HashSet<>() );
        keys.add( storeKey );
        return added;
    }

    @Override
    public Set<StoreKey> getStoreKeysByPkg( final String pkg )
    {
        final Map<StoreType, Set<StoreKey>> typedKeys = storesByPkg.get( pkg );
        if ( typedKeys != null )
        {
            final Set<StoreKey> keys = new HashSet<>();
            typedKeys.values().forEach( keys::addAll );
            logger.trace( "There are {} stores for package type {}", keys.size(), pkg );
            return keys;
        }
        else
        {
            logger.trace( "There is no store for package type {}", pkg );
            return Collections.emptySet();
        }
    }

    @Override
    public Set<StoreKey> getStoreKeysByPkgAndType( final String pkg, final StoreType type )
    {
        final Map<StoreType, Set<StoreKey>> typedKeys = storesByPkg.get( pkg );
        if ( typedKeys != null )
        {
            final Set<StoreKey> keys = typedKeys.get( type );
            if ( keys != null )
            {
                logger.trace( "There are {} stores for package type {} with type {}", keys.size(), pkg, type );
                return new HashSet<>( keys );
            }
        }
        logger.trace( "There is no store for package type {} with type {}", pkg, type );
        return Collections.emptySet();
    }

    @Override
    public Set<Group> affectedBy( final Collection<StoreKey> keys )
    {
        logger.debug( "Calculate affectedBy for keys: {}", keys );
        checkAffectedByCacheHealth();

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
                Set<StoreKey> affected = affectedByStores.get( key );
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
    public Set<ArtifactStore> getArtifactStoresByPkgAndType( String packageType, StoreType storeType )
    {
        return stores.executeCache( c -> c.values()
                                          .stream()
                                          .filter( item -> packageType.equals( item.getPackageType() )
                                                          && storeType.equals( item.getType() ) ) )
                     .collect( Collectors.toSet() );
    }

    @Override
    protected void removeAffectedStore( StoreKey key )
    {
        affectedByStores.remove( key );
    }

    @Override
    protected void removeAffectedBy( StoreKey key, StoreKey affected )
    {
        affectedByStores.computeIfAbsent( key, k -> new HashSet<>() ).remove( affected );
    }

    @Override
    protected void addAffectedBy( StoreKey key, StoreKey affected )
    {
        affectedByStores.computeIfAbsent( key, k -> new HashSet<>() ).add( affected );
    }

    public void initAffectedBy()
    {
        final Set<ArtifactStore> allStores = getAllArtifactStores();
        allStores.stream().filter( s -> group == s.getType() ).forEach( s -> refreshAffectedBy( s, null, STORE ) );

        checkAffectedByCacheHealth();
    }

    private void checkAffectedByCacheHealth()
    {
        if ( affectedByStores.isEmpty() )
        {
            logger.error( "Affected-by reverse mapping appears to have failed. The affected-by cache is empty!" );
        }
    }

    public void initByPkgMap()
    {
        // re-fill the stores by package cache each time when reboot
        if ( storesByPkg != null )
        {
            logger.info( "Clean the stores-by-pkg cache" );
            storesByPkg.clear();
        }

        final Set<ArtifactStore> allStores = getAllArtifactStores();
        logger.info( "There are {} stores need to fill in stores-by-pkg cache", allStores.size() );
        for ( ArtifactStore store : allStores )
        {
            final Map<StoreType, Set<StoreKey>> typedKeys =
                            storesByPkg.computeIfAbsent( store.getKey().getPackageType(), k -> new HashMap<>() );

            final Set<StoreKey> keys = typedKeys.computeIfAbsent( store.getKey().getType(), k -> new HashSet<>() );
            keys.add( store.getKey() );
        }
    }

    @Override
    protected CacheProducer getCacheProducer()
    {
        return producer;
    }
}
