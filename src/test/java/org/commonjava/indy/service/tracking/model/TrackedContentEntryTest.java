package org.commonjava.indy.service.tracking.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TrackedContentEntryTest
{
    @Test
    public void test_setStoreKey_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
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
        trackedContentEntry0.setStoreKey( storeKey10 );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_setMd5_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        trackedContentEntry0.setMd5( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}" );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_setSha1_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        trackedContentEntry0.setSha1( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}" );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_setEffect_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        StoreEffect storeEffect1 = StoreEffect.UPLOAD;
        trackedContentEntry0.setEffect( storeEffect1 );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_setTrackingKey_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        TrackingKey trackingKey2 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey2.getId() );
        trackedContentEntry0.setTrackingKey( trackingKey2 );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_setSize_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        trackedContentEntry0.setSize( ( -1L ) );
        assertNull( trackedContentEntry0.getMd5() );
        assertEquals( -1L, trackedContentEntry0.getSize().longValue() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_TrackedContentEntry_0() throws Throwable
    {
        TrackingKey trackingKey1 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey1.getId() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        StoreType storeType4 = storeKey3.getType();
        StoreKey storeKey7 = new StoreKey( "", storeType4, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey7.getName() );
        assertEquals( "", storeKey7.getPackageType() );
        StoreType storeType8 = storeKey7.getType();
        StoreKey storeKey11 = new StoreKey( ":remote:hi!", storeType8, "" );
        assertEquals( "", storeKey11.getName() );
        assertEquals( ":remote:hi!", storeKey11.getPackageType() );
        AccessChannel accessChannel12 = AccessChannel.GENERIC_PROXY;
        StoreEffect storeEffect15 = StoreEffect.UPLOAD;
        TrackedContentEntry trackedContentEntry20 = new TrackedContentEntry( trackingKey1, storeKey11, accessChannel12,
                                                                             "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                                                                             "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                                                                             storeEffect15, ( -1L ),
                                                                             "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                                                                             "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                                                                             "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}" );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry20.getMd5() );
        assertEquals( -1L, trackedContentEntry20.getSize().longValue() );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry20.getSha256() );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry20.getPath() );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry20.getOriginUrl() );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry20.getSha1() );

    }

    @Test
    public void test_setTimestamps_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        java.util.HashSet<Long> longSet1 = new java.util.HashSet<Long>();
        boolean boolean3 = longSet1.add( ( -1L ) );
        assertTrue( boolean3 );
        trackedContentEntry0.setTimestamps( longSet1 );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_equals_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        TrackedContent trackedContent1 = new TrackedContent();
        boolean boolean2 = trackedContentEntry0.equals( trackedContent1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_1() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
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
        boolean boolean11 = trackedContentEntry0.equals( storeKey10 );
        assertFalse( boolean11 );

    }

    @Test
    public void test_equals_2() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
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
        boolean boolean10 = trackedContentEntry0.equals( contentTransferDTO9 );
        assertFalse( boolean10 );

    }

    @Test
    public void test_equals_3() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord dtxTrackingRecord1 =
                        new org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord();
        boolean boolean2 = trackedContentEntry0.equals( dtxTrackingRecord1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_4() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
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
        boolean boolean10 = trackedContentEntry0.equals( objObjectMapperDeserializer9 );
        assertFalse( boolean10 );

    }

    @Test
    public void test_equals_5() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        org.commonjava.indy.service.tracking.model.dto.TrackedContentDTO trackedContentDTO1 =
                        new org.commonjava.indy.service.tracking.model.dto.TrackedContentDTO();
        boolean boolean2 = trackedContentEntry0.equals( trackedContentDTO1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_6() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        org.commonjava.indy.service.tracking.model.dto.TrackingIdsDTO trackingIdsDTO1 =
                        new org.commonjava.indy.service.tracking.model.dto.TrackingIdsDTO();
        boolean boolean2 = trackedContentEntry0.equals( trackingIdsDTO1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_7() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy configurableRetryPolicy3 =
                        new org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy( 10, 1 );
        boolean boolean4 = trackedContentEntry0.equals( configurableRetryPolicy3 );
        assertFalse( boolean4 );

    }

    @Test
    public void test_equals_8() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        java.util.concurrent.atomic.AtomicReference<Object> objAtomicReference1 =
                        new java.util.concurrent.atomic.AtomicReference<Object>();
        boolean boolean2 = trackedContentEntry0.equals( objAtomicReference1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_9() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
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
        boolean boolean8 = trackedContentEntry0.equals( trackedContentEntryDTO7 );
        assertFalse( boolean8 );

    }

    @Test
    public void test_equals_10() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        Object obj1 = new Object();
        boolean boolean2 = trackedContentEntry0.equals( obj1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_11() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
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
        boolean boolean16 = trackedContentEntry0.equals( dTOStreamingOutput15 );
        assertFalse( boolean16 );

    }

    @Test
    public void test_equals_12() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        com.datastax.driver.core.SocketOptions socketOptions1 = new com.datastax.driver.core.SocketOptions();
        boolean boolean2 = trackedContentEntry0.equals( socketOptions1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_13() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        TrackingKey trackingKey2 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey2.getId() );
        boolean boolean3 = trackedContentEntry0.equals( trackingKey2 );
        assertFalse( boolean3 );

    }

    @Test
    public void test_equals_14() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        java.util.concurrent.atomic.AtomicBoolean atomicBoolean1 = new java.util.concurrent.atomic.AtomicBoolean();
        boolean boolean2 = trackedContentEntry0.equals( atomicBoolean1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_15() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        TrackedContentEntry trackedContentEntry1 = new TrackedContentEntry();
        boolean boolean2 = trackedContentEntry0.equals( trackedContentEntry1 );
        assertTrue( boolean2 );

    }

    @Test
    public void test_setPath_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        trackedContentEntry0.setPath( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}" );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertNull( trackedContentEntry0.getSha256() );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_setIndex_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        trackedContentEntry0.setIndex( ( -1L ) );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertEquals( -1L, trackedContentEntry0.getIndex() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_setOriginUrl_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        trackedContentEntry0.setOriginUrl(
                        "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}" );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_setSha256_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        trackedContentEntry0.setSha256( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}" );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertEquals( "IndyEvent{eventID=b020bdc3-8ade-4e45-b284-02c51d700ca2, eventMetadata=null}",
                      trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

    }

    @Test
    public void test_setAccessChannel_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = new TrackedContentEntry();
        AccessChannel accessChannel1 = AccessChannel.GENERIC_PROXY;
        trackedContentEntry0.setAccessChannel( accessChannel1 );
        assertNull( trackedContentEntry0.getMd5() );
        assertNull( trackedContentEntry0.getSize() );
        assertNull( trackedContentEntry0.getSha256() );
        assertNull( trackedContentEntry0.getPath() );
        assertNull( trackedContentEntry0.getOriginUrl() );
        assertNull( trackedContentEntry0.getSha1() );

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
