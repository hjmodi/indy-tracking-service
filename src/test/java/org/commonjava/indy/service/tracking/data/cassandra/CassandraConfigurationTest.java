package org.commonjava.indy.service.tracking.data.cassandra;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class CassandraConfigurationTest
{
    @Test
    public void test_setReadTimeoutMillis_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setReadTimeoutMillis( 0 );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setEnabled_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        Boolean boolean1 = null;
        cassandraConfiguration0.setEnabled( boolean1 );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setConnectTimeoutMillis_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setConnectTimeoutMillis( 1098 );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 1098, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setReadRetries_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setReadRetries( 100 );
        assertEquals( 100, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setCassandraPort_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setReadTimeoutMillis( 0 );
        cassandraConfiguration0.setCassandraPort( 97 );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 97, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setCassandraPass_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setCassandraPass( "|T" );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( "|T", cassandraConfiguration0.getCassandraPass() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setKeyspace_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setKeyspace(
                        "org.commonjava.indy.service.tracking.data.cassandra.CassandraConfiguration" );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertEquals( "org.commonjava.indy.service.tracking.data.cassandra.CassandraConfiguration",
                      cassandraConfiguration0.getKeyspace() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setCassandraHost_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setCassandraHost( "R;D#t)g\"G*!" );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertEquals( "R;D#t)g\"G*!", cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setCassandraUser_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setCassandraUser( "" );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( "", cassandraConfiguration0.getCassandraUser() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setKeyspaceReplicas_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setKeyspaceReplicas( ( -1 ) );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( -1, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( 0, cassandraConfiguration0.getWriteRetries() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

    }

    @Test
    public void test_setWriteRetries_0() throws Throwable
    {
        CassandraConfiguration cassandraConfiguration0 = new CassandraConfiguration();
        cassandraConfiguration0.setWriteRetries( ( -1 ) );
        assertEquals( 0, cassandraConfiguration0.getReadRetries() );
        assertEquals( 0, cassandraConfiguration0.getConnectTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getReadTimeoutMillis() );
        assertEquals( 0, cassandraConfiguration0.getKeyspaceReplicas() );
        assertEquals( 0, cassandraConfiguration0.getCassandraPort().intValue() );
        assertEquals( -1, cassandraConfiguration0.getWriteRetries() );
        assertNull( cassandraConfiguration0.getCassandraHost() );

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
