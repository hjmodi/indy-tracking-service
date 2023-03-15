package org.commonjava.indy.service.tracking.ftests.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.commonjava.indy.service.tracking.client.content.BatchDeleteRequest;
import org.commonjava.indy.service.tracking.ftests.profile.SecuredFunctionProfile;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestProfile( SecuredFunctionProfile.class )
public class AdminResourceSecurityTest
{
    private final String TRACKING_ID = "tracking-id";

    private final String BASE_URL = "api/folo/admin/";

    @Inject
    ObjectMapper mapper;

    @Test
    void testRecalculateRecord()
    {
        given().when().get( BASE_URL + TRACKING_ID + "/record/recalculate" ).then().statusCode( 500 );
    }

    @Test
    public void testGetZipRepository()
    {
        given().when().get( BASE_URL + TRACKING_ID + "/record/zip" ).then().statusCode( 500 );
    }

    @Test
    void testGetRecord()
    {
        given().when().get( BASE_URL + TRACKING_ID + "/record" ).then().statusCode( 500 );
    }

    @Test
    void testSealRecord()
    {
        given().when().post( BASE_URL + TRACKING_ID + "/record" ).then().statusCode( 403 );
    }

    @Test
    public void testClearRecord()
    {
        given().when().delete( BASE_URL + TRACKING_ID + "/record" ).then().statusCode( 403 );
    }

    @Test
    public void testGetRecordIds()
    {
        given().when().get( BASE_URL + "report/ids/sealed" ).then().statusCode( 500 );
        given().when().get( BASE_URL + "report/ids/legacy" ).then().statusCode( 500 );
    }

    @Test
    public void testExportReport()
    {
        given().when().get( BASE_URL + "report/export" ).then().statusCode( 500 );
    }

    @Test
    public void testImportReport()
    {
        given().when().put( BASE_URL + "report/import" ).then().statusCode( 403 );
    }

    @Test
    public void testDoDelete() throws JsonProcessingException
    {
        StoreKey storeKey = new StoreKey( "maven", StoreType.remote, "test" );
        BatchDeleteRequest batchDeleteRequest = new BatchDeleteRequest();
        batchDeleteRequest.setStoreKey( storeKey );
        batchDeleteRequest.setTrackingID( TRACKING_ID );

        given().header( "Content-type", "application/json" )
               .and()
               .body( mapper.writeValueAsString( batchDeleteRequest ) )
               .when()
               .post( BASE_URL + "batch/delete" )
               .then()
               .statusCode( 403 );
    }

}
