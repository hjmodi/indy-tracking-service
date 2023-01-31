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
package org.commonjava.indy.service.tracking.data.metrics;

import org.commonjava.indy.service.tracking.data.ArtifactStoreQuery;
import org.commonjava.indy.service.tracking.exception.IndyDataException;
import org.commonjava.indy.service.tracking.model.ArtifactStore;
import org.commonjava.indy.service.tracking.model.Group;
import org.commonjava.indy.service.tracking.model.HostedRepository;
import org.commonjava.indy.service.tracking.model.RemoteRepository;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SuppressWarnings( "unchecked" )
public class MeasuringStoreQuery<T extends ArtifactStore>
                implements ArtifactStoreQuery<T>
{
    private final ArtifactStoreQuery<ArtifactStore> query;

    private final TraceManager traceManager;

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    public MeasuringStoreQuery( final ArtifactStoreQuery<ArtifactStore> query, final TraceManager traceManager )
    {
        this.query = query;
        this.traceManager = traceManager;
    }

    @Override
    public ArtifactStoreQuery<T> rewrap( final StoreDataManager manager )
    {
        return new MeasuringStoreQuery<>( query.rewrap( manager ), traceManager );
    }

    @Override
    public <C extends ArtifactStore> ArtifactStoreQuery<C> storeType( final Class<C> storeCls )
    {
        query.storeType( storeCls );
        return (ArtifactStoreQuery<C>) this;
    }

    @Override
    public ArtifactStoreQuery<T> storeTypes( final StoreType... types )
    {
        query.storeTypes( types );
        return this;
    }

    @Override
    public ArtifactStoreQuery<T> packageType( String packageType )
    {
        query.packageType( packageType );
        return this;
    }

    @Override
    public ArtifactStoreQuery<T> concreteStores()
    {
        query.concreteStores();
        return this;
    }

    @Override
    public ArtifactStoreQuery<T> enabledState( final Boolean enabled )
    {
        query.enabledState( enabled );
        return this;
    }

    @Override
    public boolean isEmpty()
    {
        return traceManager.wrapWithStandardMetrics( query::isEmpty, () -> "isEmpty" );
    }

    @Override
    public Stream<T> stream() throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        Stream<T> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return (Stream<T>) query.stream();
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "stream" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public Stream<T> stream( final Predicate<ArtifactStore> filter ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        Stream<T> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return (Stream<T>) query.stream( filter );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "stream-with-filter" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public Stream<StoreKey> keyStream() throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        Stream<StoreKey> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.keyStream();
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "keyStream" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public Stream<StoreKey> keyStream( final Predicate<StoreKey> filterPredicate ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        Stream<StoreKey> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.keyStream();
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "keyStream-with-filter" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<T> getAll() throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<T> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return (List<T>) query.getAll();
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getAll" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<T> getAll( final Predicate<ArtifactStore> filter ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<T> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return (List<T>) query.getAll( filter );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getAll-with-filter" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<T> getAllByDefaultPackageTypes() throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<T> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return (List<T>) query.getAllByDefaultPackageTypes();
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getAllByDefaultPackageTypes" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public T getByName( final String name ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        T result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return (T) query.getByName( name );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getByName" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public boolean containsByName( final String name ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        boolean result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.containsByName( name );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "containsByName" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public Set<Group> getGroupsContaining( final StoreKey storeKey ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        Set<Group> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getGroupsContaining( storeKey );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getGroupsContaining" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public Set<Group> getGroupsContaining( final StoreKey storeKey, final Boolean enabled ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        Set<Group> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getGroupsContaining( storeKey );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getGroupsContaining" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<RemoteRepository> getRemoteRepositoryByUrl( final String packageType, final String url )
                    throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<RemoteRepository> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getRemoteRepositoryByUrl( packageType, url );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getRemoteRepositoryByUrl" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<RemoteRepository> getRemoteRepositoryByUrl( final String packageType, final String url,
                                                            final Boolean enabled ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<RemoteRepository> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getRemoteRepositoryByUrl( packageType, url, enabled );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getRemoteRepositoryByUrl" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<ArtifactStore> getOrderedConcreteStoresInGroup( final String packageType, final String groupName )
                    throws IndyDataException
    {
        logger.trace( "START: metric store-query wrapper ordered-concrete-stores-in-group" );
        try
        {
            AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
            List<ArtifactStore> result = traceManager.wrapWithStandardMetrics( () -> {
                try
                {
                    return query.getOrderedConcreteStoresInGroup( packageType, groupName );
                }
                catch ( IndyDataException e )
                {
                    errorRef.set( e );
                }

                return null;
            }, () -> "getOrderedConcreteStoresInGroup" );

            IndyDataException error = errorRef.get();
            if ( error != null )
            {
                throw error;
            }

            return result;
        }
        finally
        {
            logger.trace( "END: metric store-query wrapper ordered-concrete-stores-in-group" );
        }
    }

    @Override
    public List<ArtifactStore> getOrderedConcreteStoresInGroup( final String packageType, final String groupName,
                                                                final Boolean enabled ) throws IndyDataException
    {
        logger.trace( "START: metric store-query wrapper ordered-concrete-stores-in-group" );
        try
        {
            AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
            List<ArtifactStore> result = traceManager.wrapWithStandardMetrics( () -> {
                try
                {
                    return query.getOrderedConcreteStoresInGroup( packageType, groupName, enabled );
                }
                catch ( IndyDataException e )
                {
                    errorRef.set( e );
                }

                return null;
            }, () -> "getOrderedConcreteStoresInGroup" );

            IndyDataException error = errorRef.get();
            if ( error != null )
            {
                throw error;
            }

            return result;
        }
        finally
        {
            logger.trace( "END: metric store-query wrapper ordered-concrete-stores-in-group" );
        }
    }

    @Override
    public List<ArtifactStore> getOrderedStoresInGroup( final String packageType, final String groupName )
                    throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<ArtifactStore> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getOrderedStoresInGroup( packageType, groupName );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getOrderedStoresInGroup" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<ArtifactStore> getOrderedStoresInGroup( final String packageType, final String groupName,
                                                        final Boolean enabled ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<ArtifactStore> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getOrderedStoresInGroup( packageType, groupName, enabled );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getOrderedStoresInGroup" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public Set<Group> getGroupsAffectedBy( final StoreKey... keys ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        Set<Group> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getGroupsAffectedBy( keys );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getGroupsAffectedBy-varargs" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public Set<Group> getGroupsAffectedBy( final Collection<StoreKey> keys ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        Set<Group> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getGroupsAffectedBy( keys );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getGroupsAffectedBy-collection" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public RemoteRepository getRemoteRepository( final String packageType, final String name ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        RemoteRepository result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getRemoteRepository( packageType, name );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getRemoteRepository" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public HostedRepository getHostedRepository( final String packageType, final String name ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        HostedRepository result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getHostedRepository( packageType, name );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getHostedRepository" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public Group getGroup( final String packageType, final String name ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        Group result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getGroup( packageType, name );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getGroup" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public ArtifactStoreQuery<T> noPackageType()
    {
        query.noPackageType();
        return this;
    }

    @Override
    public List<RemoteRepository> getAllRemoteRepositories( String packageType ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<RemoteRepository> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getAllRemoteRepositories( packageType );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getAllRemoteRepositories" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<RemoteRepository> getAllRemoteRepositories( String packageType, Boolean enabled )
                    throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<RemoteRepository> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getAllRemoteRepositories( packageType, enabled );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getAllRemoteRepositories" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<HostedRepository> getAllHostedRepositories( String packageType ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<HostedRepository> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getAllHostedRepositories( packageType );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getAllHostedRepositories" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<HostedRepository> getAllHostedRepositories( String packageType, Boolean enabled )
                    throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<HostedRepository> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getAllHostedRepositories( packageType, enabled );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getAllHostedRepositories" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<Group> getAllGroups( String packageType ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<Group> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getAllGroups( packageType );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getAllGroups" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }

    @Override
    public List<Group> getAllGroups( String packageType, Boolean enabled ) throws IndyDataException
    {
        AtomicReference<IndyDataException> errorRef = new AtomicReference<>();
        List<Group> result = traceManager.wrapWithStandardMetrics( () -> {
            try
            {
                return query.getAllGroups( packageType, enabled );
            }
            catch ( IndyDataException e )
            {
                errorRef.set( e );
            }

            return null;
        }, () -> "getAllGroups" );

        IndyDataException error = errorRef.get();
        if ( error != null )
        {
            throw error;
        }

        return result;
    }
}
