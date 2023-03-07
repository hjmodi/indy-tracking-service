package org.commonjava.indy.service.tracking.model.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.commonjava.indy.service.tracking.model.AccessChannel;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TrackedContentEntryDTOTest
{
    @Test
    public void test_setStoreKey_0() throws Throwable
    {
        StoreKey storeKey0 = new StoreKey();
        AccessChannel accessChannel1 = AccessChannel.NATIVE;
        TrackedContentEntryDTO trackedContentEntryDTO3 = new TrackedContentEntryDTO( storeKey0, accessChannel1,
                                                                                     "TrackedContentEntryDTO [\n  storeKey=%s\n  accessChannel=%s\n  path=%s\n  originUrl=%s\n  localUrl=%s\n  size=%d\n md5=%s\n  sha256=%s\n]" );
        assertNull( trackedContentEntryDTO3.getMd5() );
        assertNull( trackedContentEntryDTO3.getSize() );
        assertNull( trackedContentEntryDTO3.getSha256() );
        assertEquals( "/TrackedContentEntryDTO [\n  storeKey=%s\n  accessChannel=%s\n  path=%s\n  originUrl=%s\n  localUrl=%s\n  size=%d\n md5=%s\n  sha256=%s\n]",
                      trackedContentEntryDTO3.getPath() );
        assertNull( trackedContentEntryDTO3.getOriginUrl() );
        assertNull( trackedContentEntryDTO3.getSha1() );
        assertNull( trackedContentEntryDTO3.getLocalUrl() );
        trackedContentEntryDTO3.setStoreKey( storeKey0 );
        assertNull( trackedContentEntryDTO3.getMd5() );
        assertNull( trackedContentEntryDTO3.getSize() );
        assertNull( trackedContentEntryDTO3.getSha256() );
        assertEquals( "/TrackedContentEntryDTO [\n  storeKey=%s\n  accessChannel=%s\n  path=%s\n  originUrl=%s\n  localUrl=%s\n  size=%d\n md5=%s\n  sha256=%s\n]",
                      trackedContentEntryDTO3.getPath() );
        assertNull( trackedContentEntryDTO3.getOriginUrl() );
        assertNull( trackedContentEntryDTO3.getSha1() );
        assertNull( trackedContentEntryDTO3.getLocalUrl() );

    }

    @Test
    public void test_TrackedContentEntryDTO_0() throws Throwable
    {
        StoreKey storeKey0 = new StoreKey();
        AccessChannel accessChannel1 = AccessChannel.NATIVE;
        TrackedContentEntryDTO trackedContentEntryDTO3 =
                        new TrackedContentEntryDTO( storeKey0, accessChannel1, "/74sS<1" );
        assertNull( trackedContentEntryDTO3.getMd5() );
        assertNull( trackedContentEntryDTO3.getSize() );
        assertNull( trackedContentEntryDTO3.getSha256() );
        assertEquals( "/74sS<1", trackedContentEntryDTO3.getPath() );
        assertNull( trackedContentEntryDTO3.getOriginUrl() );
        assertNull( trackedContentEntryDTO3.getSha1() );
        assertNull( trackedContentEntryDTO3.getLocalUrl() );

    }

    @Test
    public void test_setMd5_0() throws Throwable
    {
        StoreKey storeKey0 = new StoreKey();
        AccessChannel accessChannel1 = AccessChannel.NATIVE;
        TrackedContentEntryDTO trackedContentEntryDTO3 =
                        new TrackedContentEntryDTO( storeKey0, accessChannel1, "/74sS<1" );
        assertNull( trackedContentEntryDTO3.getMd5() );
        assertNull( trackedContentEntryDTO3.getSize() );
        assertNull( trackedContentEntryDTO3.getSha256() );
        assertEquals( "/74sS<1", trackedContentEntryDTO3.getPath() );
        assertNull( trackedContentEntryDTO3.getOriginUrl() );
        assertNull( trackedContentEntryDTO3.getSha1() );
        assertNull( trackedContentEntryDTO3.getLocalUrl() );
        trackedContentEntryDTO3.setMd5( "i(-hhVojGKg!S!!Q" );
        assertEquals( "i(-hhVojGKg!S!!Q", trackedContentEntryDTO3.getMd5() );
        assertNull( trackedContentEntryDTO3.getSize() );
        assertNull( trackedContentEntryDTO3.getSha256() );
        assertEquals( "/74sS<1", trackedContentEntryDTO3.getPath() );
        assertNull( trackedContentEntryDTO3.getOriginUrl() );
        assertNull( trackedContentEntryDTO3.getSha1() );
        assertNull( trackedContentEntryDTO3.getLocalUrl() );

    }

    @Test
    public void test_setSha1_0() throws Throwable
    {
        TrackedContentEntryDTO trackedContentEntryDTO0 = new TrackedContentEntryDTO();
        trackedContentEntryDTO0.setSha1( " s/1r" );
        assertNull( trackedContentEntryDTO0.getMd5() );
        assertNull( trackedContentEntryDTO0.getSize() );
        assertNull( trackedContentEntryDTO0.getSha256() );
        assertNull( trackedContentEntryDTO0.getPath() );
        assertNull( trackedContentEntryDTO0.getOriginUrl() );
        assertEquals( " s/1r", trackedContentEntryDTO0.getSha1() );
        assertNull( trackedContentEntryDTO0.getLocalUrl() );

    }

    @Test
    public void test_setSize_0() throws Throwable
    {
        TrackedContentEntryDTO trackedContentEntryDTO0 = new TrackedContentEntryDTO();
        Long long2 = Long.valueOf( ( -2113L ) );
        trackedContentEntryDTO0.setSize( long2 );
        assertNull( trackedContentEntryDTO0.getMd5() );
        assertEquals( -2113L, trackedContentEntryDTO0.getSize().longValue() );
        assertNull( trackedContentEntryDTO0.getSha256() );
        assertNull( trackedContentEntryDTO0.getPath() );
        assertNull( trackedContentEntryDTO0.getOriginUrl() );
        assertNull( trackedContentEntryDTO0.getSha1() );
        assertNull( trackedContentEntryDTO0.getLocalUrl() );

    }

    @Test
    public void test_setTimestamps_0() throws Throwable
    {
        AccessChannel accessChannel0 = AccessChannel.GENERIC_PROXY;
        StoreType storeType1 = StoreType.hosted;
        StoreKey storeKey4 = new StoreKey( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                           storeType1, "mpv" );
        assertEquals( "mpv", storeKey4.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey4.getPackageType() );
        TrackedContentEntryDTO trackedContentEntryDTO6 = new TrackedContentEntryDTO( storeKey4, accessChannel0,
                                                                                     "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO6.getMd5() );
        assertNull( trackedContentEntryDTO6.getSize() );
        assertNull( trackedContentEntryDTO6.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO6.getPath() );
        assertNull( trackedContentEntryDTO6.getOriginUrl() );
        assertNull( trackedContentEntryDTO6.getSha1() );
        assertNull( trackedContentEntryDTO6.getLocalUrl() );
        java.util.HashSet<Long> longSet7 = new java.util.HashSet<Long>();
        boolean boolean9 = longSet7.add( ( -1L ) );
        assertTrue( boolean9 );
        trackedContentEntryDTO6.setTimestamps( longSet7 );
        assertNull( trackedContentEntryDTO6.getMd5() );
        assertNull( trackedContentEntryDTO6.getSize() );
        assertNull( trackedContentEntryDTO6.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO6.getPath() );
        assertNull( trackedContentEntryDTO6.getOriginUrl() );
        assertNull( trackedContentEntryDTO6.getSha1() );
        assertNull( trackedContentEntryDTO6.getLocalUrl() );

    }

    @Test
    public void test_equals_0() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        org.commonjava.indy.service.tracking.model.TrackedContent trackedContent5 =
                        new org.commonjava.indy.service.tracking.model.TrackedContent();
        boolean boolean6 = trackedContentEntryDTO4.equals( trackedContent5 );
        assertFalse( boolean6 );

    }

    @Test
    public void test_equals_1() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        StoreKey storeKey6 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        StoreKey storeKey10 = new StoreKey( "", storeType7, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey10.getName() );
        assertEquals( "", storeKey10.getPackageType() );
        StoreType storeType11 = storeKey10.getType();
        StoreKey storeKey14 = new StoreKey( ":remote:hi!", storeType11, "" );
        assertEquals( "", storeKey14.getName() );
        assertEquals( ":remote:hi!", storeKey14.getPackageType() );
        boolean boolean15 = trackedContentEntryDTO4.equals( storeKey14 );
        assertFalse( boolean15 );

    }

    @Test
    public void test_equals_2() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        StoreKey storeKey6 = StoreKey.fromString( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        org.commonjava.indy.service.tracking.model.TrackingKey trackingKey8 =
                        new org.commonjava.indy.service.tracking.model.TrackingKey(
                                        "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", trackingKey8.getId() );
        AccessChannel accessChannel9 = AccessChannel.GENERIC_PROXY;
        org.commonjava.indy.service.tracking.model.StoreEffect storeEffect10 =
                        org.commonjava.indy.service.tracking.model.StoreEffect.DOWNLOAD;
        ContentTransferDTO contentTransferDTO13 =
                        new ContentTransferDTO( storeKey6, trackingKey8, accessChannel9, "/r{v0l5M#n}5r",
                                                "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                                                storeEffect10 );
        assertEquals( "/r{v0l5M#n}5r", contentTransferDTO13.getPath() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                      contentTransferDTO13.getOriginUrl() );
        boolean boolean14 = trackedContentEntryDTO4.equals( contentTransferDTO13 );
        assertFalse( boolean14 );

    }

    @Test
    public void test_equals_3() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord dtxTrackingRecord5 =
                        new org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord();
        boolean boolean6 = trackedContentEntryDTO4.equals( dtxTrackingRecord5 );
        assertFalse( boolean6 );

    }

    @Test
    public void test_equals_4() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        Object obj5 = new Object();
        Class<?> wildcardClass6 = obj5.getClass();
        assertEquals( "java.lang.Object", wildcardClass6.getName() );
        assertEquals( "java.lang", wildcardClass6.getPackageName() );
        io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object> objObjectMapperDeserializer7 =
                        new io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object>(
                                        (Class<Object>) wildcardClass6 );
        Class<?> wildcardClass8 = objObjectMapperDeserializer7.getClass();
        assertEquals( "io.quarkus.kafka.client.serialization.ObjectMapperDeserializer", wildcardClass8.getName() );
        assertEquals( "io.quarkus.kafka.client.serialization", wildcardClass8.getPackageName() );
        com.fasterxml.jackson.databind.ObjectMapper objectMapper9 = null;
        io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object> objObjectMapperDeserializer10 =
                        new io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object>(
                                        (Class<Object>) wildcardClass8, objectMapper9 );
        Class<?> wildcardClass11 = objObjectMapperDeserializer10.getClass();
        assertEquals( "io.quarkus.kafka.client.serialization.ObjectMapperDeserializer", wildcardClass11.getName() );
        assertEquals( "io.quarkus.kafka.client.serialization", wildcardClass11.getPackageName() );
        com.fasterxml.jackson.databind.ObjectMapper objectMapper12 = null;
        io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object> objObjectMapperDeserializer13 =
                        new io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object>(
                                        (Class<Object>) wildcardClass11, objectMapper12 );
        boolean boolean14 = trackedContentEntryDTO4.equals( objObjectMapperDeserializer13 );
        assertFalse( boolean14 );

    }

    @Test
    public void test_equals_5() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        TrackedContentDTO trackedContentDTO5 = new TrackedContentDTO();
        boolean boolean6 = trackedContentEntryDTO4.equals( trackedContentDTO5 );
        assertFalse( boolean6 );

    }

    @Test
    public void test_equals_6() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        TrackingIdsDTO trackingIdsDTO5 = new TrackingIdsDTO();
        boolean boolean6 = trackedContentEntryDTO4.equals( trackingIdsDTO5 );
        assertFalse( boolean6 );

    }

    @Test
    public void test_equals_7() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy configurableRetryPolicy7 =
                        new org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy( 10, 1 );
        boolean boolean8 = trackedContentEntryDTO4.equals( configurableRetryPolicy7 );
        assertFalse( boolean8 );

    }

    @Test
    public void test_equals_8() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        java.util.concurrent.atomic.AtomicReference<Object> objAtomicReference5 =
                        new java.util.concurrent.atomic.AtomicReference<Object>();
        boolean boolean6 = trackedContentEntryDTO4.equals( objAtomicReference5 );
        assertFalse( boolean6 );

    }

    @Test
    public void test_equals_9() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        AccessChannel accessChannel5 = AccessChannel.GENERIC_PROXY;
        StoreType storeType6 = StoreType.hosted;
        StoreKey storeKey9 = new StoreKey( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                           storeType6, "mpv" );
        assertEquals( "mpv", storeKey9.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey9.getPackageType() );
        TrackedContentEntryDTO trackedContentEntryDTO11 = new TrackedContentEntryDTO( storeKey9, accessChannel5,
                                                                                      "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO11.getMd5() );
        assertNull( trackedContentEntryDTO11.getSize() );
        assertNull( trackedContentEntryDTO11.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO11.getPath() );
        assertNull( trackedContentEntryDTO11.getOriginUrl() );
        assertNull( trackedContentEntryDTO11.getSha1() );
        assertNull( trackedContentEntryDTO11.getLocalUrl() );
        boolean boolean12 = trackedContentEntryDTO4.equals( trackedContentEntryDTO11 );
        assertFalse( boolean12 );

    }

    @Test
    public void test_equals_10() throws Throwable
    {
        TrackedContentEntryDTO trackedContentEntryDTO0 = new TrackedContentEntryDTO();
        boolean boolean1 = trackedContentEntryDTO0.equals( trackedContentEntryDTO0 );
        assertTrue( boolean1 );

    }

    @Test
    public void test_equals_11() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        com.fasterxml.jackson.databind.ObjectMapper objectMapper5 = null;
        com.fasterxml.jackson.databind.ObjectMapper objectMapper6 = null;
        com.fasterxml.jackson.databind.ObjectMapper objectMapper7 = null;
        com.fasterxml.jackson.databind.ObjectMapper objectMapper8 = null;
        Object obj9 = new Object();
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager10 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput11 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper8, obj9,
                                                                                           traceManager10 );
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager12 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput13 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper7,
                                                                                           dTOStreamingOutput11,
                                                                                           traceManager12 );
        Class<?> wildcardClass14 = dTOStreamingOutput13.getClass();
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput", wildcardClass14.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs", wildcardClass14.getPackageName() );
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager15 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput16 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper6,
                                                                                           wildcardClass14,
                                                                                           traceManager15 );
        Class<?> wildcardClass17 = dTOStreamingOutput16.getClass();
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput", wildcardClass17.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs", wildcardClass17.getPackageName() );
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager18 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput19 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper5,
                                                                                           wildcardClass17,
                                                                                           traceManager18 );
        boolean boolean20 = trackedContentEntryDTO4.equals( dTOStreamingOutput19 );
        assertFalse( boolean20 );

    }

    @Test
    public void test_equals_12() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        com.datastax.driver.core.SocketOptions socketOptions5 = new com.datastax.driver.core.SocketOptions();
        boolean boolean6 = trackedContentEntryDTO4.equals( socketOptions5 );
        assertFalse( boolean6 );

    }

    @Test
    public void test_equals_13() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        org.commonjava.indy.service.tracking.model.TrackingKey trackingKey6 =
                        new org.commonjava.indy.service.tracking.model.TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey6.getId() );
        boolean boolean7 = trackedContentEntryDTO4.equals( trackingKey6 );
        assertFalse( boolean7 );

    }

    @Test
    public void test_equals_14() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        java.util.concurrent.atomic.AtomicBoolean atomicBoolean5 = new java.util.concurrent.atomic.AtomicBoolean();
        boolean boolean6 = trackedContentEntryDTO4.equals( atomicBoolean5 );
        assertFalse( boolean6 );

    }

    @Test
    public void test_equals_15() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        org.commonjava.indy.service.tracking.model.TrackedContentEntry trackedContentEntry5 =
                        new org.commonjava.indy.service.tracking.model.TrackedContentEntry();
        boolean boolean6 = trackedContentEntryDTO4.equals( trackedContentEntry5 );
        assertFalse( boolean6 );

    }

    @Test
    public void test_setPath_0() throws Throwable
    {
        TrackedContentEntryDTO trackedContentEntryDTO0 = new TrackedContentEntryDTO();
        trackedContentEntryDTO0.setPath( "*).{,9<i$9Nj?U?_z" );
        assertNull( trackedContentEntryDTO0.getMd5() );
        assertNull( trackedContentEntryDTO0.getSize() );
        assertNull( trackedContentEntryDTO0.getSha256() );
        assertEquals( "/*).{,9<i$9Nj?U?_z", trackedContentEntryDTO0.getPath() );
        assertNull( trackedContentEntryDTO0.getOriginUrl() );
        assertNull( trackedContentEntryDTO0.getSha1() );
        assertNull( trackedContentEntryDTO0.getLocalUrl() );

    }

    @Test
    public void test_compareTo_0() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "" );
        assertEquals( "", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        AccessChannel accessChannel2 = AccessChannel.GENERIC_PROXY;
        TrackedContentEntryDTO trackedContentEntryDTO4 = new TrackedContentEntryDTO( storeKey1, accessChannel2, "" );
        assertNull( trackedContentEntryDTO4.getMd5() );
        assertNull( trackedContentEntryDTO4.getSize() );
        assertNull( trackedContentEntryDTO4.getSha256() );
        assertEquals( "/", trackedContentEntryDTO4.getPath() );
        assertNull( trackedContentEntryDTO4.getOriginUrl() );
        assertNull( trackedContentEntryDTO4.getSha1() );
        assertNull( trackedContentEntryDTO4.getLocalUrl() );
        int int5 = trackedContentEntryDTO4.compareTo( trackedContentEntryDTO4 );
        assertEquals( 0, int5 );

    }

    @Test
    public void test_setOriginUrl_0() throws Throwable
    {
        TrackedContentEntryDTO trackedContentEntryDTO0 = new TrackedContentEntryDTO();
        trackedContentEntryDTO0.setOriginUrl( "nQb{MRl9pPEB" );
        assertNull( trackedContentEntryDTO0.getMd5() );
        assertNull( trackedContentEntryDTO0.getSize() );
        assertNull( trackedContentEntryDTO0.getSha256() );
        assertNull( trackedContentEntryDTO0.getPath() );
        assertEquals( "nQb{MRl9pPEB", trackedContentEntryDTO0.getOriginUrl() );
        assertNull( trackedContentEntryDTO0.getSha1() );
        assertNull( trackedContentEntryDTO0.getLocalUrl() );

    }

    @Test
    public void test_setSha256_0() throws Throwable
    {
        TrackedContentEntryDTO trackedContentEntryDTO0 = new TrackedContentEntryDTO();
        trackedContentEntryDTO0.setSha256( "}yPSmYXAE5UVC/ Jo" );
        assertNull( trackedContentEntryDTO0.getMd5() );
        assertNull( trackedContentEntryDTO0.getSize() );
        assertEquals( "}yPSmYXAE5UVC/ Jo", trackedContentEntryDTO0.getSha256() );
        assertNull( trackedContentEntryDTO0.getPath() );
        assertNull( trackedContentEntryDTO0.getOriginUrl() );
        assertNull( trackedContentEntryDTO0.getSha1() );
        assertNull( trackedContentEntryDTO0.getLocalUrl() );

    }

    @Test
    public void test_setLocalUrl_0() throws Throwable
    {
        TrackedContentEntryDTO trackedContentEntryDTO0 = new TrackedContentEntryDTO();
        String str1 = null;
        trackedContentEntryDTO0.setLocalUrl( str1 );
        assertNull( trackedContentEntryDTO0.getMd5() );
        assertNull( trackedContentEntryDTO0.getSize() );
        assertNull( trackedContentEntryDTO0.getSha256() );
        assertNull( trackedContentEntryDTO0.getPath() );
        assertNull( trackedContentEntryDTO0.getOriginUrl() );
        assertNull( trackedContentEntryDTO0.getSha1() );
        assertNull( trackedContentEntryDTO0.getLocalUrl() );

    }

    @Test
    public void test_setAccessChannel_0() throws Throwable
    {
        AccessChannel accessChannel0 = AccessChannel.GENERIC_PROXY;
        StoreType storeType1 = StoreType.hosted;
        StoreKey storeKey4 = new StoreKey( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                           storeType1, "mpv" );
        assertEquals( "mpv", storeKey4.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey4.getPackageType() );
        TrackedContentEntryDTO trackedContentEntryDTO6 = new TrackedContentEntryDTO( storeKey4, accessChannel0,
                                                                                     "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO6.getMd5() );
        assertNull( trackedContentEntryDTO6.getSize() );
        assertNull( trackedContentEntryDTO6.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO6.getPath() );
        assertNull( trackedContentEntryDTO6.getOriginUrl() );
        assertNull( trackedContentEntryDTO6.getSha1() );
        assertNull( trackedContentEntryDTO6.getLocalUrl() );
        AccessChannel accessChannel7 = AccessChannel.GENERIC_PROXY;
        trackedContentEntryDTO6.setAccessChannel( accessChannel7 );
        assertNull( trackedContentEntryDTO6.getMd5() );
        assertNull( trackedContentEntryDTO6.getSize() );
        assertNull( trackedContentEntryDTO6.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO6.getPath() );
        assertNull( trackedContentEntryDTO6.getOriginUrl() );
        assertNull( trackedContentEntryDTO6.getSha1() );
        assertNull( trackedContentEntryDTO6.getLocalUrl() );

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
