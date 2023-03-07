package org.commonjava.indy.service.tracking.jaxrs;

import com.datastax.driver.core.SocketOptions;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import io.opentelemetry.api.trace.Tracer;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import io.quarkus.test.junit.QuarkusTest;
import org.commonjava.indy.service.tracking.data.cassandra.ConfigurableRetryPolicy;
import org.commonjava.indy.service.tracking.data.cassandra.DtxTrackingRecord;
import org.commonjava.indy.service.tracking.data.metrics.TraceManager;
import org.commonjava.indy.service.tracking.model.AccessChannel;
import org.commonjava.indy.service.tracking.model.StoreEffect;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.commonjava.indy.service.tracking.model.TrackedContent;
import org.commonjava.indy.service.tracking.model.TrackedContentEntry;
import org.commonjava.indy.service.tracking.model.TrackingKey;
import org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO;
import org.commonjava.indy.service.tracking.model.dto.TrackedContentDTO;
import org.commonjava.indy.service.tracking.model.dto.TrackedContentEntryDTO;
import org.commonjava.indy.service.tracking.model.dto.TrackingIdsDTO;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class DTOStreamingOutputTest
{
    @Test
    public void test_DTOStreamingOutput_0() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        TrackedContent trackedContent5 = new TrackedContent();
        Tracer tracer6 = null;
        TraceManager traceManager7 = new TraceManager( tracer6 );
        assertEquals( "indy.ispn", getFieldValue( traceManager7, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager7, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput8 =
                        new DTOStreamingOutput( objectMapper4, trackedContent5, traceManager7 );

    }

    @Test
    public void test_DTOStreamingOutput_1() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
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
        Tracer tracer15 = null;
        TraceManager traceManager16 = new TraceManager( tracer15 );
        assertEquals( "indy.ispn", getFieldValue( traceManager16, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager16, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput17 = new DTOStreamingOutput( objectMapper4, storeKey14, traceManager16 );

    }

    @Test
    public void test_DTOStreamingOutput_2() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        StoreKey storeKey6 = StoreKey.fromString( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", storeKey6.getName() );
        assertEquals( "maven", storeKey6.getPackageType() );
        TrackingKey trackingKey8 =
                        new TrackingKey( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO" );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO", trackingKey8.getId() );
        AccessChannel accessChannel9 = AccessChannel.GENERIC_PROXY;
        StoreEffect storeEffect10 = StoreEffect.DOWNLOAD;
        ContentTransferDTO contentTransferDTO13 =
                        new ContentTransferDTO( storeKey6, trackingKey8, accessChannel9, "/r{v0l5M#n}5r",
                                                "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                                                storeEffect10 );
        assertEquals( "/r{v0l5M#n}5r", contentTransferDTO13.getPath() );
        assertEquals( "org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO",
                      contentTransferDTO13.getOriginUrl() );
        Tracer tracer14 = null;
        TraceManager traceManager15 = new TraceManager( tracer14 );
        assertEquals( "indy.ispn", getFieldValue( traceManager15, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager15, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput16 =
                        new DTOStreamingOutput( objectMapper4, contentTransferDTO13, traceManager15 );

    }

    @Test
    public void test_DTOStreamingOutput_3() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        DtxTrackingRecord dtxTrackingRecord5 = new DtxTrackingRecord();
        Tracer tracer6 = null;
        TraceManager traceManager7 = new TraceManager( tracer6 );
        assertEquals( "indy.ispn", getFieldValue( traceManager7, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager7, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput8 =
                        new DTOStreamingOutput( objectMapper4, dtxTrackingRecord5, traceManager7 );

    }

    @Test
    public void test_DTOStreamingOutput_4() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        Object obj5 = new Object();
        Class<?> wildcardClass6 = obj5.getClass();
        assertEquals( "java.lang.Object", wildcardClass6.getName() );
        assertEquals( "java.lang", wildcardClass6.getPackageName() );
        ObjectMapperDeserializer<Object> objObjectMapperDeserializer7 =
                        new ObjectMapperDeserializer<Object>( (Class<Object>) wildcardClass6 );
        Class<?> wildcardClass8 = objObjectMapperDeserializer7.getClass();
        assertEquals( "io.quarkus.kafka.client.serialization.ObjectMapperDeserializer", wildcardClass8.getName() );
        assertEquals( "io.quarkus.kafka.client.serialization", wildcardClass8.getPackageName() );
        ObjectMapper objectMapper9 = null;
        ObjectMapperDeserializer<Object> objObjectMapperDeserializer10 =
                        new ObjectMapperDeserializer<Object>( (Class<Object>) wildcardClass8, objectMapper9 );
        Class<?> wildcardClass11 = objObjectMapperDeserializer10.getClass();
        assertEquals( "io.quarkus.kafka.client.serialization.ObjectMapperDeserializer", wildcardClass11.getName() );
        assertEquals( "io.quarkus.kafka.client.serialization", wildcardClass11.getPackageName() );
        ObjectMapper objectMapper12 = null;
        ObjectMapperDeserializer<Object> objObjectMapperDeserializer13 =
                        new ObjectMapperDeserializer<Object>( (Class<Object>) wildcardClass11, objectMapper12 );
        Tracer tracer14 = null;
        TraceManager traceManager15 = new TraceManager( tracer14 );
        assertEquals( "indy.ispn", getFieldValue( traceManager15, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager15, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput16 =
                        new DTOStreamingOutput( objectMapper4, objObjectMapperDeserializer13, traceManager15 );

    }

    @Test
    public void test_DTOStreamingOutput_5() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        TrackedContentDTO trackedContentDTO5 = new TrackedContentDTO();
        Tracer tracer6 = null;
        TraceManager traceManager7 = new TraceManager( tracer6 );
        assertEquals( "indy.ispn", getFieldValue( traceManager7, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager7, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput8 =
                        new DTOStreamingOutput( objectMapper4, trackedContentDTO5, traceManager7 );

    }

    @Test
    public void test_DTOStreamingOutput_6() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        TrackingIdsDTO trackingIdsDTO5 = new TrackingIdsDTO();
        Tracer tracer6 = null;
        TraceManager traceManager7 = new TraceManager( tracer6 );
        assertEquals( "indy.ispn", getFieldValue( traceManager7, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager7, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput8 =
                        new DTOStreamingOutput( objectMapper4, trackingIdsDTO5, traceManager7 );

    }

    @Test
    public void test_DTOStreamingOutput_7() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        ConfigurableRetryPolicy configurableRetryPolicy7 = new ConfigurableRetryPolicy( 10, 1 );
        Tracer tracer8 = null;
        TraceManager traceManager9 = new TraceManager( tracer8 );
        assertEquals( "indy.ispn", getFieldValue( traceManager9, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager9, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput10 =
                        new DTOStreamingOutput( objectMapper4, configurableRetryPolicy7, traceManager9 );

    }

    @Test
    public void test_DTOStreamingOutput_8() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        AtomicReference<Object> objAtomicReference5 = new AtomicReference<Object>();
        Tracer tracer6 = null;
        TraceManager traceManager7 = new TraceManager( tracer6 );
        assertEquals( "indy.ispn", getFieldValue( traceManager7, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager7, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput8 =
                        new DTOStreamingOutput( objectMapper4, objAtomicReference5, traceManager7 );

    }

    @Test
    public void test_DTOStreamingOutput_9() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
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
        Tracer tracer12 = null;
        TraceManager traceManager13 = new TraceManager( tracer12 );
        assertEquals( "indy.ispn", getFieldValue( traceManager13, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager13, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput14 =
                        new DTOStreamingOutput( objectMapper4, trackedContentEntryDTO11, traceManager13 );

    }

    @Test
    public void test_DTOStreamingOutput_10() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        TraceManager traceManager1 = null;
        DTOStreamingOutput dTOStreamingOutput3 = new DTOStreamingOutput( objectMapper0, 0.0d, traceManager1 );

    }

    @Test
    public void test_DTOStreamingOutput_11() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        ObjectMapper objectMapper5 = null;
        ObjectMapper objectMapper6 = null;
        ObjectMapper objectMapper7 = null;
        ObjectMapper objectMapper8 = null;
        Object obj9 = new Object();
        TraceManager traceManager10 = null;
        DTOStreamingOutput dTOStreamingOutput11 = new DTOStreamingOutput( objectMapper8, obj9, traceManager10 );
        TraceManager traceManager12 = null;
        DTOStreamingOutput dTOStreamingOutput13 =
                        new DTOStreamingOutput( objectMapper7, dTOStreamingOutput11, traceManager12 );
        Class<?> wildcardClass14 = dTOStreamingOutput13.getClass();
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput", wildcardClass14.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs", wildcardClass14.getPackageName() );
        TraceManager traceManager15 = null;
        DTOStreamingOutput dTOStreamingOutput16 =
                        new DTOStreamingOutput( objectMapper6, wildcardClass14, traceManager15 );
        Class<?> wildcardClass17 = dTOStreamingOutput16.getClass();
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs.DTOStreamingOutput", wildcardClass17.getName() );
        assertEquals( "org.commonjava.indy.service.tracking.jaxrs", wildcardClass17.getPackageName() );
        TraceManager traceManager18 = null;
        DTOStreamingOutput dTOStreamingOutput19 =
                        new DTOStreamingOutput( objectMapper5, wildcardClass17, traceManager18 );
        Tracer tracer20 = null;
        TraceManager traceManager21 = new TraceManager( tracer20 );
        assertEquals( "indy.ispn", getFieldValue( traceManager21, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager21, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput22 =
                        new DTOStreamingOutput( objectMapper4, dTOStreamingOutput19, traceManager21 );

    }

    @Test
    public void test_DTOStreamingOutput_12() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        SocketOptions socketOptions5 = new SocketOptions();
        Tracer tracer6 = null;
        TraceManager traceManager7 = new TraceManager( tracer6 );
        assertEquals( "indy.ispn", getFieldValue( traceManager7, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager7, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput8 = new DTOStreamingOutput( objectMapper4, socketOptions5, traceManager7 );

    }

    @Test
    public void test_DTOStreamingOutput_13() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        TrackingKey trackingKey6 = new TrackingKey( "hi!" );
        assertEquals( "hi!", trackingKey6.getId() );
        Tracer tracer7 = null;
        TraceManager traceManager8 = new TraceManager( tracer7 );
        assertEquals( "indy.ispn", getFieldValue( traceManager8, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager8, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput9 = new DTOStreamingOutput( objectMapper4, trackingKey6, traceManager8 );

    }

    @Test
    public void test_DTOStreamingOutput_14() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        AtomicBoolean atomicBoolean5 = new AtomicBoolean();
        Tracer tracer6 = null;
        TraceManager traceManager7 = new TraceManager( tracer6 );
        assertEquals( "indy.ispn", getFieldValue( traceManager7, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager7, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput8 = new DTOStreamingOutput( objectMapper4, atomicBoolean5, traceManager7 );

    }

    @Test
    public void test_DTOStreamingOutput_15() throws Throwable
    {
        ObjectMapper objectMapper0 = null;
        JsonFactory jsonFactory1 = null;
        DefaultSerializerProvider defaultSerializerProvider2 = null;
        DefaultDeserializationContext defaultDeserializationContext3 = null;
        ObjectMapper objectMapper4 =
                        new ObjectMapper( jsonFactory1, defaultSerializerProvider2, defaultDeserializationContext3 );
        TrackedContentEntry trackedContentEntry5 = new TrackedContentEntry();
        Tracer tracer6 = null;
        TraceManager traceManager7 = new TraceManager( tracer6 );
        assertEquals( "indy.ispn", getFieldValue( traceManager7, "INDY_METRIC_ISPN" ) );
        assertEquals( "default", getFieldValue( traceManager7, "DEFAULT" ) );
        DTOStreamingOutput dTOStreamingOutput8 =
                        new DTOStreamingOutput( objectMapper4, trackedContentEntry5, traceManager7 );

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
