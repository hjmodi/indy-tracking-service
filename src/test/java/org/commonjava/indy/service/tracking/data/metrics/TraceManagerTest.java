package org.commonjava.indy.service.tracking.data.metrics;

import io.opentelemetry.api.trace.Tracer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TraceManagerTest
{
    @Test
    public void test_TraceManager_0() throws Throwable
    {
        Tracer tracer0 = null;
        TraceManager traceManager1 = new TraceManager( tracer0 );
        assertEquals( "indy.ispn", getFieldValue( traceManager1, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager1, "DEFAULT" ) );

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
