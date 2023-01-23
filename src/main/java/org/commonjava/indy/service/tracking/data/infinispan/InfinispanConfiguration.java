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
package org.commonjava.indy.service.tracking.data.infinispan;

import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Startup
@ApplicationScoped
public class InfinispanConfiguration
{
    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    @Inject
    @ConfigProperty( name = "ispn.configDir" )
    Optional<String> infinispanConfigDir;

    public File getInfinispanConfigDir()
    {
        final String dir = infinispanConfigDir.orElse( null );
        return isEmpty( dir ) ? null : new File( dir );
    }

    public void setInfinispanConfigDir( String infinispanConfigDir )
    {
        this.infinispanConfigDir = Optional.of( infinispanConfigDir );
    }
}
