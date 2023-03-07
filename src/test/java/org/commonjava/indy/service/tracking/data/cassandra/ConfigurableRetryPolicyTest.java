package org.commonjava.indy.service.tracking.data.cassandra;

import com.datastax.driver.core.Cluster;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@QuarkusTest
public class ConfigurableRetryPolicyTest
{
    @Test
    public void test_ConfigurableRetryPolicy_0() throws Throwable
    {
        ConfigurableRetryPolicy configurableRetryPolicy2 = new ConfigurableRetryPolicy( ( -1 ), 1 );

    }

    @Test
    public void test_init_0() throws Throwable
    {
        ConfigurableRetryPolicy configurableRetryPolicy2 = new ConfigurableRetryPolicy( 0, 1 );
        Cluster cluster3 = null;
        configurableRetryPolicy2.init( cluster3 );

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
