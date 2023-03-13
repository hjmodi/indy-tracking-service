/**
 * Copyright (C) 2011-2022 Red Hat, Inc. (https://github.com/Commonjava/indy)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.indy.service.tracking.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class TrackingKeyTest
{
    @Inject
    private ObjectMapper mapper;

    @Test
    public void roundTripToJson() throws Exception
    {
        final String id = "adsfadsfadsfadsfads";
        final TrackingKey key = new TrackingKey( id );

        final String json = mapper.writeValueAsString( key );
        System.out.println( json );

        final TrackingKey result = mapper.readValue( json, TrackingKey.class );

        assertThat( result, notNullValue() );
        assertThat( result, equalTo( key ) );
        assertThat( result.getId(), equalTo( key.getId() ) );
    }

    @Test
    public void dontAllowNullTrackingId()
    {
        assertThrows( RuntimeException.class, () -> {
            new TrackingKey( null );
        } );
    }

}
