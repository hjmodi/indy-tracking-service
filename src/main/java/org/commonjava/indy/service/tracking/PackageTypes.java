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
package org.commonjava.indy.service.tracking;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

/**
 * Utility class concerned with loading mapping of key String to {@link PackageTypeDescriptor} using {@link ServiceLoader}.
 * This class also provides methods for accessing the loaded descriptors in various ways.
 *
 * Created by jdcasey on 5/10/17.
 *
 * @see PackageTypeDescriptor
 */
public class PackageTypes
{
    private static final Map<String, PackageTypeDescriptor> PACKAGE_TYPES;

    static
    {
        Map<String, PackageTypeDescriptor> descriptors = new HashMap<>();

        ServiceLoader<PackageTypeDescriptor> descriptorLoader = ServiceLoader.load( PackageTypeDescriptor.class );
        descriptorLoader.forEach( ( descriptor ) -> descriptors.put( descriptor.getKey(), descriptor ) );

        PACKAGE_TYPES = unmodifiableMap( descriptors );
    }

    public static Set<PackageTypeDescriptor> getPackageTypeDescriptors()
    {
        return Set.copyOf( PACKAGE_TYPES.values() );
    }

    public static Map<String, PackageTypeDescriptor> getPackageTypeDescriptorMap()
    {
        return PACKAGE_TYPES;
    }

    public static Set<String> getPackageTypes()
    {
        return unmodifiableSet( PACKAGE_TYPES.keySet() );
    }

    public static boolean contains( final String packageType )
    {
        return PACKAGE_TYPES.containsKey( packageType );
    }

    public static PackageTypeDescriptor getPackageTypeDescriptor( final String packageType )
    {
        return PACKAGE_TYPES.get( packageType );
    }
}
