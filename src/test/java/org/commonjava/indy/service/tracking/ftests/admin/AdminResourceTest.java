package org.commonjava.indy.service.tracking.ftests.admin;

import com.datastax.oss.quarkus.test.CassandraTestResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.mockito.InjectMock;
import org.commonjava.indy.service.tracking.Constants;
import org.commonjava.indy.service.tracking.client.content.BatchDeleteRequest;
import org.commonjava.indy.service.tracking.controller.AdminController;
import org.commonjava.indy.service.tracking.data.cassandra.CassandraClient;
import org.commonjava.indy.service.tracking.data.cassandra.CassandraConfiguration;
import org.commonjava.indy.service.tracking.data.cassandra.CassandraTrackingQuery;
import org.commonjava.indy.service.tracking.exception.ContentException;
import org.commonjava.indy.service.tracking.exception.IndyWorkflowException;
import org.commonjava.indy.service.tracking.ftests.profile.CassandraFunctionProfile;
import org.commonjava.indy.service.tracking.jaxrs.ResponseHelper;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.commonjava.indy.service.tracking.model.TrackingKey;
import org.commonjava.indy.service.tracking.model.dto.TrackedContentDTO;
import org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO;
import org.commonjava.indy.service.tracking.model.dto.TrackingIdsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.CassandraContainer;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
@TestProfile( CassandraFunctionProfile.class )
public class AdminResourceTest
{
    private final String TRACKING_ID = "tracking-id";

    private final String BASE_URL = "api/folo/admin/";

    @InjectMock
    CassandraConfiguration config;

    private volatile CassandraContainer<?> cassandraContainer;

    @BeforeEach
    public void start() throws Exception
    {
        this.cassandraContainer = (CassandraContainer) ( new CassandraContainer() );
        URL resource = Thread.currentThread().getContextClassLoader().getResource( "init_script.cql" );
        if ( resource != null )
        {
            this.cassandraContainer.withInitScript( "init_script.cql" );
        }
        this.cassandraContainer.start();
        String host = this.cassandraContainer.getHost();
        int port = this.cassandraContainer.getMappedPort( CassandraContainer.CQL_PORT );
        when(config.getCassandraHost()).thenReturn( host );
        when(config.getCassandraPort()).thenReturn( port );
        when(config.getCassandraUser()).thenReturn( "cassandra" );
        when(config.getCassandraPass()).thenReturn( "cassandra" );
        when(config.getKeyspace()).thenReturn( "folo" );
        when(config.getKeyspaceReplicas()).thenReturn( 1 );
        when(config.isEnabled()).thenReturn( true );
    }



    @Inject
    ObjectMapper mapper;

    @Inject
    private ResponseHelper responseHelper;

    @Test
    void testRecalculateRecordSuccess() throws IndyWorkflowException
    {
        TrackedContentDTO trackedContentDTO = new TrackedContentDTO();
        given().when()
               .get( BASE_URL + TRACKING_ID + "/record/recalculate" )
               .then()
               .statusCode( 200 )
               .body( is( responseHelper.formatOkResponseWithJsonEntity( trackedContentDTO ).getEntity().toString() ) );
    }

    @Test
    void testRecalculateRecordNotFound() throws IndyWorkflowException
    {
        given().when().get( BASE_URL + TRACKING_ID + "/record/recalculate" ).then().statusCode( 404 );
    }

    @Test
    void testRecalculateRecordFailed() throws IndyWorkflowException
    {
        given().when().get( BASE_URL + TRACKING_ID + "/record/recalculate" ).then().statusCode( 404 );
    }

    @Test
    public void testGetZipRepository() throws IOException
    {
        File file = File.createTempFile( "test", ".zip" );

        given().when().get( BASE_URL + TRACKING_ID + "/record/zip" ).then().statusCode( 200 ).body( is( "" ) );
    }

    @Test
    void testGetRecordReturnsOkResponse() throws IndyWorkflowException
    {
        // given
        TrackedContentDTO trackedContentDTO = new TrackedContentDTO();
        trackedContentDTO.setKey( new TrackingKey( TRACKING_ID ) );

        given().when()
               .get( BASE_URL + TRACKING_ID + "/record" )
               .then()
               .statusCode( 200 )
               .body( is( responseHelper.formatOkResponseWithJsonEntity( trackedContentDTO ).getEntity().toString() ) );

        given().when()
               .get( BASE_URL + TRACKING_ID + "/record" )
               .then()
               .statusCode( 200 )
               .body( is( responseHelper.formatOkResponseWithJsonEntity( trackedContentDTO ).getEntity().toString() ) );

    }

    @Test
    void testGetRecordReturnsNotFoundResponse() throws IndyWorkflowException
    {
        // given

        // when
        given().when().get( BASE_URL + TRACKING_ID + "/record" ).then().statusCode( 500 );
    }

    @Test
    void testSealRecordSuccess()
    {
        // Mock controller methods

        // Call the function
        given().when().post( BASE_URL + TRACKING_ID + "/record" ).then().statusCode( 200 );
    }

