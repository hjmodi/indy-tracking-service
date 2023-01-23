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

import org.apache.commons.lang3.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Alternative
public class DeprecatedApis
{
    private final Logger logger = LoggerFactory.getLogger( getClass() );

    private final List<DeprecatedApiEntry> deprecatedApis = new ArrayList<>();

    private final String minApiVersion;

    public DeprecatedApis( Properties props )
    {

        float minVersion = 0f;

        Set<String> keys = props.stringPropertyNames();
        for ( String key : keys )
        {
            DeprecatedApiEntry et;
            String value = props.getProperty( key );

            float startVersion;
            float endVersion;

            if ( key.contains( "," ) ) // range
            {
                key = key.replaceAll( "[\\[|\\]]", "" ); // strip off square brackets if present
                String[] kv = key.split( "," );
                startVersion = Float.parseFloat( kv[0].trim() );
                endVersion = Float.parseFloat( kv[1].trim() );
                et = new DeprecatedApiEntry( Range.between( startVersion, endVersion ), value );
            }
            else
            {
                endVersion = Float.parseFloat( key.trim() );
                et = new DeprecatedApiEntry( endVersion, value );
            }

            // Calculate minApiVersion
            if ( et.isOff() )
            {
                minVersion = endVersion + 0.1f;
            }
            deprecatedApis.add( et );
        }

        minApiVersion = Float.toString( minVersion );

        logger.debug( "Parsed deprecatedApis:{}, minApiVersion:{}", deprecatedApis, minApiVersion );
    }

    public Optional<DeprecatedApiEntry> getDeprecated( String reqApiVersion )
    {
        if ( isBlank( reqApiVersion ) )
        {
            return empty();
        }
        Float reqVer;
        try
        {
            reqVer = Float.parseFloat( reqApiVersion );
        }
        catch ( NumberFormatException e )
        {
            logger.warn( "Unknown api version: {}", reqApiVersion );
            return empty();
        }

        // the scopes may overlap, we go through range entries first and other entries next
        for ( DeprecatedApiEntry et : deprecatedApis )
        {
            if ( et.range != null && et.range.contains( reqVer ) )
            {
                return of( et );
            }
        }

        for ( DeprecatedApiEntry et : deprecatedApis )
        {
            if ( et.endVersion != null && reqVer <= et.endVersion )
            {
                return of( et );
            }
        }

        return empty();
    }

    public String getMinApiVersion()
    {
        return minApiVersion;
    }

    public static class DeprecatedApiEntry
    {
        private final String value;

        private Range<Float> range;

        private Float endVersion;

        DeprecatedApiEntry( Float endVersion, String value )
        {
            this.endVersion = endVersion;
            this.value = value;
        }

        public DeprecatedApiEntry( Range<Float> floatRange, String value )
        {
            this.range = floatRange;
            this.value = value;
        }

        public boolean isOff()
        {
            return "OFF".equals( value );
        }

        @Override
        public String toString()
        {
            return "DeprecatedApiEntry{" + "range=" + range + ", endVersion=" + endVersion + ", value='" + value + '\''
                            + '}';
        }

        public String getValue()
        {
            return value;
        }
    }
}
