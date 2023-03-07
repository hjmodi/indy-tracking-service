package org.commonjava.indy.service.tracking.util;

import io.quarkus.test.junit.QuarkusTest;
import org.commonjava.indy.service.tracking.model.TrackedContent;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

@QuarkusTest
public class TrackingUtilsTest
{
    @Test
    public void test_toInputStream_0() throws Throwable
    {
        TrackedContent trackedContent0 = null;
        InputStream inputStream1 = TrackingUtils.toInputStream( trackedContent0 );

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
