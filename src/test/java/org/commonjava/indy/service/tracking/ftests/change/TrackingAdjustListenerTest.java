package org.commonjava.indy.service.tracking.ftests.change;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.reactive.messaging.providers.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.providers.connectors.InMemorySource;
import org.commonjava.event.promote.PathsPromoteCompleteEvent;
import org.commonjava.indy.service.tracking.data.cassandra.CassandraConfiguration;
import org.commonjava.indy.service.tracking.data.cassandra.CassandraTrackingQuery;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.commonjava.indy.service.tracking.model.TrackedContent;
import org.commonjava.indy.service.tracking.model.TrackedContentEntry;
import org.commonjava.indy.service.tracking.model.TrackingKey;
import org.commonjava.indy.service.tracking.model.pkg.PackageTypeConstants;
import org.commonjava.indy.service.tracking.profile.CassandraFunctionProfile;
import org.commonjava.indy.service.tracking.profile.KafkaPromoteEventProfile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.CassandraContainer;

import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.mockito.Mockito.when;

@QuarkusTest
@TestProfile( CassandraFunctionProfile.class )
public class TrackingAdjustListenerTest
{
    private static volatile CassandraContainer<?> cassandraContainer;

    @Inject
    @Any
    InMemoryConnector connector;

    @InjectMock
    CassandraConfiguration config;

    @Inject
    CassandraTrackingQuery trackingQuery;

    @BeforeAll
    public static void init()
    {
        cassandraContainer = (CassandraContainer) ( new CassandraContainer() );
        String initScript = "folo_init_script.cql";
        URL resource = Thread.currentThread().getContextClassLoader().getResource( initScript );
        if ( resource != null )
        {
            cassandraContainer.withInitScript( initScript );
        }
        cassandraContainer.start();
    }

    @AfterAll
    public static void stop()
    {
        cassandraContainer.stop();
    }

    @BeforeEach
    public void start()
    {
        String host = cassandraContainer.getHost();
        int port = cassandraContainer.getMappedPort( CassandraContainer.CQL_PORT );
        when( config.getCassandraHost() ).thenReturn( host );
        when( config.getCassandraPort() ).thenReturn( port );
        when( config.getCassandraUser() ).thenReturn( "cassandra" );
        when( config.getCassandraPass() ).thenReturn( "cassandra" );
        when( config.getKeyspace() ).thenReturn( "folo" );
        when( config.getKeyspaceReplicas() ).thenReturn( 1 );
        when( config.isEnabled() ).thenReturn( true );
    }

}
