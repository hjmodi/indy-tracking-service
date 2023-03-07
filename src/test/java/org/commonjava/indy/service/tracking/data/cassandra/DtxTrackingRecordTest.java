package org.commonjava.indy.service.tracking.data.cassandra;

import io.quarkus.test.junit.QuarkusTest;
import org.commonjava.indy.service.tracking.model.TrackedContentEntry;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class DtxTrackingRecordTest
{
    @Test
    public void test_setState_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = null;
        DtxTrackingRecord dtxTrackingRecord2 = DtxTrackingRecord.fromTrackedContentEntry( trackedContentEntry0, true );
        assertNull( dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertNull( dtxTrackingRecord2.getState() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );
        String str3 = dtxTrackingRecord2.getTrackingKey();
        dtxTrackingRecord2.setState( false );
        assertNull( dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertFalse( dtxTrackingRecord2.getState().booleanValue() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );

    }

    @Test
    public void test_setMd5_0() throws Throwable
    {
        DtxTrackingRecord dtxTrackingRecord0 = new DtxTrackingRecord();
        dtxTrackingRecord0.setMd5( "hi!" );
        assertEquals( "hi!", dtxTrackingRecord0.getMd5() );
        assertNull( dtxTrackingRecord0.getTrackingKey() );
        assertNull( dtxTrackingRecord0.getAccessChannel() );
        assertNull( dtxTrackingRecord0.getStoreKey() );
        assertNull( dtxTrackingRecord0.getSha256() );
        assertNull( dtxTrackingRecord0.getStoreEffect() );
        assertNull( dtxTrackingRecord0.getSize() );
        assertNull( dtxTrackingRecord0.getState() );
        assertNull( dtxTrackingRecord0.getStarted() );
        assertNull( dtxTrackingRecord0.getPath() );
        assertNull( dtxTrackingRecord0.getOriginUrl() );
        assertNull( dtxTrackingRecord0.getSha1() );
        assertNull( dtxTrackingRecord0.getLocalUrl() );

    }

    @Test
    public void test_setSha1_0() throws Throwable
    {
        DtxTrackingRecord dtxTrackingRecord0 = new DtxTrackingRecord();
        dtxTrackingRecord0.setMd5( "hi!" );
        dtxTrackingRecord0.setStarted( 0L );
        dtxTrackingRecord0.setAccessChannel( "" );
        dtxTrackingRecord0.setSha1( "" );
        assertEquals( "hi!", dtxTrackingRecord0.getMd5() );
        assertNull( dtxTrackingRecord0.getTrackingKey() );
        assertEquals( "", dtxTrackingRecord0.getAccessChannel() );
        assertNull( dtxTrackingRecord0.getStoreKey() );
        assertNull( dtxTrackingRecord0.getSha256() );
        assertNull( dtxTrackingRecord0.getStoreEffect() );
        assertNull( dtxTrackingRecord0.getSize() );
        assertNull( dtxTrackingRecord0.getState() );
        assertEquals( 0L, dtxTrackingRecord0.getStarted().longValue() );
        assertNull( dtxTrackingRecord0.getPath() );
        assertNull( dtxTrackingRecord0.getOriginUrl() );
        assertEquals( "", dtxTrackingRecord0.getSha1() );
        assertNull( dtxTrackingRecord0.getLocalUrl() );

    }

    @Test
    public void test_setStarted_0() throws Throwable
    {
        DtxTrackingRecord dtxTrackingRecord0 = new DtxTrackingRecord();
        dtxTrackingRecord0.setStarted( 10L );
        assertNull( dtxTrackingRecord0.getMd5() );
        assertNull( dtxTrackingRecord0.getTrackingKey() );
        assertNull( dtxTrackingRecord0.getAccessChannel() );
        assertNull( dtxTrackingRecord0.getStoreKey() );
        assertNull( dtxTrackingRecord0.getSha256() );
        assertNull( dtxTrackingRecord0.getStoreEffect() );
        assertNull( dtxTrackingRecord0.getSize() );
        assertNull( dtxTrackingRecord0.getState() );
        assertEquals( 10L, dtxTrackingRecord0.getStarted().longValue() );
        assertNull( dtxTrackingRecord0.getPath() );
        assertNull( dtxTrackingRecord0.getOriginUrl() );
        assertNull( dtxTrackingRecord0.getSha1() );
        assertNull( dtxTrackingRecord0.getLocalUrl() );

    }

    @Test
    public void test_fromTrackedContentEntry_0() throws Throwable
    {
        TrackedContentEntry trackedContentEntry0 = null;
        DtxTrackingRecord dtxTrackingRecord2 = DtxTrackingRecord.fromTrackedContentEntry( trackedContentEntry0, false );
        assertNull( dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertNull( dtxTrackingRecord2.getState() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );

    }

    @Test
    public void test_setSize_0() throws Throwable
    {
        DtxTrackingRecord dtxTrackingRecord0 = new DtxTrackingRecord();
        dtxTrackingRecord0.setMd5( "hi!" );
        Long long3 = dtxTrackingRecord0.getSize();
        Long long4 = dtxTrackingRecord0.getStarted();
        dtxTrackingRecord0.setSha256( "hi!" );
        dtxTrackingRecord0.setSize( -1L );
        assertEquals( "hi!", dtxTrackingRecord0.getMd5() );
        assertNull( dtxTrackingRecord0.getTrackingKey() );
        assertNull( dtxTrackingRecord0.getAccessChannel() );
        assertNull( dtxTrackingRecord0.getStoreKey() );
        assertEquals( "hi!", dtxTrackingRecord0.getSha256() );
        assertNull( dtxTrackingRecord0.getStoreEffect() );
        assertEquals( -1L, dtxTrackingRecord0.getSize().longValue() );
        assertNull( dtxTrackingRecord0.getState() );
        assertNull( dtxTrackingRecord0.getStarted() );
        assertNull( dtxTrackingRecord0.getPath() );
        assertNull( dtxTrackingRecord0.getOriginUrl() );
        assertNull( dtxTrackingRecord0.getSha1() );
        assertNull( dtxTrackingRecord0.getLocalUrl() );

    }

    @Test
    public void test_setTimestamps_0() throws Throwable
    {
        DtxTrackingRecord dtxTrackingRecord0 = new DtxTrackingRecord();
        HashSet<Long> longSet1 = new HashSet<Long>();
        boolean boolean3 = longSet1.add( 0L );
        assertTrue( boolean3 );
        dtxTrackingRecord0.setTimestamps( longSet1 );
        assertNull( dtxTrackingRecord0.getMd5() );
        assertNull( dtxTrackingRecord0.getTrackingKey() );
        assertNull( dtxTrackingRecord0.getAccessChannel() );
        assertNull( dtxTrackingRecord0.getStoreKey() );
        assertNull( dtxTrackingRecord0.getSha256() );
        assertNull( dtxTrackingRecord0.getStoreEffect() );
        assertNull( dtxTrackingRecord0.getSize() );
        assertNull( dtxTrackingRecord0.getState() );
        assertNull( dtxTrackingRecord0.getStarted() );
        assertNull( dtxTrackingRecord0.getPath() );
        assertNull( dtxTrackingRecord0.getOriginUrl() );
        assertNull( dtxTrackingRecord0.getSha1() );
        assertNull( dtxTrackingRecord0.getLocalUrl() );

    }

    @Test
    public void test_DtxTrackingRecord_0() throws Throwable
    {
        HashSet<Long> longSet13 = new HashSet<Long>();
        boolean boolean15 = longSet13.add( 0L );
        assertTrue( boolean15 );
        DtxTrackingRecord dtxTrackingRecord16 = new DtxTrackingRecord(
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}", true,
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}", 0L, 0L,
                        longSet13 );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getMd5() );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getTrackingKey() );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getAccessChannel() );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getStoreKey() );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getSha256() );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getStoreEffect() );
        assertEquals( 0L, dtxTrackingRecord16.getSize().longValue() );
        assertTrue( dtxTrackingRecord16.getState().booleanValue() );
        assertEquals( 0L, dtxTrackingRecord16.getStarted().longValue() );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getPath() );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getOriginUrl() );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getSha1() );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      dtxTrackingRecord16.getLocalUrl() );

    }

    @Test
    public void test_setTrackingKey_0() throws Throwable
    {
        org.commonjava.indy.service.tracking.model.TrackedContentEntry trackedContentEntry0 = null;
        DtxTrackingRecord dtxTrackingRecord2 = DtxTrackingRecord.fromTrackedContentEntry( trackedContentEntry0, false );
        assertNull( dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertNull( dtxTrackingRecord2.getState() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );
        dtxTrackingRecord2.setTrackingKey( "" );
        assertNull( dtxTrackingRecord2.getMd5() );
        assertEquals( "", dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertNull( dtxTrackingRecord2.getState() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );

    }

    @Test
    public void test_setPath_0() throws Throwable
    {
        org.commonjava.indy.service.tracking.model.TrackedContentEntry trackedContentEntry0 = null;
        DtxTrackingRecord dtxTrackingRecord2 = DtxTrackingRecord.fromTrackedContentEntry( trackedContentEntry0, true );
        assertNull( dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertNull( dtxTrackingRecord2.getState() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );
        String str3 = dtxTrackingRecord2.getTrackingKey();
        dtxTrackingRecord2.setState( false );
        dtxTrackingRecord2.setMd5( "hi!" );
        dtxTrackingRecord2.setPath( "hi!" );
        assertEquals( "hi!", dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertFalse( dtxTrackingRecord2.getState().booleanValue() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertEquals( "hi!", dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );

    }

    @Test
    public void test_setAccessChannel_0() throws Throwable
    {
        DtxTrackingRecord dtxTrackingRecord0 = new DtxTrackingRecord();
        dtxTrackingRecord0.setMd5( "hi!" );
        String str3 = dtxTrackingRecord0.getMd5();
        assertEquals( "hi!", str3 );
        dtxTrackingRecord0.setAccessChannel( "hi!" );
        assertEquals( "hi!", dtxTrackingRecord0.getMd5() );
        assertNull( dtxTrackingRecord0.getTrackingKey() );
        assertEquals( "hi!", dtxTrackingRecord0.getAccessChannel() );
        assertNull( dtxTrackingRecord0.getStoreKey() );
        assertNull( dtxTrackingRecord0.getSha256() );
        assertNull( dtxTrackingRecord0.getStoreEffect() );
        assertNull( dtxTrackingRecord0.getSize() );
        assertNull( dtxTrackingRecord0.getState() );
        assertNull( dtxTrackingRecord0.getStarted() );
        assertNull( dtxTrackingRecord0.getPath() );
        assertNull( dtxTrackingRecord0.getOriginUrl() );
        assertNull( dtxTrackingRecord0.getSha1() );
        assertNull( dtxTrackingRecord0.getLocalUrl() );

    }

    @Test
    public void test_setStoreKey_0() throws Throwable
    {
        DtxTrackingRecord dtxTrackingRecord0 = new DtxTrackingRecord();
        dtxTrackingRecord0.setMd5( "hi!" );
        Long long3 = dtxTrackingRecord0.getStarted();
        dtxTrackingRecord0.setStoreKey( "hi!" );
        assertEquals( "hi!", dtxTrackingRecord0.getMd5() );
        assertNull( dtxTrackingRecord0.getTrackingKey() );
        assertNull( dtxTrackingRecord0.getAccessChannel() );
        assertEquals( "hi!", dtxTrackingRecord0.getStoreKey() );
        assertNull( dtxTrackingRecord0.getSha256() );
        assertNull( dtxTrackingRecord0.getStoreEffect() );
        assertNull( dtxTrackingRecord0.getSize() );
        assertNull( dtxTrackingRecord0.getState() );
        assertNull( dtxTrackingRecord0.getStarted() );
        assertNull( dtxTrackingRecord0.getPath() );
        assertNull( dtxTrackingRecord0.getOriginUrl() );
        assertNull( dtxTrackingRecord0.getSha1() );
        assertNull( dtxTrackingRecord0.getLocalUrl() );

    }

    @Test
    public void test_setStoreEffect_0() throws Throwable
    {
        org.commonjava.indy.service.tracking.model.TrackedContentEntry trackedContentEntry0 = null;
        DtxTrackingRecord dtxTrackingRecord2 = DtxTrackingRecord.fromTrackedContentEntry( trackedContentEntry0, true );
        assertNull( dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertNull( dtxTrackingRecord2.getState() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );
        String str3 = dtxTrackingRecord2.getTrackingKey();
        dtxTrackingRecord2.setState( false );
        dtxTrackingRecord2.setMd5( "hi!" );
        String str8 = dtxTrackingRecord2.getSha1();
        dtxTrackingRecord2.setStoreEffect( "hi!" );
        assertEquals( "hi!", dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertEquals( "hi!", dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertFalse( dtxTrackingRecord2.getState().booleanValue() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );

    }

    @Test
    public void test_setOriginUrl_0() throws Throwable
    {
        DtxTrackingRecord dtxTrackingRecord0 = new DtxTrackingRecord();
        dtxTrackingRecord0.setMd5( "hi!" );
        Long long3 = dtxTrackingRecord0.getStarted();
        dtxTrackingRecord0.setStoreKey( "hi!" );
        dtxTrackingRecord0.setMd5( "" );
        dtxTrackingRecord0.setTrackingKey( "" );
        dtxTrackingRecord0.setOriginUrl( "hi!" );
        assertEquals( "", dtxTrackingRecord0.getMd5() );
        assertEquals( "", dtxTrackingRecord0.getTrackingKey() );
        assertNull( dtxTrackingRecord0.getAccessChannel() );
        assertEquals( "hi!", dtxTrackingRecord0.getStoreKey() );
        assertNull( dtxTrackingRecord0.getSha256() );
        assertNull( dtxTrackingRecord0.getStoreEffect() );
        assertNull( dtxTrackingRecord0.getSize() );
        assertNull( dtxTrackingRecord0.getState() );
        assertNull( dtxTrackingRecord0.getStarted() );
        assertNull( dtxTrackingRecord0.getPath() );
        assertEquals( "hi!", dtxTrackingRecord0.getOriginUrl() );
        assertNull( dtxTrackingRecord0.getSha1() );
        assertNull( dtxTrackingRecord0.getLocalUrl() );

    }

    @Test
    public void test_setSha256_0() throws Throwable
    {
        DtxTrackingRecord dtxTrackingRecord0 = new DtxTrackingRecord();
        dtxTrackingRecord0.setMd5( "hi!" );
        Long long3 = dtxTrackingRecord0.getSize();
        Long long4 = dtxTrackingRecord0.getStarted();
        dtxTrackingRecord0.setSha256( "hi!" );
        assertEquals( "hi!", dtxTrackingRecord0.getMd5() );
        assertNull( dtxTrackingRecord0.getTrackingKey() );
        assertNull( dtxTrackingRecord0.getAccessChannel() );
        assertNull( dtxTrackingRecord0.getStoreKey() );
        assertEquals( "hi!", dtxTrackingRecord0.getSha256() );
        assertNull( dtxTrackingRecord0.getStoreEffect() );
        assertNull( dtxTrackingRecord0.getSize() );
        assertNull( dtxTrackingRecord0.getState() );
        assertNull( dtxTrackingRecord0.getStarted() );
        assertNull( dtxTrackingRecord0.getPath() );
        assertNull( dtxTrackingRecord0.getOriginUrl() );
        assertNull( dtxTrackingRecord0.getSha1() );
        assertNull( dtxTrackingRecord0.getLocalUrl() );

    }

    @Test
    public void test_setLocalUrl_0() throws Throwable
    {
        org.commonjava.indy.service.tracking.model.TrackedContentEntry trackedContentEntry0 = null;
        DtxTrackingRecord dtxTrackingRecord2 = DtxTrackingRecord.fromTrackedContentEntry( trackedContentEntry0, false );
        assertNull( dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertNull( dtxTrackingRecord2.getState() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertNull( dtxTrackingRecord2.getLocalUrl() );
        dtxTrackingRecord2.setLocalUrl( "hi!" );
        assertNull( dtxTrackingRecord2.getMd5() );
        assertNull( dtxTrackingRecord2.getTrackingKey() );
        assertNull( dtxTrackingRecord2.getAccessChannel() );
        assertNull( dtxTrackingRecord2.getStoreKey() );
        assertNull( dtxTrackingRecord2.getSha256() );
        assertNull( dtxTrackingRecord2.getStoreEffect() );
        assertNull( dtxTrackingRecord2.getSize() );
        assertNull( dtxTrackingRecord2.getState() );
        assertNull( dtxTrackingRecord2.getStarted() );
        assertNull( dtxTrackingRecord2.getPath() );
        assertNull( dtxTrackingRecord2.getOriginUrl() );
        assertNull( dtxTrackingRecord2.getSha1() );
        assertEquals( "hi!", dtxTrackingRecord2.getLocalUrl() );

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
