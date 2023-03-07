package org.commonjava.indy.service.tracking.data.metrics;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class NameUtilsTest
{
    @Test
    public void test_getName_0() throws Throwable
    {
        String[] strArray4 =
                        new String[] { "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}" };
        String str5 = NameUtils.getName( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                                         "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                                         "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                                         strArray4 );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}.IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}.IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      str5 );

    }

    @Test
    public void test_getSupername_0() throws Throwable
    {
        String[] strArray2 =
                        new String[] { "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}" };
        String str3 = NameUtils.getSupername(
                        "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}", strArray2 );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}.IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      str3 );

    }

    @Test
    public void test_name_0() throws Throwable
    {
        String[] strArray2 =
                        new String[] { "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}" };
        String str3 = NameUtils.name( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                                      strArray2 );
        assertEquals( "IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}.IndyEvent{eventID=e22f4d1d-8873-4519-963c-dc54891f366a, eventMetadata=null}",
                      str3 );

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
