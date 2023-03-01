package org.commonjava.indy.service.tracking.ftests.admin;

import io.quarkus.test.junit.QuarkusTest;
import org.commonjava.indy.service.tracking.Constants;
import org.commonjava.indy.service.tracking.client.content.BatchDeleteRequest;
import org.commonjava.indy.service.tracking.client.content.MaintenanceService;
import org.commonjava.indy.service.tracking.controller.AdminController;
import org.commonjava.indy.service.tracking.exception.ContentException;
import org.commonjava.indy.service.tracking.exception.IndyWorkflowException;
import org.commonjava.indy.service.tracking.jaxrs.AdminResource;
import org.commonjava.indy.service.tracking.jaxrs.ResponseHelper;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.TrackingKey;
import org.commonjava.indy.service.tracking.model.dto.TrackedContentDTO;
import org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO;
import org.commonjava.indy.service.tracking.model.dto.TrackingIdsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.wildfly.common.Assert.assertNotNull;

@QuarkusTest
public class AdminResourceTest
{
    private final String TRACKING_ID = "tracking-id";

    private final String BASE_URL = "http://localhost:8080";

    @InjectMocks
    private AdminResource adminResource;

    @Inject
    private ResponseHelper responseHelper;

    @Mock
    private AdminController adminControllerMock;

    @Mock
    private MaintenanceService maintenanceService;

    @Mock
    private UriInfo uriInfo;

    @BeforeEach
    void setup()
    {
        adminResource = new AdminResource( responseHelper );
        MockitoAnnotations.openMocks( this );
        // mock UriInfo
        UriBuilder uriBuilder = UriBuilder.fromPath( BASE_URL );
        when( uriInfo.getBaseUriBuilder() ).thenReturn( uriBuilder );
    }

    @Test
    void testRecalculateRecordSuccess() throws IndyWorkflowException
    {
        TrackedContentDTO trackedContentDTO = new TrackedContentDTO();
        when( adminControllerMock.recalculateRecord( TRACKING_ID, BASE_URL + "/api" ) ).thenReturn( trackedContentDTO );
        Response response = adminResource.recalculateRecord( TRACKING_ID, uriInfo );

        assertEquals( Response.ok( trackedContentDTO ).build().getStatus(), response.getStatus() );
        assertEquals( responseHelper.formatOkResponseWithJsonEntity( trackedContentDTO ).getEntity().toString(),
                      response.getEntity().toString() );
    }

    @Test
    void testRecalculateRecordNotFound() throws IndyWorkflowException
    {
        when( adminControllerMock.recalculateRecord( TRACKING_ID, "" ) ).thenReturn( null );
        Response response = adminResource.recalculateRecord( TRACKING_ID, uriInfo );

        assertEquals( Response.status( Response.Status.NOT_FOUND ).build().getStatus(), response.getStatus() );
    }

    @Test
    void testRecalculateRecordFailed() throws IndyWorkflowException
    {
        when( adminControllerMock.recalculateRecord( TRACKING_ID, "" ) ).thenThrow( new IndyWorkflowException( "" ) );
        //when( responseHelper.formatResponse( any( IndyWorkflowException.class ) ) ).thenReturn(
        //              Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build() );

        Response response = adminResource.recalculateRecord( TRACKING_ID, uriInfo );

        assertEquals( Response.status( Response.Status.NOT_FOUND ).build().getStatus(), response.getStatus() );
    }

    @Test
    public void testGetZipRepository()
    {
        File file = new File( "test.zip" );
        when( adminControllerMock.getZipRepository( TRACKING_ID ) ).thenReturn( file );

        File result = adminResource.getZipRepository( TRACKING_ID );

        assertNotNull( result );
        assertEquals( file.getName(), result.getName() );
    }

    @Test
    void testGetRecordReturnsOkResponse() throws IndyWorkflowException
    {
        // given
        TrackedContentDTO trackedContentDTO = new TrackedContentDTO();
        trackedContentDTO.setKey( new TrackingKey( TRACKING_ID ) );
        when( adminControllerMock.getRecord( anyString(), anyString() ) ).thenReturn( trackedContentDTO, null );
        when( adminControllerMock.getLegacyRecord( anyString(), anyString() ) ).thenReturn( trackedContentDTO );

        // when
        Response response = adminResource.getRecord( TRACKING_ID, uriInfo );

        // then
        assertEquals( Response.Status.OK.getStatusCode(), response.getStatus() );
        assertEquals( responseHelper.formatOkResponseWithJsonEntity( trackedContentDTO ).getEntity().toString(),
                      response.getEntity().toString() );

        response = adminResource.getRecord( TRACKING_ID, uriInfo );

        // then
        assertEquals( Response.Status.OK.getStatusCode(), response.getStatus() );
        assertEquals( responseHelper.formatOkResponseWithJsonEntity( trackedContentDTO ).getEntity().toString(),
                      response.getEntity().toString() );

        verify( adminControllerMock, times( 2 ) ).getRecord( anyString(), anyString() );
        verify( adminControllerMock, times( 1 ) ).getLegacyRecord( anyString(), anyString() );
    }

