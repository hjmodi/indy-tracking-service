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
package org.commonjava.indy.service.tracking.data;

import org.commonjava.event.common.EventMetadata;
import org.commonjava.event.store.StoreUpdateType;
import org.commonjava.indy.service.tracking.audit.ChangeSummary;
import org.commonjava.indy.service.tracking.change.event.StoreEventDispatcher;
import org.commonjava.indy.service.tracking.concurrent.Locker;
import org.commonjava.indy.service.tracking.config.IndyTrackingConfiguration;
import org.commonjava.indy.service.tracking.data.infinispan.CacheProducer;
import org.commonjava.indy.service.tracking.exception.IndyDataException;
import org.commonjava.indy.service.tracking.model.ArtifactStore;
import org.commonjava.indy.service.tracking.model.Group;
import org.commonjava.indy.service.tracking.model.HostedRepository;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.commonjava.indy.service.tracking.model.ValuePipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptySet;
import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.commonjava.indy.service.tracking.data.StoreUpdateAction.DELETE;
import static org.commonjava.indy.service.tracking.data.StoreUpdateAction.STORE;
import static org.commonjava.indy.service.tracking.model.StoreType.group;
import static org.commonjava.indy.service.tracking.model.StoreType.hosted;

@SuppressWarnings( { "unused", "unchecked" } )
public abstract class AbstractStoreDataManager
                implements StoreDataManager
{
    protected static final long LOCK_TIMEOUT_SECONDS = 30;

    protected final Locker<StoreKey> opLocks = new Locker<>(); // used internally

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    @Inject
    StoreValidator storeValidator;

    @Inject
    IndyTrackingConfiguration trackingConfig;

    protected AbstractStoreDataManager()
    {
    }

    abstract protected StoreEventDispatcher getStoreEventDispatcher();

    @Override
    public ArtifactStoreQuery<ArtifactStore> query()
    {
        boolean queryCacheEnabled = trackingConfig != null && trackingConfig.queryCacheEnabled();
        return new DefaultArtifactStoreQuery<>( this,
                                                new DefaultArtifactStoreQuery.QueryCacheWrapper( queryCacheEnabled,
                                                                                                 getCacheProducer() ) );
    }

    protected abstract Optional<ArtifactStore> getArtifactStoreInternal( final StoreKey key );

    @Override
    //    @WithSpan
    public Optional<ArtifactStore> getArtifactStore( final StoreKey key )
    {
        return getArtifactStoreInternal( key );
    }

    @Override
    //    @WithSpan
    public boolean storeArtifactStore( final ArtifactStore store, final ChangeSummary summary,
                                       final boolean skipIfExists, final boolean fireEvents,
                                       final EventMetadata eventMetadata ) throws IndyDataException
    {
        return store( store, summary, skipIfExists, fireEvents, eventMetadata );
    }

    protected void preStore( final ArtifactStore store, final ArtifactStore original, final ChangeSummary summary,
                             final boolean exists, final boolean fireEvents, final EventMetadata eventMetadata )
    {
        StoreEventDispatcher dispatcher = getStoreEventDispatcher();
        if ( dispatcher != null && isStarted() && fireEvents )
        {
            logger.debug( "Firing store pre-update event for: {} (originally: {})", store, original );
            dispatcher.updating( exists ? StoreUpdateType.UPDATE : StoreUpdateType.ADD, eventMetadata,
                                 singletonMap( store, original ) );

            if ( exists )
            {
                if ( store.isDisabled() && !original.isDisabled() )
                {
                    dispatcher.disabling( eventMetadata, store.getKey() );
                }
                else if ( !store.isDisabled() && original.isDisabled() )
                {
                    dispatcher.enabling( eventMetadata, store.getKey() );
                }
            }
        }
    }

    protected void postStore( final ArtifactStore store, final ArtifactStore original, final ChangeSummary summary,
                              final boolean exists, final boolean fireEvents, final EventMetadata eventMetadata )
    {
        StoreEventDispatcher dispatcher = getStoreEventDispatcher();
        if ( dispatcher != null && isStarted() && fireEvents )
        {
            logger.debug( "Firing store post-update event for: {} (originally: {})", store, original );
            dispatcher.updated( exists ? StoreUpdateType.UPDATE : StoreUpdateType.ADD, eventMetadata,
                                singletonMap( store, original ) );

            if ( exists )
            {
                if ( store.isDisabled() && !original.isDisabled() )
                {
                    dispatcher.disabled( eventMetadata, store.getKey() );
                }
                else if ( !store.isDisabled() && original.isDisabled() )
                {
                    dispatcher.enabled( eventMetadata, store.getKey() );
                }
            }
        }
        // Hosted or Remote update does not change affectedBy relationships
        if ( store instanceof Group )
        {
            refreshAffectedBy( store, original, STORE );
        }
    }

    protected void preDelete( final ArtifactStore store, final ChangeSummary summary, final boolean fireEvents,
                              final EventMetadata eventMetadata )
    {
        StoreEventDispatcher dispatcher = getStoreEventDispatcher();
        if ( dispatcher != null && isStarted() && fireEvents )
        {
            eventMetadata.set( StoreDataManager.CHANGE_SUMMARY, summary );
            dispatcher.deleting( eventMetadata, store.getKey() );
        }
    }

    protected void postDelete( final ArtifactStore store, final ChangeSummary summary, final boolean fireEvents,
                               final EventMetadata eventMetadata )
    {
        StoreEventDispatcher dispatcher = getStoreEventDispatcher();
        if ( dispatcher != null && isStarted() && fireEvents )
        {
            dispatcher.deleted( eventMetadata, store.getKey() );
        }

        refreshAffectedBy( store, null, StoreUpdateAction.DELETE );
    }

    //    @WithSpan
    protected void refreshAffectedBy( final ArtifactStore store, final ArtifactStore original,
                                      StoreUpdateAction action )
    {
        if ( store == null )
        {
            return;
        }

        if ( store instanceof Group && isExcludedGroup( (Group) store ) )
        {
            logger.info( "Skip affectedBy calculation of group: {}", store.getName() );
            return;
        }

        if ( action == DELETE )
        {
            if ( store instanceof Group )
            {
                Group grp = (Group) store;

                new HashSet<>( grp.getConstituents() ).forEach( ( key ) -> removeAffectedBy( key, store.getKey() ) );

                logger.info( "Removed affected-by reverse mapping for: {} in {} member stores", store.getKey(),
                             grp.getConstituents().size() );
            }
            else
            {
                removeAffectedStore( store.getKey() );
            }
        }
        else if ( action == STORE )
        {
            // NOTE: Only group membership changes can affect our affectedBy, unless the update is a store deletion.
            if ( store instanceof Group )
            {
                final Set<StoreKey> updatedConstituents = new HashSet<>( ( (Group) store ).getConstituents() );
                final Set<StoreKey> originalConstituents;
                if ( original != null )
                {
                    originalConstituents = new HashSet<>( ( (Group) original ).getConstituents() );
                }
                else
                {
                    originalConstituents = new HashSet<>();
                }

                final Set<StoreKey> added = new HashSet<>();
                final Set<StoreKey> removed = new HashSet<>();
                for ( StoreKey updKey : updatedConstituents )
                {
                    if ( !originalConstituents.contains( updKey ) )
                    {
                        added.add( updKey );
                    }
                }

                for ( StoreKey oriKey : originalConstituents )
                {
                    if ( !updatedConstituents.contains( oriKey ) )
                    {
                        removed.add( oriKey );
                    }
                }

                removed.forEach( ( key ) -> removeAffectedBy( key, store.getKey() ) );

                logger.info( "Removed affected-by reverse mapping for: {} in {} member stores", store.getKey(),
                             removed.size() );

                added.forEach( ( key ) -> addAffectedBy( key, store.getKey() ) );

                logger.info( "Added affected-by reverse mapping for: {} in {} member stores", store.getKey(),
                             added.size() );
            }
        }
    }

    protected abstract void removeAffectedBy( StoreKey key, StoreKey affected );

    protected abstract void addAffectedBy( StoreKey key, StoreKey affected );

    protected abstract void removeAffectedStore( StoreKey key );

    protected abstract ArtifactStore removeArtifactStoreInternal( StoreKey key );

    @Override
    //    @WithSpan
    public void deleteArtifactStore( final StoreKey key, final ChangeSummary summary,
                                     final EventMetadata eventMetadata ) throws IndyDataException
    {
        AtomicReference<IndyDataException> error = new AtomicReference<>();
        opLocks.lockAnd( key, LOCK_TIMEOUT_SECONDS, k -> {
            try
            {
                final Optional<ArtifactStore> storeOpt = getArtifactStoreInternal( k );
                if ( storeOpt.isEmpty() )
                {
                    logger.warn( "No store found for: {}", k );
                    return null;
                }

                final ArtifactStore store = storeOpt.get();
                if ( isReadonly( store ) )
                {
                    throw new IndyDataException( METHOD_NOT_ALLOWED.getStatusCode(),
                                                 "The store {} is readonly. If you want to delete this store, please modify it to non-readonly",
                                                 store.getKey() );
                }

                preDelete( store, summary, true, eventMetadata );

                ArtifactStore removed = removeArtifactStoreInternal( k );
                logger.info( "REMOVED store: {}", removed );

                postDelete( store, summary, true, eventMetadata );
            }
            catch ( IndyDataException e )
            {
                error.set( e );
            }

            return null;
        }, ( k, lock ) -> {
            error.set( new IndyDataException( "Failed to lock: %s for DELETE after %d seconds.", k,
                                              LOCK_TIMEOUT_SECONDS ) );
            return false;
        } );

        IndyDataException ex = error.get();
        if ( ex != null )
        {
            throw ex;
        }
    }

    /**
     * TODO: currently we only check hosted readonly to prevent unexpected removing of both files and repo itself.
     * We may expand to remote or group in the future to support functions like remote repo "deploy-through".
     */
    @Override
    public boolean isReadonly( final ArtifactStore store )
    {
        if ( store != null )
        {
            return store.getKey().getType() == hosted && ( (HostedRepository) store ).isReadonly();
        }
        return false;
    }

    @Override
    public void install()
    {
    }

    @Override
    public abstract void clear( final ChangeSummary summary ) throws IndyDataException;

    @Override
    //    @WithSpan
    public abstract Set<ArtifactStore> getAllArtifactStores() throws IndyDataException;

    @Override
    //    @WithSpan
    public Stream<ArtifactStore> streamArtifactStores() throws IndyDataException
    {
        return getAllArtifactStores().stream();
    }

    @Override
    //    @WithSpan
    public abstract Map<StoreKey, ArtifactStore> getArtifactStoresByKey();

    @Override
    public abstract boolean hasArtifactStore( final StoreKey key );

    @Override
    public void reload()
    {
    }

    @Override
    public abstract boolean isStarted();

    protected abstract ArtifactStore putArtifactStoreInternal( final StoreKey storeKey, ArtifactStore store );

    protected boolean store( final ArtifactStore store, final ChangeSummary summary, final boolean skipIfExists,
                             final boolean fireEvents, final EventMetadata eventMetadata ) throws IndyDataException
    {
        if ( store == null )
        {
            logger.warn( "Tried to store null ArtifactStore!" );
            return false;
        }

        AtomicReference<IndyDataException> error = new AtomicReference<>();
        logger.trace( "Storing {} using operation lock: {}", store, opLocks );

        final StoreKey storeKey = store.getKey();

        logger.warn( "Storing {} using operation lock: {}", store, opLocks );

        if ( trackingConfig != null && trackingConfig.storeValidationEnabled() && store.getType() != group )
        {
            ArtifactStoreValidateData validateData = storeValidator.validate( store );
            if ( !validateData.isValid() )
            {
                logger.warn( "=> [AbstractStoreDataManager] Adding Validation Metadata to Remote Store: "
                                             + store.getKey() + " - not Valid! " );
                if ( store.getMetadata() != null )
                {
                    store.getMetadata().putAll( validateData.getErrors() );
                }
                else
                {
                    store.setMetadata( validateData.getErrors() );
                }
            }
        }

        Function<StoreKey, Boolean> lockHandler =
                        k -> doStore( k, store, summary, error, skipIfExists, fireEvents, eventMetadata );

        BiFunction<StoreKey, ReentrantLock, Boolean> lockFailedHandler = ( k, lock ) -> {
            error.set( new IndyDataException( "Failed to lock: %s for STORE after %d seconds.", k,
                                              LOCK_TIMEOUT_SECONDS ) );
            return false;
        };

        Boolean result = opLocks.lockAnd( storeKey, LOCK_TIMEOUT_SECONDS, lockHandler, lockFailedHandler );
        if ( result == null )
        {
            throw new IndyDataException( "Store failed due to tryLock timeout." );
        }

        IndyDataException ex = error.get();
        if ( ex != null )
        {
            throw ex;
        }

        return result;
    }

    private Boolean doStore( StoreKey k, ArtifactStore store, ChangeSummary summary,
                             AtomicReference<IndyDataException> error, boolean skipIfExists, boolean fireEvents,
                             EventMetadata eventMetadata )
    {
        ArtifactStore original = getArtifactStoreInternal( k ).orElse( null );
        if ( original == store )
        {
            // if they're the same instance, preUpdate events may not work correctly!
            logger.warn( "Storing changes on existing instance of: {}! You forgot to call {}.copyOf().", store,
                         store.getClass().getSimpleName() );
        }

        if ( skipIfExists && original != null )
        {
            logger.info( "Skip storing for {} (repo exists)", original );
            return true;
        }

        if ( eventMetadata != null && summary != null )
        {
            eventMetadata.set( StoreDataManager.CHANGE_SUMMARY, summary );
        }
        logger.debug( "Starting pre-store actions for {}", k );
        preStore( store, original, summary, original != null, fireEvents, eventMetadata );
        logger.debug( "Pre-store actions complete for {}", k );

        logger.debug( "Put {} to stores map", k );
        final ArtifactStore old = putArtifactStoreInternal( store.getKey(), store );

        logger.debug( "Starting post-store actions for {}", k );
        postStore( store, original, summary, original != null, fireEvents, eventMetadata );
        logger.debug( "Post-store actions complete for {}", k );

        return true;
    }

    public void disableNotValidStore( ArtifactStore store, ArtifactStoreValidateData validateData )
    {
        store.setDisabled( true );

        // Problem with this way ( how it is sugested in NOS-1892 issue ) is that this is circular reference
        // Calling storeDataManager.storeArtifactStore()  ( No matter if parameter "skipIfExist" is set to true )
        // Will call StoreDataManager default implementation , which is this class store() method.

        //        final ChangeSummary changeSummary = new ChangeSummary( ChangeSummary.SYSTEM_USER,
        //            String.format("Disabling %s due to Unvalid Store: %s StoreKey: %s ",store.getType(), validateData.getErrors() ,store.getKey())
        //        );
        //        try {
        //            storeDataManager.storeArtifactStore( store, changeSummary, true, true, new EventMetadata() );
        //        } catch (IndyDataException e) {
        //            logger.warn("=> Disabling this store has thrown IndyDataException: " + e.getMessage());
        //            throw new IndyDataException("=> Disabling store is not applicable!", null);
        //        }
    }

    @Override
    public Set<StoreKey> getStoreKeysByPkg( String pkg )
    {
        return streamArtifactStoreKeys().filter( key -> key.getPackageType().equals( pkg ) )
                                        .collect( Collectors.toSet() );
    }

    @Override
    public Set<StoreKey> getStoreKeysByPkgAndType( final String pkg, final StoreType type )
    {
        return streamArtifactStoreKeys().filter( key -> key.getPackageType().equals( pkg ) && key.getType() == type )
                                        .collect( Collectors.toSet() );
    }

    @Override
    public Set<Group> affectedBy( Collection<StoreKey> keys, EventMetadata eventMetadata )
    {
        Set<Group> groups = null;
        if ( eventMetadata != null )
        {
            //noinspection rawtypes
            ValuePipe<Set<Group>> valuePipe = (ValuePipe) eventMetadata.get( AFFECTED_GROUPS );
            groups = valuePipe != null ? valuePipe.get() : null;
        }
        if ( groups == null )
        {
            groups = affectedBy( keys );
        }
        return groups;
    }

    public Set<Group> affectedBy( final Collection<StoreKey> keys )
    {
        return affectedByFromStores( keys );
    }

    protected Set<Group> affectedByFromStores( final Collection<StoreKey> keys )
    {
        Logger logger = LoggerFactory.getLogger( getClass() );
        logger.debug( "Getting groups affected by: {}", keys );

        List<StoreKey> toProcess = new ArrayList<>( new HashSet<>( keys ) );

        Set<Group> groups = new HashSet<>();
        if ( toProcess.isEmpty() )
        {
            return groups;
        }

        Set<StoreKey> processed = new HashSet<>();
        final String packageType = toProcess.get( 0 ).getPackageType();

        Set<ArtifactStore> all = this.getStoreKeysByPkgAndType( packageType, group )
                                     .stream()
                                     .map( k -> this.getArtifactStoreInternal( k ).orElse( null ) )
                                     .filter( Objects::nonNull )
                                     .collect( Collectors.toSet() );

        while ( !toProcess.isEmpty() )
        {
            // as long as we have another key to process, pop it off the list (remove it) and process it.
            StoreKey next = toProcess.remove( 0 );
            if ( processed.contains( next ) )
            {
                // if we've already handled this group (via another branch in the group membership tree, etc. then don't bother.
                continue;
            }

            // use this to avoid reprocessing groups we've already encountered.
            processed.add( next );

            for ( ArtifactStore store : all )
            {
                if ( ( store instanceof Group ) && !processed.contains( store.getKey() ) )
                {
                    Group g = (Group) store;
                    if ( g.getConstituents() != null && g.getConstituents().contains( next ) )
                    {
                        groups.add( g );

                        // add this group as another one to process for groups that contain it...and recurse upwards
                        toProcess.add( g.getKey() );
                    }
                }
            }
        }

        return filterAffectedGroups( groups );
    }

    /**
     * Filter unnecessary affected groups in clean-up process. Most likely to exclude all the temp groups.
     */
    public Set<Group> filterAffectedGroups( Set<Group> affectedGroups )
    {
        if ( affectedGroups == null )
        {
            return emptySet();
        }
        if ( trackingConfig == null )
        {
            return affectedGroups;
        }
        String excludeFilter = trackingConfig.affectedGroupsExcludeFilter().orElse( "" );
        logger.debug( "Filter affected groups, exclude: {}", excludeFilter );
        if ( isBlank( excludeFilter ) )
        {
            return affectedGroups;
        }
        return affectedGroups.stream()
                             .filter( s -> !s.getName().matches( excludeFilter ) )
                             .collect( Collectors.toSet() );
    }

    public boolean isExcludedGroup( Group group )
    {
        if ( trackingConfig == null )
        {
            return false;
        }
        String filter = trackingConfig.affectedGroupsExcludeFilter().orElse( "" );
        return isNotBlank( filter ) && group.getName().matches( filter );
    }

    protected CacheProducer getCacheProducer()
    {
        return null;
    }
}
