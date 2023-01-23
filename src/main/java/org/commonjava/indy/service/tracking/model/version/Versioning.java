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
package org.commonjava.indy.service.tracking.model.version;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;

@Alternative
@Named
public class Versioning
{
    public final static String HEADER_INDY_API_VERSION = "Indy-API-Version"; // the API version for the requester

    public final static String HEADER_INDY_CUR_API_VERSION = "Indy-Cur-API-Version";
    // the current API version for the server

    public final static String HEADER_INDY_MIN_API_VERSION = "Indy-Min-API-Version";
    // the lowest supported API version for the server

    private String version;

    private String builder;

    @JsonProperty( "commit-id" )
    private String commitId;

    private String timestamp;

    private String apiVersion;

    public Versioning()
    {
    }

    @JsonCreator
    public Versioning( @JsonProperty( value = "version" ) final String version,
                       @JsonProperty( "builder" ) final String builder,
                       @JsonProperty( "commit-id" ) final String commitId,
                       @JsonProperty( "timestamp" ) final String timestamp,
                       @JsonProperty( "api-version" ) final String apiVersion )
    {
        this.version = version;
        this.builder = builder;
        this.commitId = commitId;
        this.timestamp = timestamp;
        this.apiVersion = apiVersion;
    }

    public String getVersion()
    {
        return version;
    }

    public String getBuilder()
    {
        return builder;
    }

    public String getCommitId()
    {
        return commitId;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public String getApiVersion()
    {
        return apiVersion;
    }

}