    @Test
    void testGetRecordReturnsNotFoundResponse() throws IndyWorkflowException
    {
        // given
        when( adminControllerMock.getRecord( anyString(), anyString() ) ).thenThrow(
                        new IndyWorkflowException( "test" ) );

        // when
        Response response = adminResource.getRecord( TRACKING_ID, uriInfo );

        // then
        assertEquals( Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus() );
    }

    @Test
    void testSealRecordSuccess()
    {
        // Mock controller methods
        when( adminControllerMock.seal( anyString(), anyString() ) ).thenReturn( new TrackedContentDTO() );

        // Call the function
        final Response response = adminResource.sealRecord( TRACKING_ID, uriInfo );

        // Verify the results
        assertEquals( Response.Status.OK.getStatusCode(), response.getStatus() );
    }

    @Test
    void testSealRecordError()
    {
        // Mock controller methods
        final String errorMessage = "Failed to seal tracking record.";
        when( adminControllerMock.seal( anyString(), anyString() ) ).thenThrow( new RuntimeException( errorMessage ) );

        // Mock uriInfo methods
        when( uriInfo.getBaseUriBuilder() ).thenReturn( UriBuilder.fromPath( BASE_URL ).path( "api" ) );

        // Call the function
        assertThrows( RuntimeException.class, () -> {
            adminResource.sealRecord( TRACKING_ID, uriInfo );
        } );
    }

    @Test
    public void testClearRecordSuccess() throws IndyWorkflowException, ContentException
    {
        // Mock the controller's behavior
        doNothing().when( adminControllerMock ).clearRecord( TRACKING_ID );

        // Act
        Response response = adminResource.clearRecord( TRACKING_ID );

        // Assert
        assertEquals( Response.Status.NO_CONTENT.getStatusCode(), response.getStatus() );
        verify( adminControllerMock ).clearRecord( TRACKING_ID );
    }

    @Test
    public void testClearRecordError() throws IndyWorkflowException, ContentException
    {
        String errorMessage = "An error occurred while clearing the record.";

        // Mock the controller's behavior
        doThrow( new ContentException( errorMessage ) ).when( adminControllerMock ).clearRecord( TRACKING_ID );

        // Act
        Response response = adminResource.clearRecord( TRACKING_ID );

        // Assert
        verify( adminControllerMock ).clearRecord( TRACKING_ID );
        //verify( responseHelper ).formatResponse( any( ContentException.class ) );
        assertEquals( Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus() );
    }

    @Test
    public void testGetRecordIdsSuccess()
    {
        // Set up mock response from adminController
        Set<Constants.TRACKING_TYPE> types = new HashSet<>();
        types.add( Constants.TRACKING_TYPE.SEALED );

        when( adminControllerMock.getLegacyTrackingIds() ).thenReturn( new TrackingIdsDTO() );
        when( adminControllerMock.getTrackingIds( types ) ).thenReturn( new TrackingIdsDTO() );

        // Call getRecordIds() function
        Response response1 = adminResource.getRecordIds( "sealed" );
        Response response2 = adminResource.getRecordIds( "legacy" );

        // Verify that the response is successful and returns the expected data
        assertEquals( Response.Status.OK.getStatusCode(), response1.getStatus() );
        assertEquals( Response.Status.OK.getStatusCode(), response2.getStatus() );
        String expected_string = "{\"inProgress\":null,\"sealed\":null}";

        assertEquals( expected_string, response1.getEntity().toString() );
        assertEquals( expected_string, response2.getEntity().toString() );

        // Verify that the getAllTrackingIds() function was called on the adminController
        verify( adminControllerMock, times( 1 ) ).getLegacyTrackingIds();
        verify( adminControllerMock, times( 1 ) ).getTrackingIds( types );
    }

    @Test
    public void testGetRecordIdsNotFound()
    {
        // Set up mock response from adminController
        when( adminControllerMock.getLegacyTrackingIds() ).thenReturn( null );

        // Call getRecordIds() function
        Response response = adminResource.getRecordIds( "legacy" );

        // Verify that the response is successful and returns the expected data
        assertEquals( Response.Status.NOT_FOUND.getStatusCode(), response.getStatus() );

        // Verify that the getAllTrackingIds() function was called on the adminController
        verify( adminControllerMock, times( 1 ) ).getLegacyTrackingIds();
    }

