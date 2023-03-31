/**
 * Copyright (C) 2023 Red Hat, Inc. (https://github.com/Commonjava/indy-tracking-service)
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
package org.commonjava.indy.service.tracking.profile;

import java.util.HashMap;
import java.util.Map;

public class KafkaFileEventProfile
                extends BaseIndyTestProfile
{
    @Override
    Map<String, String> getExtraConfigOverrides()
    {
        Map<String, String> config = new HashMap<>();
        config.put( "mp.messaging.incoming.file-event-in.connector", "smallrye-in-memory" );
        config.put( "mp.messaging.incoming.promote-event-in.connector", "smallrye-in-memory" );
        return config;
    }

}
