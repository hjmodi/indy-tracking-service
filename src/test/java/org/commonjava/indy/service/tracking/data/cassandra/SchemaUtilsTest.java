package org.commonjava.indy.service.tracking.data.cassandra;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class SchemaUtilsTest
{
    @Test
    public void test_getSchemaCreateKeyspace_0() throws Throwable
    {
        String str2 = SchemaUtils.getSchemaCreateKeyspace(
                        "CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS hi! WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':-1}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':0}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':-1}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':10}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':35}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':1};",
                        100 );
        assertEquals( "CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS CREATE KEYSPACE IF NOT EXISTS hi! WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':-1}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':0}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':-1}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':10}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':35}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':1}; WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':100};",
                      str2 );

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
