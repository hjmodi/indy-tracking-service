package org.commonjava.indy.service.tracking.jaxrs;

import io.quarkus.test.junit.QuarkusTest;
import org.commonjava.indy.service.tracking.client.content.BatchDeleteRequest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class AdminResourceTest
{
    @Test
    public void test_AdminResource_0() throws Throwable
    {
        ResponseHelper responseHelper0 = null;
        AdminResource adminResource1 = new AdminResource( responseHelper0 );
        assertEquals( "application/zip", getFieldValue( adminResource1, "MEDIATYPE_APPLICATION_ZIP" ) );

    }

    @Test
    public void test_doDelete_0() throws Throwable
    {
        ResponseHelper responseHelper0 = null;
        AdminResource adminResource1 = new AdminResource( responseHelper0 );
        assertEquals( "application/zip", getFieldValue( adminResource1, "MEDIATYPE_APPLICATION_ZIP" ) );
        UriInfo uriInfo2 = null;
        BatchDeleteRequest batchDeleteRequest3 = new BatchDeleteRequest();
        Response response4 = adminResource1.doDelete( uriInfo2, batchDeleteRequest3 );

    }

    private Object getFieldValue( Object obj, String fieldName )
                    throws InvocationTargetException, SecurityException, IllegalArgumentException,
                    IllegalAccessException
    {
        try
        {
            Field field = obj.getClass().getField( fieldName );
            return field.get( obj );
        }
        catch ( NoSuchFieldException e )
        {
            for ( Method publicMethod : obj.getClass().getMethods() )
            {
                if ( publicMethod.getName().startsWith( "get" ) && publicMethod.getParameterCount() == 0
                                && publicMethod.getName().toLowerCase().equals( "get" + fieldName.toLowerCase() ) )
                {
                    return publicMethod.invoke( obj );
                }
            }
        }
        throw new IllegalArgumentException(
                        "Could not find field or getter " + fieldName + " for class " + obj.getClass().getName() );
    }
}
