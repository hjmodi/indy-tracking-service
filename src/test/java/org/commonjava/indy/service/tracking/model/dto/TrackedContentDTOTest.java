package org.commonjava.indy.service.tracking.model.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.commonjava.indy.service.tracking.model.TrackingKey;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TrackedContentDTOTest
{
    @Test
    public void test_setDownloads_0() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        HashSet<TrackedContentEntryDTO> trackedContentEntryDTOSet1 = new HashSet<TrackedContentEntryDTO>();
        org.commonjava.indy.service.tracking.model.AccessChannel accessChannel2 =
                        org.commonjava.indy.service.tracking.model.AccessChannel.GENERIC_PROXY;
        org.commonjava.indy.service.tracking.model.StoreType storeType3 =
                        org.commonjava.indy.service.tracking.model.StoreType.hosted;
        org.commonjava.indy.service.tracking.model.StoreKey storeKey6 =
                        new org.commonjava.indy.service.tracking.model.StoreKey(
                                        "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                        storeType3, "mpv" );
        assertEquals( "mpv", storeKey6.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey6.getPackageType() );
        TrackedContentEntryDTO trackedContentEntryDTO8 = new TrackedContentEntryDTO( storeKey6, accessChannel2,
                                                                                     "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO8.getMd5() );
        assertNull( trackedContentEntryDTO8.getSize() );
        assertNull( trackedContentEntryDTO8.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO8.getPath() );
        assertNull( trackedContentEntryDTO8.getOriginUrl() );
        assertNull( trackedContentEntryDTO8.getSha1() );
        assertNull( trackedContentEntryDTO8.getLocalUrl() );
        boolean boolean9 = trackedContentEntryDTOSet1.add( trackedContentEntryDTO8 );
        assertTrue( boolean9 );
        trackedContentDTO0.setDownloads( trackedContentEntryDTOSet1 );

    }

    @Test
    public void test_setUploads_0() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        HashSet<TrackedContentEntryDTO> trackedContentEntryDTOSet1 = new HashSet<TrackedContentEntryDTO>();
        org.commonjava.indy.service.tracking.model.AccessChannel accessChannel2 =
                        org.commonjava.indy.service.tracking.model.AccessChannel.GENERIC_PROXY;
        org.commonjava.indy.service.tracking.model.StoreType storeType3 =
                        org.commonjava.indy.service.tracking.model.StoreType.hosted;
        org.commonjava.indy.service.tracking.model.StoreKey storeKey6 =
                        new org.commonjava.indy.service.tracking.model.StoreKey(
                                        "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                        storeType3, "mpv" );
        assertEquals( "mpv", storeKey6.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey6.getPackageType() );
        TrackedContentEntryDTO trackedContentEntryDTO8 = new TrackedContentEntryDTO( storeKey6, accessChannel2,
                                                                                     "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO8.getMd5() );
        assertNull( trackedContentEntryDTO8.getSize() );
        assertNull( trackedContentEntryDTO8.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO8.getPath() );
        assertNull( trackedContentEntryDTO8.getOriginUrl() );
        assertNull( trackedContentEntryDTO8.getSha1() );
        assertNull( trackedContentEntryDTO8.getLocalUrl() );
        boolean boolean9 = trackedContentEntryDTOSet1.add( trackedContentEntryDTO8 );
        assertTrue( boolean9 );
        trackedContentDTO0.setUploads( trackedContentEntryDTOSet1 );

    }

    @Test
    public void test_equals_0() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        org.commonjava.indy.service.tracking.model.TrackedContent trackedContent1 =
                        new org.commonjava.indy.service.tracking.model.TrackedContent();
        boolean boolean2 = trackedContentDTO0.equals( trackedContent1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_1() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        org.commonjava.indy.service.tracking.model.StoreKey storeKey2 =
                        org.commonjava.indy.service.tracking.model.StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey2.getName() );
        assertEquals( "maven", storeKey2.getPackageType() );
        org.commonjava.indy.service.tracking.model.StoreType storeType3 = storeKey2.getType();
        org.commonjava.indy.service.tracking.model.StoreKey storeKey6 =
                        new org.commonjava.indy.service.tracking.model.StoreKey( "", storeType3, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey6.getName() );
        assertEquals( "", storeKey6.getPackageType() );
        org.commonjava.indy.service.tracking.model.StoreType storeType7 = storeKey6.getType();
        org.commonjava.indy.service.tracking.model.StoreKey storeKey10 =
                        new org.commonjava.indy.service.tracking.model.StoreKey( ":remote:hi!", storeType7, "" );
        assertEquals( "", storeKey10.getName() );
        assertEquals( ":remote:hi!", storeKey10.getPackageType() );
        boolean boolean11 = trackedContentDTO0.equals( storeKey10 );
        assertFalse( boolean11 );

    }

    @Test
    public void test_equals_2() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        org.commonjava.indy.service.tracking.model.StoreKey storeKey2 =
                        org.commonjava.indy.service.tracking.model.StoreKey.fromString(
                                        "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", storeKey2.getName() );
        assertEquals( "maven", storeKey2.getPackageType() );
        TrackingKey trackingKey4 =
                        new TrackingKey( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", trackingKey4.getId() );
        org.commonjava.indy.service.tracking.model.AccessChannel accessChannel5 =
                        org.commonjava.indy.service.tracking.model.AccessChannel.GENERIC_PROXY;
        org.commonjava.indy.service.tracking.model.StoreEffect storeEffect6 =
                        org.commonjava.indy.service.tracking.model.StoreEffect.DOWNLOAD;
        ContentTransferDTO contentTransferDTO9 =
                        new ContentTransferDTO( storeKey2, trackingKey4, accessChannel5, "/r{v0l5M#n}5r",
                                                "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                                                storeEffect6 );
        assertEquals( "/r{v0l5M#n}5r", contentTransferDTO9.getPath() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                      contentTransferDTO9.getOriginUrl() );
        boolean boolean10 = trackedContentDTO0.equals( contentTransferDTO9 );
        assertFalse( boolean10 );

    }

    @Test
    public void test_equals_3() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord dtxTrackingRecord1 =
                        new org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord();
        boolean boolean2 = trackedContentDTO0.equals( dtxTrackingRecord1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_4() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
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
        boolean boolean10 = trackedContentDTO0.equals( objObjectMapperDeserializer9 );
        assertFalse( boolean10 );

    }

    @Test
    public void test_equals_5() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        TrackedContentDTO trackedContentDTO1 = new TrackedContentDTO();
        boolean boolean2 = trackedContentDTO0.equals( trackedContentDTO1 );
        assertTrue( boolean2 );

    }

    @Test
    public void test_equals_6() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        TrackingIdsDTO trackingIdsDTO1 = new TrackingIdsDTO();
        boolean boolean2 = trackedContentDTO0.equals( trackingIdsDTO1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_7() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy configurableRetryPolicy3 =
                        new org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy( 10, 1 );
        boolean boolean4 = trackedContentDTO0.equals( configurableRetryPolicy3 );
        assertFalse( boolean4 );

    }

    @Test
    public void test_equals_8() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        java.util.concurrent.atomic.AtomicReference<Object> objAtomicReference1 =
                        new java.util.concurrent.atomic.AtomicReference<Object>();
        boolean boolean2 = trackedContentDTO0.equals( objAtomicReference1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_9() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        org.commonjava.indy.service.tracking.model.AccessChannel accessChannel1 =
                        org.commonjava.indy.service.tracking.model.AccessChannel.GENERIC_PROXY;
        org.commonjava.indy.service.tracking.model.StoreType storeType2 =
                        org.commonjava.indy.service.tracking.model.StoreType.hosted;
        org.commonjava.indy.service.tracking.model.StoreKey storeKey5 =
                        new org.commonjava.indy.service.tracking.model.StoreKey(
                                        "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                        storeType2, "mpv" );
        assertEquals( "mpv", storeKey5.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey5.getPackageType() );
        TrackedContentEntryDTO trackedContentEntryDTO7 = new TrackedContentEntryDTO( storeKey5, accessChannel1,
                                                                                     "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO7.getMd5() );
        assertNull( trackedContentEntryDTO7.getSize() );
        assertNull( trackedContentEntryDTO7.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO7.getPath() );
        assertNull( trackedContentEntryDTO7.getOriginUrl() );
        assertNull( trackedContentEntryDTO7.getSha1() );
        assertNull( trackedContentEntryDTO7.getLocalUrl() );
        boolean boolean8 = trackedContentDTO0.equals( trackedContentEntryDTO7 );
        assertFalse( boolean8 );

    }

    @Test
    public void test_equals_10() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        boolean boolean1 = trackedContentDTO0.equals( trackedContentDTO0 );
        assertTrue( boolean1 );

    }

    @Test
    public void test_equals_11() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
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
        boolean boolean16 = trackedContentDTO0.equals( dTOStreamingOutput15 );
        assertFalse( boolean16 );

    }

    @Test
    public void test_equals_12() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        com.datastax.driver.core.SocketOptions socketOptions1 = new com.datastax.driver.core.SocketOptions();
        boolean boolean2 = trackedContentDTO0.equals( socketOptions1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_13() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        TrackingKey trackingKey2 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey2.getId() );
        boolean boolean3 = trackedContentDTO0.equals( trackingKey2 );
        assertFalse( boolean3 );

    }

    @Test
    public void test_equals_14() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        java.util.concurrent.atomic.AtomicBoolean atomicBoolean1 = new java.util.concurrent.atomic.AtomicBoolean();
        boolean boolean2 = trackedContentDTO0.equals( atomicBoolean1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_15() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        org.commonjava.indy.service.tracking.model.TrackedContentEntry trackedContentEntry1 =
                        new org.commonjava.indy.service.tracking.model.TrackedContentEntry();
        boolean boolean2 = trackedContentDTO0.equals( trackedContentEntry1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_setKey_0() throws Throwable
    {
        TrackedContentDTO trackedContentDTO0 = new TrackedContentDTO();
        boolean boolean2 = trackedContentDTO0.equals( -1 );
        assertFalse( boolean2 );
        TrackingKey trackingKey3 = null;
        trackedContentDTO0.setKey( trackingKey3 );

    }

    @Test
    public void test_TrackedContentDTO_0() throws Throwable
    {
        TrackingKey trackingKey1 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey1.getId() );
        HashSet<TrackedContentEntryDTO> trackedContentEntryDTOSet2 = new HashSet<TrackedContentEntryDTO>();
        org.commonjava.indy.service.tracking.model.AccessChannel accessChannel3 =
                        org.commonjava.indy.service.tracking.model.AccessChannel.GENERIC_PROXY;
        org.commonjava.indy.service.tracking.model.StoreType storeType4 =
                        org.commonjava.indy.service.tracking.model.StoreType.hosted;
        org.commonjava.indy.service.tracking.model.StoreKey storeKey7 =
                        new org.commonjava.indy.service.tracking.model.StoreKey(
                                        "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                        storeType4, "mpv" );
        assertEquals( "mpv", storeKey7.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey7.getPackageType() );
        TrackedContentEntryDTO trackedContentEntryDTO9 = new TrackedContentEntryDTO( storeKey7, accessChannel3,
                                                                                     "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO9.getMd5() );
        assertNull( trackedContentEntryDTO9.getSize() );
        assertNull( trackedContentEntryDTO9.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO9.getPath() );
        assertNull( trackedContentEntryDTO9.getOriginUrl() );
        assertNull( trackedContentEntryDTO9.getSha1() );
        assertNull( trackedContentEntryDTO9.getLocalUrl() );
        boolean boolean10 = trackedContentEntryDTOSet2.add( trackedContentEntryDTO9 );
        assertTrue( boolean10 );
        HashSet<TrackedContentEntryDTO> trackedContentEntryDTOSet11 = new HashSet<TrackedContentEntryDTO>();
        org.commonjava.indy.service.tracking.model.AccessChannel accessChannel12 =
                        org.commonjava.indy.service.tracking.model.AccessChannel.GENERIC_PROXY;
        org.commonjava.indy.service.tracking.model.StoreType storeType13 =
                        org.commonjava.indy.service.tracking.model.StoreType.hosted;
        org.commonjava.indy.service.tracking.model.StoreKey storeKey16 =
                        new org.commonjava.indy.service.tracking.model.StoreKey(
                                        "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                        storeType13, "mpv" );
        assertEquals( "mpv", storeKey16.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey16.getPackageType() );
        TrackedContentEntryDTO trackedContentEntryDTO18 = new TrackedContentEntryDTO( storeKey16, accessChannel12,
                                                                                      "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO18.getMd5() );
        assertNull( trackedContentEntryDTO18.getSize() );
        assertNull( trackedContentEntryDTO18.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO18.getPath() );
        assertNull( trackedContentEntryDTO18.getOriginUrl() );
        assertNull( trackedContentEntryDTO18.getSha1() );
        assertNull( trackedContentEntryDTO18.getLocalUrl() );
        boolean boolean19 = trackedContentEntryDTOSet11.add( trackedContentEntryDTO18 );
        assertTrue( boolean19 );
        TrackedContentDTO trackedContentDTO20 =
                        new TrackedContentDTO( trackingKey1, trackedContentEntryDTOSet2, trackedContentEntryDTOSet11 );

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
