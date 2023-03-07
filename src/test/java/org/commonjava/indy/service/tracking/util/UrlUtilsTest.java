package org.commonjava.indy.service.tracking.util;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class UrlUtilsTest
{
    @Test
    public void test_uriDecode_0() throws Throwable
    {
        Charset charset0 = null;
        String str2 = UrlUtils.uriDecode( "hi!", charset0 );
        assertEquals( "hi!", str2 );

    }

    @Test
    public void test_uriDecode_1() throws Throwable
    {
        String str1 = UrlUtils.uriDecode( "" );
        assertEquals( "", str1 );

    }

    private Object getFieldValue( Object obj, String fieldName )
                    throws java.lang.reflect.InvocationTargetException, SecurityException, IllegalArgumentException,
                    IllegalAccessException
    {
        try
        {
            java.lang.reflect.Field field = obj.getClass().getField( fieldName );
            return field.get( obj );
        }
        catch ( NoSuchFieldException e )
        {
            for ( java.lang.reflect.Method publicMethod : obj.getClass().getMethods() )
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
