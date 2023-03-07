package org.commonjava.indy.service.tracking.change;

import io.quarkus.test.junit.QuarkusTest;
import org.commonjava.event.file.FileEvent;
import org.commonjava.event.file.FileEventType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class FoloTrackingListenerTest
{
    @Test
    public void test_handleFileStorageEvent_0() throws Throwable
    {
        FoloTrackingListener foloTrackingListener0 = new FoloTrackingListener();
        FileEventType fileEventType1 = null;
        FileEvent fileEvent2 = new FileEvent( fileEventType1 );
        assertNull( fileEvent2.getMd5() );
        assertNull( fileEvent2.getNodeId() );
        assertNull( fileEvent2.getSourceLocation() );
        assertNull( fileEvent2.getStoreKey() );
        assertNull( fileEvent2.getSourcePath() );
        assertNull( fileEvent2.getChecksum() );
        assertNull( fileEvent2.getSize() );
        assertNull( fileEvent2.getTargetPath() );
        assertNull( fileEvent2.getSessionId() );
        assertNull( fileEvent2.getTargetLocation() );
        assertNull( fileEvent2.getRequestId() );
        assertEquals( 1, fileEvent2.getEventVersion().intValue() );
        assertNull( fileEvent2.getSha1() );
        foloTrackingListener0.handleFileStorageEvent( fileEvent2 );

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
