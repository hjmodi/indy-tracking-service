package org.commonjava.indy.service.tracking.client;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.ClientRequestContext;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@QuarkusTest
public class CustomClientRequestFilterTest
{
    @Test
    public void test_filter_0() throws Throwable
    {
        CustomClientRequestFilter customClientRequestFilter0 = new CustomClientRequestFilter();
        ClientRequestContext clientRequestContext1 = null;
        customClientRequestFilter0.filter( clientRequestContext1 );

    }

    private Object getFieldValue( Object obj, String fieldName )
                    throws java.lang.reflect.InvocationTargetException, SecurityException, IllegalArgumentException,
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
