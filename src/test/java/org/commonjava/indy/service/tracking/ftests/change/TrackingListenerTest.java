package org.commonjava.indy.service.tracking.ftests.change;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.reactive.messaging.providers.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.providers.connectors.InMemorySource;
import org.commonjava.event.common.EventMetadata;
import org.commonjava.event.file.FileEvent;
import org.commonjava.event.file.FileEventType;
import org.commonjava.event.promote.PathsPromoteCompleteEvent;
import org.commonjava.indy.service.tracking.Constants;
import org.commonjava.indy.service.tracking.data.cassandra.CassandraConfiguration;
import org.commonjava.indy.service.tracking.data.cassandra.CassandraTrackingQuery;
import org.commonjava.indy.service.tracking.model.AccessChannel;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.commonjava.indy.service.tracking.model.TrackedContent;
import org.commonjava.indy.service.tracking.model.TrackedContentEntry;
import org.commonjava.indy.service.tracking.model.TrackingKey;
import org.commonjava.indy.service.tracking.model.pkg.PackageTypeConstants;
import org.commonjava.indy.service.tracking.profile.CassandraFunctionProfile;
import org.commonjava.indy.service.tracking.profile.KafkaFileEventProfile;
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
public class TrackingListenerTest
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

    @Test
    void TestHandleFileAccessEvent() throws InterruptedException
    {
        InMemorySource<FileEvent> fileEvents = connector.source( "file-event-in" );
        FileEvent event = new FileEvent( FileEventType.ACCESS );
        EventMetadata metadata = new EventMetadata();
        metadata.set( Constants.ACCESS_CHANNEL, AccessChannel.GENERIC_PROXY );
        event.setEventMetadata( metadata );
        String trackingId = "access-tracking-id";
        event.setSessionId( trackingId );
        StoreKey storeKey = new StoreKey( PackageTypeConstants.PKG_TYPE_MAVEN, StoreType.remote, "test" );
        event.setStoreKey( storeKey.toString() );
        event.setTargetPath( "path/to/file" );
        event.setSourceLocation( "file://path/to/sourcefile" );
        event.setSize( 123L );
        event.setMd5( "md5123" );
        event.setSha1( "sha112345" );
        event.setChecksum( "sha256123" );
        event.setSourcePath( "/path/to/sourcefile" );
        fileEvents.send( event );
        sleep( 10000 );
        TrackedContent content = trackingQuery.get( new TrackingKey( trackingId ) );
        assert content != null;
    }

    @Test
    void TestHandleFileStorageEvent() throws InterruptedException
    {
        InMemorySource<FileEvent> fileEvents = connector.source( "file-event-in" );
        FileEvent event = new FileEvent( FileEventType.STORAGE );
        EventMetadata metadata = new EventMetadata();
        metadata.set( Constants.ACCESS_CHANNEL, AccessChannel.GENERIC_PROXY );
        event.setEventMetadata( metadata );
        String trackingId = "access-tracking-id1";
        event.setSessionId( trackingId );
        StoreKey storeKey = new StoreKey( PackageTypeConstants.PKG_TYPE_MAVEN, StoreType.remote, "test" );
        event.setStoreKey( storeKey.toString() );
        event.setTargetPath( "path/to/file" );
        event.setSourceLocation( "file://path/to/sourcefile" );
        event.setSize( 123L );
        event.setMd5( "md5123" );
        event.setSha1( "sha112345" );
        event.setChecksum( "sha256123" );
        event.setSourcePath( "/path/to/sourcefile" );
        fileEvents.send( event );
        sleep( 10000 );
        TrackedContent content = trackingQuery.get( new TrackingKey( trackingId ) );
        assert content != null;
    }

    @Test
    void TestOnPromoteComplete() throws InterruptedException
    {
        InMemorySource<PathsPromoteCompleteEvent> pathsPromoteCompleteEvents = connector.source( "promote-event-in" );
        PathsPromoteCompleteEvent event = new PathsPromoteCompleteEvent();
        Set<String> paths = new HashSet<>();
        paths.add( "/path/to/file" );
        event.setCompletedPaths( paths );
        String trackingId = "abc124";
        StoreKey sourceStore = new StoreKey( PackageTypeConstants.PKG_TYPE_MAVEN, StoreType.hosted, trackingId );
        StoreKey targetStore = new StoreKey( PackageTypeConstants.PKG_TYPE_MAVEN, StoreType.remote, "new-test" );
        event.setSourceStore( sourceStore.toString() );
        event.setTargetStore( targetStore.toString() );
        pathsPromoteCompleteEvents.send( event );
        sleep( 200000 );
        TrackedContent trackedContent = trackingQuery.get( new TrackingKey( trackingId ) );
        assert trackedContent != null;
        Set<TrackedContentEntry> uploads = trackedContent.getUploads();
        uploads.forEach( entry -> {
            assert entry.getStoreKey().toString().equals( targetStore.toString() );
        } );
    }

}
