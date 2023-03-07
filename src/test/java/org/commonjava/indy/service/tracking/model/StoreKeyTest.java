package org.commonjava.indy.service.tracking.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class StoreKeyTest
{
    @Test
    public void test_equals_0() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        TrackedContent trackedContent14 = new TrackedContent();
        boolean boolean15 = storeKey13.equals( trackedContent14 );
        assertFalse( boolean15 );

    }

    @Test
    public void test_equals_1() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        StoreKey storeKey15 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey15.getName() );
        assertEquals( "maven", storeKey15.getPackageType() );
        StoreType storeType16 = storeKey15.getType();
        StoreKey storeKey19 = new StoreKey( "", storeType16, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey19.getName() );
        assertEquals( "", storeKey19.getPackageType() );
        StoreType storeType20 = storeKey19.getType();
        StoreKey storeKey23 = new StoreKey( ":remote:hi!", storeType20, "" );
        assertEquals( "", storeKey23.getName() );
        assertEquals( ":remote:hi!", storeKey23.getPackageType() );
        boolean boolean24 = storeKey13.equals( storeKey23 );
        assertFalse( boolean24 );

    }

    @Test
    public void test_equals_2() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        StoreKey storeKey15 =
                        StoreKey.fromString( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", storeKey15.getName() );
        assertEquals( "maven", storeKey15.getPackageType() );
        TrackingKey trackingKey17 =
                        new TrackingKey( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", trackingKey17.getId() );
        AccessChannel accessChannel18 = AccessChannel.GENERIC_PROXY;
        StoreEffect storeEffect19 = StoreEffect.DOWNLOAD;
        org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO contentTransferDTO22 =
                        new org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO( storeKey15,
                                                                                               trackingKey17,
                                                                                               accessChannel18,
                                                                                               "/r{v0l5M#n}5r",
                                                                                               "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                                                                                               storeEffect19 );
        assertEquals( "/r{v0l5M#n}5r", contentTransferDTO22.getPath() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                      contentTransferDTO22.getOriginUrl() );
        boolean boolean23 = storeKey13.equals( contentTransferDTO22 );
        assertFalse( boolean23 );

    }

    @Test
    public void test_equals_3() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord dtxTrackingRecord14 =
                        new org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord();
        boolean boolean15 = storeKey13.equals( dtxTrackingRecord14 );
        assertFalse( boolean15 );

    }

    @Test
    public void test_equals_4() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        Object obj14 = new Object();
        Class<?> wildcardClass15 = obj14.getClass();
        assertEquals( "java.lang.Object", wildcardClass15.getName() );
        assertEquals( "java.lang", wildcardClass15.getPackageName() );
        io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object> objObjectMapperDeserializer16 =
                        new io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object>(
                                        (Class<Object>) wildcardClass15 );
        Class<?> wildcardClass17 = objObjectMapperDeserializer16.getClass();
        assertEquals( "io.quarkus.kafka.client.serialization.ObjectMapperDeserializer", wildcardClass17.getName() );
        assertEquals( "io.quarkus.kafka.client.serialization", wildcardClass17.getPackageName() );
        com.fasterxml.jackson.databind.ObjectMapper objectMapper18 = null;
        io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object> objObjectMapperDeserializer19 =
                        new io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object>(
                                        (Class<Object>) wildcardClass17, objectMapper18 );
        Class<?> wildcardClass20 = objObjectMapperDeserializer19.getClass();
        assertEquals( "io.quarkus.kafka.client.serialization.ObjectMapperDeserializer", wildcardClass20.getName() );
        assertEquals( "io.quarkus.kafka.client.serialization", wildcardClass20.getPackageName() );
        com.fasterxml.jackson.databind.ObjectMapper objectMapper21 = null;
        io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object> objObjectMapperDeserializer22 =
                        new io.quarkus.kafka.client.serialization.ObjectMapperDeserializer<Object>(
                                        (Class<Object>) wildcardClass20, objectMapper21 );
        boolean boolean23 = storeKey13.equals( objObjectMapperDeserializer22 );
        assertFalse( boolean23 );

    }

    @Test
    public void test_equals_5() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        org.commonjava.indy.service.tracking.model.dto.TrackedContentDTO trackedContentDTO14 =
                        new org.commonjava.indy.service.tracking.model.dto.TrackedContentDTO();
        boolean boolean15 = storeKey13.equals( trackedContentDTO14 );
        assertFalse( boolean15 );

    }

    @Test
    public void test_equals_6() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        org.commonjava.indy.service.tracking.model.dto.TrackingIdsDTO trackingIdsDTO14 =
                        new org.commonjava.indy.service.tracking.model.dto.TrackingIdsDTO();
        boolean boolean15 = storeKey13.equals( trackingIdsDTO14 );
        assertFalse( boolean15 );

    }

    @Test
    public void test_equals_7() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy configurableRetryPolicy16 =
                        new org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy( 10, 1 );
        boolean boolean17 = storeKey13.equals( configurableRetryPolicy16 );
        assertFalse( boolean17 );

    }

    @Test
    public void test_equals_8() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        java.util.concurrent.atomic.AtomicReference<Object> objAtomicReference14 =
                        new java.util.concurrent.atomic.AtomicReference<Object>();
        boolean boolean15 = storeKey13.equals( objAtomicReference14 );
        assertFalse( boolean15 );

    }

    @Test
    public void test_equals_9() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        AccessChannel accessChannel14 = AccessChannel.GENERIC_PROXY;
        StoreType storeType15 = StoreType.hosted;
        StoreKey storeKey18 = new StoreKey( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                                            storeType15, "mpv" );
        assertEquals( "mpv", storeKey18.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      storeKey18.getPackageType() );
        org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO trackedContentEntryDTO20 =
                        new org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO( storeKey18,
                                                                                                   accessChannel14,
                                                                                                   "org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO" );
        assertNull( trackedContentEntryDTO20.getMd5() );
        assertNull( trackedContentEntryDTO20.getSize() );
        assertNull( trackedContentEntryDTO20.getSha256() );
        assertEquals( "/org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO",
                      trackedContentEntryDTO20.getPath() );
        assertNull( trackedContentEntryDTO20.getOriginUrl() );
        assertNull( trackedContentEntryDTO20.getSha1() );
        assertNull( trackedContentEntryDTO20.getLocalUrl() );
        boolean boolean21 = storeKey13.equals( trackedContentEntryDTO20 );
        assertFalse( boolean21 );

    }

    @Test
    public void test_equals_10() throws Throwable
    {
        StoreKey storeKey0 = new StoreKey();
        Object obj1 = new Object();
        boolean boolean2 = storeKey0.equals( obj1 );
        assertFalse( boolean2 );

    }

    @Test
    public void test_equals_11() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        com.fasterxml.jackson.databind.ObjectMapper objectMapper14 = null;
        com.fasterxml.jackson.databind.ObjectMapper objectMapper15 = null;
        com.fasterxml.jackson.databind.ObjectMapper objectMapper16 = null;
        com.fasterxml.jackson.databind.ObjectMapper objectMapper17 = null;
        Object obj18 = new Object();
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager19 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput20 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper17, obj18,
                                                                                           traceManager19 );
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager21 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput22 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper16,
                                                                                           dTOStreamingOutput20,
                                                                                           traceManager21 );
        Class<?> wildcardClass23 = dTOStreamingOutput22.getClass();
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput", wildcardClass23.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs", wildcardClass23.getPackageName() );
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager24 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput25 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper15,
                                                                                           wildcardClass23,
                                                                                           traceManager24 );
        Class<?> wildcardClass26 = dTOStreamingOutput25.getClass();
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput", wildcardClass26.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs", wildcardClass26.getPackageName() );
        org.commonjava.indy.service.tracking.data.metrics.TraceManager traceManager27 = null;
        org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput dTOStreamingOutput28 =
                        new org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput( objectMapper14,
                                                                                           wildcardClass26,
                                                                                           traceManager27 );
        boolean boolean29 = storeKey13.equals( dTOStreamingOutput28 );
        assertFalse( boolean29 );

    }

    @Test
    public void test_equals_12() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        com.datastax.driver.core.SocketOptions socketOptions14 = new com.datastax.driver.core.SocketOptions();
        boolean boolean15 = storeKey13.equals( socketOptions14 );
        assertFalse( boolean15 );

    }

    @Test
    public void test_equals_13() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        TrackingKey trackingKey15 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey15.getId() );
        boolean boolean16 = storeKey13.equals( trackingKey15 );
        assertFalse( boolean16 );

    }

    @Test
    public void test_equals_14() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        java.util.concurrent.atomic.AtomicBoolean atomicBoolean14 = new java.util.concurrent.atomic.AtomicBoolean();
        boolean boolean15 = storeKey13.equals( atomicBoolean14 );
        assertFalse( boolean15 );

    }

    @Test
    public void test_equals_15() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreKey storeKey3 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey3.getName() );
        assertEquals( "maven", storeKey3.getPackageType() );
        int int4 = storeKey1.compareTo( storeKey3 );
        assertEquals( 0, int4 );
        StoreKey storeKey6 = StoreKey.fromString( "" );
        assertEquals( "", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        StoreType storeType7 = storeKey6.getType();
        boolean boolean8 = storeKey3.equals( storeType7 );
        assertFalse( boolean8 );
        String str9 = storeKey3.toString();
        assertEquals( "maven:remote:hi!", str9 );
        StoreType storeType10 = storeKey3.getType();
        StoreKey storeKey13 = new StoreKey( ":remote:maven:remote:hi!", storeType10, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey13.getName() );
        assertEquals( ":remote:maven:remote:hi!", storeKey13.getPackageType() );
        TrackedContentEntry trackedContentEntry14 = new TrackedContentEntry();
        boolean boolean15 = storeKey13.equals( trackedContentEntry14 );
        assertFalse( boolean15 );

    }

    @Test
    public void test_dedupe_0() throws Throwable
    {
        StoreKey storeKey0 = new StoreKey();
        StoreKey storeKey1 = StoreKey.dedupe( storeKey0 );
        assertNull( storeKey1.getName() );
        assertNull( storeKey1.getPackageType() );

    }

    @Test
    public void test_writeExternal_0() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        StoreType storeType2 = storeKey1.getType();
        StoreKey storeKey5 = new StoreKey( "", storeType2, "maven:remote:hi!" );
        assertEquals( "maven:remote:hi!", storeKey5.getName() );
        assertEquals( "", storeKey5.getPackageType() );
        StoreType storeType6 = storeKey5.getType();
        StoreKey storeKey9 = new StoreKey( ":remote:hi!", storeType6, "" );
        assertEquals( "", storeKey9.getName() );
        assertEquals( ":remote:hi!", storeKey9.getPackageType() );
        java.io.OutputStream outputStream10 = java.io.OutputStream.nullOutputStream();
        ObjectOutputStream objectOutputStream11 = new ObjectOutputStream( outputStream10 );
        assertEquals( (byte) 113, getFieldValue( objectOutputStream11, "TC_REFERENCE" ) );
        assertEquals( (byte) 119, getFieldValue( objectOutputStream11, "TC_BLOCKDATA" ) );
        assertEquals( (short) 5, getFieldValue( objectOutputStream11, "STREAM_VERSION" ) );
        assertEquals( (byte) 16, getFieldValue( objectOutputStream11, "SC_ENUM" ) );
        assertEquals( (byte) 126, getFieldValue( objectOutputStream11, "TC_MAX" ) );
        assertEquals( (byte) 122, getFieldValue( objectOutputStream11, "TC_BLOCKDATALONG" ) );
        assertEquals( (byte) 125, getFieldValue( objectOutputStream11, "TC_PROXYCLASSDESC" ) );
        assertEquals( (byte) 117, getFieldValue( objectOutputStream11, "TC_ARRAY" ) );
        assertEquals( (byte) 123, getFieldValue( objectOutputStream11, "TC_EXCEPTION" ) );
        assertEquals( (byte) 4, getFieldValue( objectOutputStream11, "SC_EXTERNALIZABLE" ) );
        assertEquals( (byte) 120, getFieldValue( objectOutputStream11, "TC_ENDBLOCKDATA" ) );
        assertEquals( (byte) 112, getFieldValue( objectOutputStream11, "TC_BASE" ) );
        assertEquals( (byte) 116, getFieldValue( objectOutputStream11, "TC_STRING" ) );
        assertEquals( (byte) 118, getFieldValue( objectOutputStream11, "TC_CLASS" ) );
        assertEquals( 8257536, getFieldValue( objectOutputStream11, "baseWireHandle" ) );
        assertEquals( (byte) 8, getFieldValue( objectOutputStream11, "SC_BLOCK_DATA" ) );
        assertEquals( (byte) 112, getFieldValue( objectOutputStream11, "TC_NULL" ) );
        assertEquals( (byte) 114, getFieldValue( objectOutputStream11, "TC_CLASSDESC" ) );
        assertEquals( (byte) 115, getFieldValue( objectOutputStream11, "TC_OBJECT" ) );
        assertEquals( (byte) 126, getFieldValue( objectOutputStream11, "TC_ENUM" ) );
        assertEquals( 2, getFieldValue( objectOutputStream11, "PROTOCOL_VERSION_2" ) );
        assertEquals( 1, getFieldValue( objectOutputStream11, "PROTOCOL_VERSION_1" ) );
        assertEquals( (short) -21267, getFieldValue( objectOutputStream11, "STREAM_MAGIC" ) );
        assertEquals( (byte) 2, getFieldValue( objectOutputStream11, "SC_SERIALIZABLE" ) );
        assertEquals( (byte) 124, getFieldValue( objectOutputStream11, "TC_LONGSTRING" ) );
        assertEquals( (byte) 121, getFieldValue( objectOutputStream11, "TC_RESET" ) );
        assertEquals( (byte) 1, getFieldValue( objectOutputStream11, "SC_WRITE_METHOD" ) );
        storeKey9.writeExternal( objectOutputStream11 );
        assertEquals( "", storeKey9.getName() );
        assertEquals( ":remote:hi!", storeKey9.getPackageType() );

    }

    @Test
    public void test_StoreKey_0() throws Throwable
    {
        StoreType storeType0 = StoreType.hosted;
        StoreKey storeKey3 = new StoreKey( "", storeType0, "" );
        assertEquals( "", storeKey3.getName() );
        assertEquals( "", storeKey3.getPackageType() );

    }

    @Test
    public void test_compareTo_0() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( ":" );
        assertEquals( ":", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );
        int int2 = storeKey1.compareTo( storeKey1 );
        assertEquals( 0, int2 );

    }

    @Test
    public void test_fromString_0() throws Throwable
    {
        StoreKey storeKey1 = StoreKey.fromString( "hi!" );
        assertEquals( "hi!", storeKey1.getName() );
        assertEquals( "maven", storeKey1.getPackageType() );

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
