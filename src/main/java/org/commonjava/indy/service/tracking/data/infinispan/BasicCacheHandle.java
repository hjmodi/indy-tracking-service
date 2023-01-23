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

import org.commonjava.indy.service.tracking.data.metrics.TraceManager;
import org.infinispan.commons.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.commonjava.indy.service.tracking.data.metrics.NameUtils.name;

public class BasicCacheHandle<K, V>
{
    protected BasicCache<K, V> cache;

    protected TraceManager traceManager;

    private String name;

    private String metricPrefix;

    private boolean stopped;

    protected BasicCacheHandle()
    {
    }

    protected BasicCacheHandle( String named, BasicCache<K, V> cache, TraceManager traceManager, String metricPrefix )
    {
        this.name = named;
        this.cache = cache;
        this.traceManager = traceManager;
        this.metricPrefix = metricPrefix;
    }

    public String getMetricPrefix()
    {
        return metricPrefix;
    }

    public boolean isStopped()
    {
        return stopped;
    }

    public String getName()
    {
        return name;
    }

    public BasicCache<K, V> getCache()
    {
        return this.cache;
    }

    public <R> R execute( Function<BasicCache<K, V>, R> operation )
    {
        return doExecute( "execute", operation );
    }

    protected <R> R doExecute( String metricName, Function<BasicCache<K, V>, R> operation )
    {
        Supplier<R> execution = executionFor( operation );
        if ( traceManager != null )
        {
            return traceManager.wrapWithStandardMetrics( execution, () -> getMetricName( metricName ) );
        }

        return execution.get();
    }

    private <R> Supplier<R> executionFor( Function<BasicCache<K, V>, R> operation )
    {
        return () -> {
            Logger logger = LoggerFactory.getLogger( getClass() );

            if ( !stopped )
            {
                try
                {
                    return operation.apply( cache );
                }
                catch ( RuntimeException e )
                {
                    logger.error( "Failed to complete operation: " + e.getMessage(), e );
                }
            }
            else
            {
                logger.error( "Cannot complete operation. Cache {} is shutting down.", name );
            }

            return null;
        };
    }

    public void stop()
    {
        Logger logger = LoggerFactory.getLogger( getClass() );
        logger.info( "Cache {} is shutting down!", name );
        this.stopped = true;
    }

    public boolean containsKey( K key )
    {
        return doExecute( "containsKey", cache -> cache.containsKey( key ) );
    }

    public V put( K key, V value )
    {
        return doExecute( "put", cache -> cache.put( key, value ) );
    }

    public V put( K key, V value, int expiration, TimeUnit timeUnit )
    {
        return doExecute( "put-with-expiration", cache -> cache.put( key, value, expiration, timeUnit ) );
    }

    public V putIfAbsent( K key, V value )
    {
        return doExecute( "putIfAbsent", ( c ) -> c.putIfAbsent( key, value ) );
    }

    public V computeIfAbsent( K key, Function<? super K, ? extends V> mappingFunction )
    {
        return doExecute( "computeIfAbsent", c -> c.computeIfAbsent( key, mappingFunction ) );
    }

    public V remove( K key )
    {
        return doExecute( "remove", cache -> cache.remove( key ) );
    }

    public V get( K key )
    {
        return doExecute( "get", cache -> cache.get( key ) );
    }

    /**
     * WARNING: Be careful to use this clear operation, because we don't know if it will swept out all persistent data
     * of this cache if the persistence has been enabled for it!!!
     */
    public void clear()
    {
        doExecute( "clear", cache -> {
            cache.clear();
            return null;
        } );
    }

    protected String getMetricName( String opName )
    {
        return name( metricPrefix, opName );
    }

    public boolean isEmpty()
    {
        return execute( Map::isEmpty );
    }

}