    @Test
    void testSealRecordError()
    {
        // Mock controller methods
        final String errorMessage = "Failed to seal tracking record.";

        // Call the function
        given().when().post( BASE_URL + TRACKING_ID + "/record" ).then().statusCode( 500 );
    }

    @Test
    public void testClearRecordSuccess() throws ContentException
    {
        // Mock the controller's behavior

        // Act
        // Call the function
        given().when().delete( BASE_URL + TRACKING_ID + "/record" ).then().statusCode( 204 );

        // Assert
    }

    @Test
    public void testClearRecordError() throws ContentException
    {
        String errorMessage = "An error occurred while clearing the record.";

        // Mock the controller's behavior

        // Act
        given().when().delete( BASE_URL + TRACKING_ID + "/record" ).then().statusCode( 500 );

        // Assert
    }

    @Test
    public void testGetRecordIdsSuccess()
    {
        // Set up mock response from adminController
        Set<Constants.TRACKING_TYPE> types = new HashSet<>();
        types.add( Constants.TRACKING_TYPE.SEALED );
        String expected_string = "{\"inProgress\":null,\"sealed\":null}";


        // Call getRecordIds() function
        given().when().get( BASE_URL + "report/ids/sealed" ).then().statusCode( 200 ).body( is( expected_string ) );

        given().when().get( BASE_URL + "report/ids/legacy" ).then().statusCode( 200 ).body( is( expected_string ) );

        // Verify that the getAllTrackingIds() function was called on the adminController

    }

    @Test
    public void testGetRecordIdsNotFound()
    {
        // Set up mock response from adminController

        // Call getRecordIds() function
        given().when().get( BASE_URL + "report/ids/legacy" ).then().statusCode( 404 );

        // Verify that the getAllTrackingIds() function was called on the adminController
    }

    @Test
    public void testExportReportSuccess() throws IndyWorkflowException, IOException
    {
        File file = File.createTempFile( "test", ".zip" );
        // Set up mock response from adminController
        given().when().get( BASE_URL + "report/export" ).then().statusCode( 200 ).body( is( "" ) );
    }

    @Test
    public void testExportReportError() throws IndyWorkflowException
    {
        // Set up mock response from adminController
        given().when().get( BASE_URL + "report/export" ).then().statusCode( 500 );
    }

    @Test
    public void testImportReportSuccess() throws IndyWorkflowException
    {
        given().when().put( BASE_URL + "report/import" ).then().statusCode( 201 );
    }

    @Test
    public void testImportReportError() throws IndyWorkflowException, IOException
    {
        // Set up mock response from adminController
        given().when().put( BASE_URL + "report/import" ).then().statusCode( 500 );
    }

    @Test
    public void testDoDeleteSuccess() throws IndyWorkflowException, JsonProcessingException
    {
        StoreKey storeKey = new StoreKey( "maven", StoreType.remote, "test" );
        BatchDeleteRequest batchDeleteRequest = new BatchDeleteRequest();
        batchDeleteRequest.setStoreKey( storeKey );
        batchDeleteRequest.setTrackingID( TRACKING_ID );

        TrackedContentDTO trackedContentDTO = new TrackedContentDTO();
        TrackedContentEntryDTO entry = new TrackedContentEntryDTO();
        entry.setPath( "test" );
        Set<TrackedContentEntryDTO> entries = new HashSet<>();
        entries.add( entry );
        trackedContentDTO.setUploads( entries );
        trackedContentDTO.setKey( new TrackingKey( TRACKING_ID ) );

        given().header( "Content-type", "application/json" )
               .and()
               .body( mapper.writeValueAsString( batchDeleteRequest ) )
               .when()
               .post( BASE_URL + "batch/delete" )
               .then()
               .statusCode( 200 );
    }

    @Test
    public void testDoDeleteError() throws IndyWorkflowException, JsonProcessingException
    {
        StoreKey storeKey = new StoreKey( "maven", StoreType.remote, "test" );
        BatchDeleteRequest batchDeleteRequest1 = new BatchDeleteRequest();
        batchDeleteRequest1.setStoreKey( storeKey );
        batchDeleteRequest1.setTrackingID( TRACKING_ID );

        BatchDeleteRequest batchDeleteRequest2 = new BatchDeleteRequest();
        batchDeleteRequest2.setStoreKey( null );
        batchDeleteRequest2.setTrackingID( null );

        TrackedContentDTO trackedContentDTO = new TrackedContentDTO();
        TrackedContentEntryDTO entry = new TrackedContentEntryDTO();
        entry.setPath( "test" );
        Set<TrackedContentEntryDTO> entries = new HashSet<>();
        entries.add( entry );
        trackedContentDTO.setUploads( entries );
        trackedContentDTO.setKey( new TrackingKey( TRACKING_ID ) );
        given().header( "Content-type", "application/json" )
               .and()
               .body( mapper.writeValueAsString( batchDeleteRequest1 ) )
               .when()
               .post( BASE_URL + "batch/delete" )
               .then()
               .statusCode( 400 );
        given().header( "Content-type", "application/json" )
               .and()
               .body( mapper.writeValueAsString( batchDeleteRequest2 ) )
               .when()
               .post( BASE_URL + "batch/delete" )
               .then()
               .statusCode( 400 );

    }
}
