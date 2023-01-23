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

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@ApplicationScoped
public class CassandraClient
{
    private final Logger logger = LoggerFactory.getLogger( getClass() );

    final private Map<String, Session> sessions = new ConcurrentHashMap<>();

    @Inject
    CassandraConfiguration config;

    private String host;

    private int port;

    private String username;

    private Cluster cluster;

    private volatile boolean closed;

    public CassandraClient()
    {
    }

    public CassandraClient( CassandraConfiguration config )
    {
        this.config = config;
        init();
    }

    @PostConstruct
    public void init()
    {
        if ( !config.isEnabled() )
        {
            logger.info( "Cassandra client not enabled" );
            return;
        }

        host = config.getCassandraHost();
        port = config.getCassandraPort();
        SocketOptions socketOptions = new SocketOptions();
        socketOptions.setConnectTimeoutMillis( config.getConnectTimeoutMillis() );
        socketOptions.setReadTimeoutMillis( config.getReadTimeoutMillis() );
        Cluster.Builder builder = Cluster.builder()
                                         .withoutJMXReporting()
                                         .withRetryPolicy( new ConfigurableRetryPolicy( config.getReadRetries(),
                                                                                        config.getWriteRetries() ) )
                                         .addContactPoint( host )
                                         .withPort( port )
                                         .withSocketOptions( socketOptions );
        username = config.getCassandraUser();
        String password = config.getCassandraPass();
        if ( isNotBlank( username ) && isNotBlank( password ) )
        {
            logger.info( "Build with credentials, user: {}, pass: ****", username );
            builder.withCredentials( username, password );
        }
        cluster = builder.build();
    }

    public Session getSession( String keyspace )
    {
        if ( !config.isEnabled() )
        {
            logger.info( "Cassandra client not enabled" );
            return null;
        }

        return sessions.computeIfAbsent( keyspace, key -> {
            logger.info( "Connect to Cassandra, host: {}, port: {}, user: {}, keyspace: {}", host, port, username,
                         key );
            try
            {
                return cluster.connect();
            }
            catch ( Exception e )
            {
                logger.error( "Connecting to Cassandra failed", e );
            }
            return null;
        } );
    }

    public void close()
    {
        if ( !closed && cluster != null )
        {
            logger.info( "Close cassandra client" );
            sessions.forEach( ( key, value ) -> value.close() );
            sessions.clear();
            cluster.close();
            cluster = null;
            closed = true;
        }
    }

    public Map<String, Session> getSessions()
    {
        return sessions;
    }
}
