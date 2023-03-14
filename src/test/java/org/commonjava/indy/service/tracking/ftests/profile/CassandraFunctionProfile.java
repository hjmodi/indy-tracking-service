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
package org.commonjava.indy.service.tracking.ftests.profile;

import org.commonjava.indy.service.tracking.data.cassandra.CassandraClient;
import org.commonjava.indy.service.tracking.data.cassandra.CassandraConfiguration;
import org.commonjava.indy.service.tracking.data.cassandra.CassandraTrackingQuery;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.CassandraContainer;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CassandraFunctionProfile
                extends BaseIndyTestProfile
{
    @Override
    Map<String, String> getExtraConfigOverrides()
    {
        Map<String, String> config = new HashMap<>();
        config.put( "cassandra.port", "9142" );
        return config;
    }

}
