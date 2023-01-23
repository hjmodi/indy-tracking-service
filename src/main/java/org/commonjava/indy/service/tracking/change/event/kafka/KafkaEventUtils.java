/**
 * Copyright (C) 2011-2022 Red Hat, Inc. (https://github.com/Commonjava/indy-repository-service)
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
package org.commonjava.indy.service.tracking.change.event.kafka;

import org.commonjava.indy.service.tracking.data.datafile.change.DataFileEvent;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * This event dispatcher will dispatch Store Event through kafka
 */
@ApplicationScoped
public class KafkaEventUtils
{
    public static final String CHANNEL_STORE = "store-event";

    public static final String CHANNEL_INTERNAL = "internal-store-stream";

    private final Logger logger = LoggerFactory.getLogger( KafkaEventUtils.class );

    @Channel( CHANNEL_STORE )
    @OnOverflow( value = OnOverflow.Strategy.BUFFER )
    @Inject
    Emitter<DataFileEvent> eventEmitter;

    @Channel( CHANNEL_INTERNAL )
    @OnOverflow( value = OnOverflow.Strategy.BUFFER )
    @Inject
    Emitter<DataFileEvent> internalEventEmitter;

    public void fireEvent( DataFileEvent event )
    {
        logger.trace( "Firing event to internal: {}", event );
        handleEvent( internalEventEmitter, event, "Can not processing internal event." );
        logger.trace( "Firing event to external: {}", event );
        handleEvent( eventEmitter, event, "Can not processing external event." );
    }

    private void handleEvent( Emitter<DataFileEvent> emitter, DataFileEvent event, String message )
    {
        try
        {
            emitter.send( event );
        }
        catch ( RuntimeException e )
        {
            logger.error( String.format( "%s: %s, Reason: %s", message, e, e.getMessage() ), e );
        }
    }
}
