/**
 * Copyright (C) 2023 Red Hat, Inc. (https://github.com/Commonjava/indy-repository-service)
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

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseIndyTestProfile
                implements QuarkusTestProfile
{
    @Override
    public Map<String, String> getConfigOverrides()
    {
        Map<String, String> configs = new HashMap<>();
        configs.put( "quarkus.security.auth.enabled-in-dev-mode", "false" );
        configs.put( "quarkus.keycloak.devservices.enabled", "false" );
        configs.put( "quarkus.oidc.enabled", "false" );
        configs.put( "indy_security.enabled", "false" );
        configs.putAll( getExtraConfigOverrides() );
        return configs;
    }

    abstract Map<String, String> getExtraConfigOverrides();

}