    @Test
    public void testExportReportSuccess() throws IndyWorkflowException
    {
        // Set up mock response from adminController
        when( adminControllerMock.renderReportZip() ).thenReturn( new File( "testfile" ) );
        File file = adminResource.exportReport();
        assertEquals( "testfile", file.getName() );
    }

    @Test
    public void testExportReportError() throws IndyWorkflowException
    {
        // Set up mock response from adminController
        when( adminControllerMock.renderReportZip() ).thenThrow( new IndyWorkflowException( "test" ) );
        assertThrows( NullPointerException.class, () -> {
            adminResource.exportReport();
        } );
    }

    @Test
    public void testImportReportSuccess() throws IndyWorkflowException
    {
        doNothing().when( adminControllerMock ).importRecordZip( any() );
        HttpServletRequest httpServletRequest = mock( HttpServletRequest.class );
        Response response = adminResource.importReport( uriInfo, httpServletRequest );
        assertEquals( Response.Status.CREATED.getStatusCode(), response.getStatus() );
    }

    @Test
    public void testImportReportError() throws IndyWorkflowException, IOException
    {
        // Set up mock response from adminController
        doThrow( new IndyWorkflowException( "test" ) ).when( adminControllerMock ).importRecordZip( any() );
        HttpServletRequest httpServletRequest = mock( HttpServletRequest.class );
        when( httpServletRequest.getInputStream() ).thenThrow( new IOException() ).thenReturn( null );
        assertThrows( NullPointerException.class, () -> {
            adminResource.importReport( uriInfo, httpServletRequest );
        } );
        assertThrows( NullPointerException.class, () -> {
            adminResource.importReport( uriInfo, httpServletRequest );
        } );
    }

    @Test
    public void testDoDeleteSuccess() throws IndyWorkflowException
    {
        BatchDeleteRequest batchDeleteRequest = mock( BatchDeleteRequest.class );
        when( batchDeleteRequest.getTrackingID() ).thenReturn( TRACKING_ID );
        when( batchDeleteRequest.getStoreKey() ).thenReturn( new StoreKey() );
        when( maintenanceService.doDelete( any() ) ).thenReturn( Response.ok().build() );

        TrackedContentDTO trackedContentDTO = new TrackedContentDTO();
        TrackedContentEntryDTO entry = new TrackedContentEntryDTO();
        entry.setPath( "test" );
        Set<TrackedContentEntryDTO> entries = new HashSet<>();
        entries.add( entry );
        trackedContentDTO.setUploads( entries );
        trackedContentDTO.setKey( new TrackingKey( TRACKING_ID ) );
        when( adminControllerMock.getRecord( anyString(), anyString() ) ).thenReturn( trackedContentDTO, null );
        Response response = adminResource.doDelete( uriInfo, batchDeleteRequest );
        assertEquals( Response.Status.OK, response.getStatusInfo().toEnum() );
    }

    @Test
    public void testDoDeleteError() throws IndyWorkflowException
    {
        BatchDeleteRequest batchDeleteRequest = mock( BatchDeleteRequest.class );
        when( batchDeleteRequest.getTrackingID() ).thenReturn( TRACKING_ID );
        when( batchDeleteRequest.getStoreKey() ).thenReturn( null, new StoreKey() );

        TrackedContentDTO trackedContentDTO = new TrackedContentDTO();
        TrackedContentEntryDTO entry = new TrackedContentEntryDTO();
        entry.setPath( "test" );
        Set<TrackedContentEntryDTO> entries = new HashSet<>();
        entries.add( entry );
        trackedContentDTO.setUploads( entries );
        trackedContentDTO.setKey( new TrackingKey( TRACKING_ID ) );
        when( adminControllerMock.getRecord( anyString(), anyString() ) ).thenReturn( null );
        Response response1 = adminResource.doDelete( uriInfo, batchDeleteRequest );
        Response response2 = adminResource.doDelete( uriInfo, batchDeleteRequest );
        assertEquals( Response.Status.BAD_REQUEST, response1.getStatusInfo().toEnum() );
        assertEquals( Response.Status.BAD_REQUEST, response2.getStatusInfo().toEnum() );
        verify( batchDeleteRequest, times( 2 ) ).getStoreKey();
        verify( adminControllerMock, times( 1 ) ).getRecord( anyString(), anyString() );
    }
}
