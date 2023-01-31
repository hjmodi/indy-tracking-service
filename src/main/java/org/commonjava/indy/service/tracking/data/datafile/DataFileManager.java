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
package org.commonjava.indy.service.tracking.data.datafile;

import org.commonjava.indy.service.tracking.config.IndyTrackingConfiguration;
import org.commonjava.indy.service.tracking.data.datafile.change.DataFileEventManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.nio.file.Paths;

@ApplicationScoped
public class DataFileManager
{

    @Inject
    private IndyTrackingConfiguration config;

    @Inject
    private DataFileEventManager fileEventManager;

    protected DataFileManager()
    {
    }

    public DataFileManager( final IndyTrackingConfiguration config, final DataFileEventManager fileEventManager )
    {
        this.config = config;
        this.fileEventManager = fileEventManager;
    }

    public DataFile getDataFile( final String... pathParts )
    {
        final File base = config.baseDir();
        final File f = Paths.get( base.getAbsolutePath(), pathParts ).toFile();

        return new DataFile( f, fileEventManager );
    }

    public File getDetachedDataBasedir()
    {
        return config.baseDir();
    }

    public File getDetachedWorkBasedir()
    {
        return config.baseDir();
    }

}
