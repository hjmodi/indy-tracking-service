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
package org.commonjava.indy.service.tracking.data.infinispan.cpool;

import io.agroal.api.AgroalDataSource;
import io.agroal.api.AgroalDataSourceListener;
import io.agroal.api.configuration.AgroalDataSourceConfiguration;
import io.agroal.api.configuration.supplier.AgroalPropertiesReader;
import org.commonjava.indy.service.tracking.exception.IndyLifecycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;

@ApplicationScoped
public class ConnectionPoolProvider
{
    private final Logger logger = LoggerFactory.getLogger( getClass() );

    @Inject
    ConnectionPoolConfig config;

    //    @Inject
    //    private MetricRegistry metricRegistry;

    public void init() throws IndyLifecycleException
    {
        logger.info( "Starting connection pool binding..." );

        Properties properties = System.getProperties();
        properties.setProperty( INITIAL_CONTEXT_FACTORY, CPInitialContextFactory.class.getName() );
        System.setProperties( properties );

        InitialContext ctx;
        try
        {
            ctx = new InitialContext();
        }
        catch ( NamingException e )
        {
            throw new IndyLifecycleException( "Failed to create JNDI InitialContext for binding datasources", e );
        }

        Map<String, ConnectionPoolInfo> poolConfigs = config.getPools();
        logger.info( "Creating bindings for {} pools from config: {}", poolConfigs.size(), config );
        for ( ConnectionPoolInfo poolInfo : poolConfigs.values() )
        {
            try
            {
                AgroalPropertiesReader propertiesReader =
                                new AgroalPropertiesReader( ConnectionPoolConfig.DS_PROPERTY_PREFIX );
                AgroalDataSourceConfiguration config =
                                propertiesReader.readProperties( poolInfo.getProperties() ).get();
                config.setMetricsEnabled( poolInfo.isUseMetrics() );
                AgroalDataSource ds = AgroalDataSource.from( config, new AgroalDataSourceLogger( poolInfo.getName() ) );

                //                if ( poolInfo.isUseMetrics() )
                //                {
                //                    registerMetrics(ds.getMetrics(), poolInfo.getName());
                //                }
                //                if ( poolInfo.isUseHealthChecks() )
                //                {
                //                    registerHealthChecks(ds, poolInfo.getName());
                //                }

                String jndiName = "java:/comp/env/jdbc/" + poolInfo.getName();
                logger.info( "Binding datasource: {}", jndiName );
                ctx.rebind( jndiName, ds );
            }
            catch ( NamingException e )
            {
                throw new IndyLifecycleException( "Failed to bind datasource: " + poolInfo.getName(), e );
            }
            catch ( SQLException e )
            {
                throw new IndyLifecycleException( "Failed to start datasource: " + poolInfo.getName(), e );
            }
        }
    }

    //    private void registerMetrics(AgroalDataSourceMetrics agroalMetrics, String name) {
    //        metricRegistry.register(name(name, "acquireCount"), (Gauge<Long>) agroalMetrics::acquireCount);
    //        metricRegistry.register(name(name, "creationCount"), (Gauge<Long>) agroalMetrics::creationCount);
    //        metricRegistry.register(name(name, "leakDetectionCount"), (Gauge<Long>) agroalMetrics::leakDetectionCount);
    //        metricRegistry.register(name(name, "destroyCount"), (Gauge<Long>) agroalMetrics::destroyCount);
    //        metricRegistry.register(name(name, "flushCount"), (Gauge<Long>) agroalMetrics::flushCount);
    //        metricRegistry.register(name(name, "invalidCount"), (Gauge<Long>) agroalMetrics::invalidCount);
    //        metricRegistry.register(name(name, "reapCount"), (Gauge<Long>) agroalMetrics::reapCount);
    //
    //        metricRegistry.register(name(name, "activeCount"), (Gauge<Long>) agroalMetrics::activeCount);
    //        metricRegistry.register(name(name, "availableCount"), (Gauge<Long>) agroalMetrics::availableCount);
    //        metricRegistry.register(name(name, "maxUsedCount"), (Gauge<Long>) agroalMetrics::maxUsedCount);
    //        metricRegistry.register(name(name, "awaitingCount"), (Gauge<Long>) agroalMetrics::awaitingCount);
    //        metricRegistry.register(name(name, "blockingTimeAverage"), (Gauge<Duration>) agroalMetrics::blockingTimeAverage);
    //        metricRegistry.register(name(name, "blockingTimeMax"), (Gauge<Duration>) agroalMetrics::blockingTimeMax);
    //        metricRegistry.register(name(name, "blockingTimeTotal"), (Gauge<Duration>) agroalMetrics::blockingTimeTotal);
    //        metricRegistry.register(name(name, "creationTimeAverage"), (Gauge<Duration>) agroalMetrics::creationTimeAverage);
    //        metricRegistry.register(name(name, "creationTimeMax"), (Gauge<Duration>) agroalMetrics::creationTimeMax);
    //        metricRegistry.register(name(name, "creationTimeTotal"), (Gauge<Duration>) agroalMetrics::creationTimeTotal);
    //    }

    //    private void registerHealthChecks( AgroalDataSource ds, String name )
    //    {
    //        metricRegistry.registerHealthCheck( name, () -> {
    //            try (Connection con = ds.getConnection())
    //            {
    //                if ( con.isValid( 5 ) )
    //                {
    //                    return HealthCheckResult.healthy();
    //                }
    //                else
    //                {
    //                    return HealthCheckResult.unhealthy(
    //                                    String.format( "validation check failed for DataSource %s", name ) );
    //                }
    //            }
    //            catch ( SQLException e )
    //            {
    //                return HealthCheckResult.unhealthy( e );
    //            }
    //        } );
    //    }

    private static class AgroalDataSourceLogger
                    implements AgroalDataSourceListener
    {
        private final Logger logger;

        public AgroalDataSourceLogger( String name )
        {
            logger = LoggerFactory.getLogger( AgroalDataSource.class.getName() + ".'" + name + "'" );
        }

        @Override
        public void onConnectionPooled( Connection connection )
        {
            logger.debug( "Added connection {} to the pool", connection );
        }

        @Override
        public void onConnectionAcquire( Connection connection )
        {
            logger.debug( "Connection {} acquired", connection );
        }

        @Override
        public void onConnectionReturn( Connection connection )
        {
            logger.debug( "Connection {} return", connection );
        }

        @Override
        public void onConnectionLeak( Connection connection, Thread thread )
        {
            logger.info( "Connection {} leak. Acquired by {}", connection, thread );
        }

        @Override
        public void beforeConnectionValidation( Connection connection )
        {
            logger.debug( "Connection {} about to be validated", connection );
        }

        @Override
        public void beforeConnectionFlush( Connection connection )
        {
            logger.debug( "Connection {} removed from the pool", connection );
        }

        @Override
        public void beforeConnectionReap( Connection connection )
        {
            logger.debug( "Connection {} idle", connection );
        }

        @Override
        public void onWarning( String message )
        {
            logger.warn( message );
        }

        @Override
        public void onWarning( Throwable throwable )
        {
            logger.warn( "Exception", throwable );
        }

        @Override
        public void onInfo( String message )
        {
            logger.info( message );
        }
    }
}
