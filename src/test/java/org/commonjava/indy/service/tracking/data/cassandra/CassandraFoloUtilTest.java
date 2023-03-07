package org.commonjava.indy.service.tracking.data.cassandra;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CassandraFoloUtilTest
{
    @Test
    public void test_getSchemaCreateTableFolo_0() throws Throwable
    {
        String str1 = CassandraFoloUtil.getSchemaCreateTableFolo(
                        "CREATE TABLE IF NOT EXISTS hi!.records2 (tracking_key text,sealed boolean,store_key text,access_channel text,path text,origin_url text,local_url text,store_effect text,md5 text,sha256 text,sha1 text,size bigint,started bigint,timestamps set<bigint>,PRIMARY KEY ((tracking_key),store_key,path,store_effect));" );
        assertEquals( "CREATE TABLE IF NOT EXISTS CREATE TABLE IF NOT EXISTS hi!.records2 (tracking_key text,sealed boolean,store_key text,access_channel text,path text,origin_url text,local_url text,store_effect text,md5 text,sha256 text,sha1 text,size bigint,started bigint,timestamps set<bigint>,PRIMARY KEY ((tracking_key),store_key,path,store_effect));.records2 (tracking_key text,sealed boolean,store_key text,access_channel text,path text,origin_url text,local_url text,store_effect text,md5 text,sha256 text,sha1 text,size bigint,started bigint,timestamps set<bigint>,PRIMARY KEY ((tracking_key),store_key,path,store_effect));",
                      str1 );

    }

    @Test
    public void test_getSchemaCreateTableFoloLegacy_0() throws Throwable
    {
        String str1 = CassandraFoloUtil.getSchemaCreateTableFoloLegacy(
                        "CREATE TABLE IF NOT EXISTS CREATE TABLE IF NOT EXISTS records.records (tracking_key text,sealed boolean,store_key text,access_channel text,path text,origin_url text,local_url text,store_effect text,md5 text,sha256 text,sha1 text,size bigint,started bigint,timestamps set<bigint>,PRIMARY KEY ((tracking_key),store_key,path,store_effect));.records2 (tracking_key text,sealed boolean,store_key text,access_channel text,path text,origin_url text,local_url text,store_effect text,md5 text,sha256 text,sha1 text,size bigint,started bigint,timestamps set<bigint>,PRIMARY KEY ((tracking_key),store_key,path,store_effect));" );
        assertEquals( "CREATE TABLE IF NOT EXISTS CREATE TABLE IF NOT EXISTS CREATE TABLE IF NOT EXISTS records.records (tracking_key text,sealed boolean,store_key text,access_channel text,path text,origin_url text,local_url text,store_effect text,md5 text,sha256 text,sha1 text,size bigint,started bigint,timestamps set<bigint>,PRIMARY KEY ((tracking_key),store_key,path,store_effect));.records2 (tracking_key text,sealed boolean,store_key text,access_channel text,path text,origin_url text,local_url text,store_effect text,md5 text,sha256 text,sha1 text,size bigint,started bigint,timestamps set<bigint>,PRIMARY KEY ((tracking_key),store_key,path,store_effect));.records (tracking_key text,sealed boolean,store_key text,access_channel text,path text,origin_url text,local_url text,store_effect text,md5 text,sha256 text,sha1 text,size bigint,started bigint,timestamps set<bigint>,PRIMARY KEY ((tracking_key),store_key,path,store_effect));",
                      str1 );

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
