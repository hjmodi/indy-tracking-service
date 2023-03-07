package org.commonjava.indy.service.tracking.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TrackingKeyTest
{
    @Test
    public void test_equals_0() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        TrackedContent trackedContent1 = new TrackedContent();
        boolean boolean2 = trackingKey0.equals( trackedContent1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_1() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        StoreKey storeKey2 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey2.getName() );
        assertEquals( "maven", storeKey2.getPackageType() );
        StoreType storeType3 = storeKey2.getType();
        StoreKey storeKey6 = new StoreKey( "", storeType3, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey6.getName() );
        assertEquals( "", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        StoreKey storeKey10 = new StoreKey( ":remote:hi!", storeType7, "" );
        assertEquals( "", storeKey10.getName() );
        assertEquals( ":remote:hi!", storeKey10.getPackageType() );
        boolean boolean11 = trackingKey0.equals( storeKey10 );
        assertFalse( boolean11 );

    }

    @Test
    public void test_equals_2() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        StoreKey storeKey2 = StoreKey.fromString( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", storeKey2.getName() );
        assertEquals( "maven", storeKey2.getPackageType() );
        TrackingKey trackingKey4 =
                        new TrackingKey( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", trackingKey4.getId() );
        AccessChannel accessChannel5 = AccessChannel.GENERIC_PROXY;
        StoreEffect storeEffect6 = StoreEffect.DOWNLOAD;
        org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO contentTransferDTO9 =
                        new org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO( storeKey2, trackingKey4,
                                                                                               accessChannel5,
                                                                                               "/r{v0l5M#n}5r",
                                                                                               "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                                                                                               storeEffect6 );
        assertEquals( "/r{v0l5M#n}5r", contentTransferDTO9.getPath() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                      contentTransferDTO9.getOriginUrl() );
        boolean boolean10 = trackingKey0.equals( contentTransferDTO9 );
        assertFalse( boolean10 );

    }

    @Test
    public void test_equals_3() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord dtxTrackingRecord1 =
                        new org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord();
        boolean boolean2 = trackingKey0.equals( dtxTrackingRecord1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_4() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        Object obj1 = new Object();
        Class<?> wildcardClass2 = obj1.getClass();
        assertEquals( "java.lang.Object", wildcardClass2.getName() );
        assertEquals( "java.lang", wildcardClass2.getPackageName() );
        io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object> objObjectMapperDeserializer3 =
                        new io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object>(
                                        (Class<Object>) wildcardClass2 );
        Class<?> wildcardClass4 = objObjectMapperDeserializer3.getClass();
        assertEquals( "io.quarkus.kafka.client.serialization.ObjectMapperDeserializer", wildcardClass4.getName() );
        assertEquals( "io.quarkus.kafka.client.serialization", wildcardClass4.getPackageName() );
        com.fasterxml.jackson.databind.ObjectMapper objectMapper5 = null;
        io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object> objObjectMapperDeserializer6 =
                        new io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object>(
                                        (Class<Object>) wildcardClass4, objectMapper5 );
        Class<?> wildcardClass7 = objObjectMapperDeserializer6.getClass();
        assertEquals( "io.quarkus.kafka.client.serialization.ObjectMapperDeserializer", wildcardClass7.getName() );
        assertEquals( "io.quarkus.kafka.client.serialization", wildcardClass7.getPackageName() );
        com.fasterxml.jackson.databind.ObjectMapper objectMapper8 = null;
        io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object> objObjectMapperDeserializer9 =
                        new io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object>(
                                        (Class<Object>) wildcardClass7, objectMapper8 );
        boolean boolean10 = trackingKey0.equals( objObjectMapperDeserializer9 );
        assertFalse( boolean10 );

    }

    @Test
    public void test_equals_5() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        org.commonjava.indy.service.tracking.model.dto.TrackedContentDTO trackedContentDTO1 =
                        new org.commonjava.indy.service.tracking.model.dto.TrackedContentDTO();
        boolean boolean2 = trackingKey0.equals( trackedContentDTO1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_6() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        org.commonjava.indy.service.tracking.model.dto.TrackingIdsDTO trackingIdsDTO1 =
                        new org.commonjava.indy.service.tracking.model.dto.TrackingIdsDTO();
        boolean boolean2 = trackingKey0.equals( trackingIdsDTO1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_7() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy configurableRetryPolicy3 =
                        new org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy( 10, 1 );
        boolean boolean4 = trackingKey0.equals( configurableRetryPolicy3 );
        assertFalse( boolean4 );

    }

    @Test
    public void test_equals_8() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        java.util.concurrent.atomic.AtomicReference<Object> objAtomicReference1 =
                        new java.util.concurrent.atomic.AtomicReference<Object>();
        boolean boolean2 = trackingKey0.equals( objAtomicReference1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_9() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        AccessChannel accessChannel1 = AccessChannel.GENERIC_PROXY;
        StoreType storeType2 = StoreType.hosted;
        StoreKey storeKey5 = new StoreKey( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                           storeType2, "mpv" );
        assertEquals( "mpv", storeKey5.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey5.getPackageType() );
        org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO trackedContentEntryDTO7 =
                        new org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO( storeKey5,
                                                                                                   accessChannel1,
                                                                                                   "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO7.getMd5() );
        assertNull( trackedContentEntryDTO7.getSize() );
        assertNull( trackedContentEntryDTO7.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO7.getPath() );
        assertNull( trackedContentEntryDTO7.getOriginUrl() );
        assertNull( trackedContentEntryDTO7.getSha1() );
        assertNull( trackedContentEntryDTO7.getLocalUrl() );
        boolean boolean8 = trackingKey0.equals( trackedContentEntryDTO7 );
        assertFalse( boolean8 );

    }

    @Test
    public void test_equals_10() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        boolean boolean1 = trackingKey0.equals( trackingKey0 );
        assertTrue( boolean1 );

    }

    @Test
    public void test_equals_11() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper1 = null;
        com.fasterxml.jackson.databind.ObjectMapper objectMapper2 = null;
        com.fasterxml.jackson.databind.ObjectMapper objectMapper3 = null;
        com.fasterxml.jackson.databind.ObjectMapper objectMapper4 = null;
        Object obj5 = new Object();
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager6 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput7 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper4, obj5,
                                                                                           traceManager6 );
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager8 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput9 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper3,
                                                                                           dTOStreamingOutput7,
                                                                                           traceManager8 );
        Class<?> wildcardClass10 = dTOStreamingOutput9.getClass();
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput", wildcardClass10.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs", wildcardClass10.getPackageName() );
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager11 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput12 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper2,
                                                                                           wildcardClass10,
                                                                                           traceManager11 );
        Class<?> wildcardClass13 = dTOStreamingOutput12.getClass();
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput", wildcardClass13.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs", wildcardClass13.getPackageName() );
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager14 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput15 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper1,
                                                                                           wildcardClass13,
                                                                                           traceManager14 );
        boolean boolean16 = trackingKey0.equals( dTOStreamingOutput15 );
        assertFalse( boolean16 );

    }

    @Test
    public void test_equals_12() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        com.datastax.driver.core.SocketOptions socketOptions1 = new com.datastax.driver.core.SocketOptions();
        boolean boolean2 = trackingKey0.equals( socketOptions1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_13() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        TrackingKey trackingKey2 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey2.getId() );
        boolean boolean3 = trackingKey0.equals( trackingKey2 );
        assertFalse( boolean3 );

    }

    @Test
    public void test_equals_14() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        java.util.concurrent.atomic.AtomicBoolean atomicBoolean1 = new java.util.concurrent.atomic.AtomicBoolean();
        boolean boolean2 = trackingKey0.equals( atomicBoolean1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_15() throws Throwable
    {
        TrackingKey trackingKey0 = new TrackingKey();
        TrackedContentEntry trackedContentEntry1 = new TrackedContentEntry();
        boolean boolean2 = trackingKey0.equals( trackedContentEntry1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_writeExternal_0() throws Throwable
    {
        TrackingKey trackingKey1 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey1.getId() );
        java.io.OutputStream outputStream2 = java.io.OutputStream.nullOutputStream();
        ObjectOutputStream objectOutputStream3 = new ObjectOutputStream( outputStream2 );
        assertEquals( (byte) 113, getFieldValue( objectOutputStream3, "TC_REFERENCE" ) );
        assertEquals( (byte) 119, getFieldValue( objectOutputStream3, "TC_BLOCKDATA" ) );
        assertEquals( (short) 5, getFieldValue( objectOutputStream3, "STREAM_VERSION" ) );
        assertEquals( (byte) 16, getFieldValue( objectOutputStream3, "SC_ENUM" ) );
        assertEquals( (byte) 126, getFieldValue( objectOutputStream3, "TC_MAX" ) );
        assertEquals( (byte) 122, getFieldValue( objectOutputStream3, "TC_BLOCKDATALONG" ) );
        assertEquals( (byte) 125, getFieldValue( objectOutputStream3, "TC_PROXYCLASSDESC" ) );
        assertEquals( (byte) 117, getFieldValue( objectOutputStream3, "TC_ARRAY" ) );
        assertEquals( (byte) 123, getFieldValue( objectOutputStream3, "TC_EXCEPTION" ) );
        assertEquals( (byte) 4, getFieldValue( objectOutputStream3, "SC_EXTERNALIZABLE" ) );
        assertEquals( (byte) 120, getFieldValue( objectOutputStream3, "TC_ENDBLOCKDATA" ) );
        assertEquals( (byte) 112, getFieldValue( objectOutputStream3, "TC_BASE" ) );
        assertEquals( (byte) 116, getFieldValue( objectOutputStream3, "TC_STRING" ) );
        assertEquals( (byte) 118, getFieldValue( objectOutputStream3, "TC_CLASS" ) );
        assertEquals( 8257536, getFieldValue( objectOutputStream3, "baseWireHandle" ) );
        assertEquals( (byte) 8, getFieldValue( objectOutputStream3, "SC_BLOCK_DATA" ) );
        assertEquals( (byte) 112, getFieldValue( objectOutputStream3, "TC_NULL" ) );
        assertEquals( (byte) 114, getFieldValue( objectOutputStream3, "TC_CLASSDESC" ) );
        assertEquals( (byte) 115, getFieldValue( objectOutputStream3, "TC_OBJECT" ) );
        assertEquals( (byte) 126, getFieldValue( objectOutputStream3, "TC_ENUM" ) );
        assertEquals( 2, getFieldValue( objectOutputStream3, "PROTOCOL_VERSION_2" ) );
        assertEquals( 1, getFieldValue( objectOutputStream3, "PROTOCOL_VERSION_1" ) );
        assertEquals( (short) -21267, getFieldValue( objectOutputStream3, "STREAM_MAGIC" ) );
        assertEquals( (byte) 2, getFieldValue( objectOutputStream3, "SC_SERIALIZABLE" ) );
        assertEquals( (byte) 124, getFieldValue( objectOutputStream3, "TC_LONGSTRING" ) );
        assertEquals( (byte) 121, getFieldValue( objectOutputStream3, "TC_RESET" ) );
        assertEquals( (byte) 1, getFieldValue( objectOutputStream3, "SC_WRITE_METHOD" ) );
        trackingKey1.writeExternal( objectOutputStream3 );
        assertEquals( "hi!", trackingKey1.getId() );

    }

    @Test
    public void test_TrackingKey_0() throws Throwable
    {
        TrackingKey trackingKey1 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey1.getId() );

    }

    @Test
    public void test_setId_0() throws Throwable
    {
        TrackingKey trackingKey1 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey1.getId() );
        trackingKey1.setId( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}" );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackingKey1.getId() );

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
