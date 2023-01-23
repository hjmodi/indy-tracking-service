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
package org.commonjava.indy.service.tracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.commonjava.indy.service.tracking.model.pkg.MavenPackageTypeDescriptor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Schema( description = "Grouping of other artifact stores, with a defined order to the membership that determines content preference" )
public class Group
                extends ArtifactStore
                implements Externalizable
{

    private static final int STORE_VERSION = 1;

    private List<StoreKey> constituents;

    @JsonProperty( "prepend_constituent" )
    private boolean prependConstituent = false;

    public Group()
    {
        super();
        this.constituents = new ArrayList<>();
    }

    public Group( final String packageType, final String name, final List<StoreKey> constituents )
    {
        super( packageType, StoreType.group, name );
        this.constituents = new ArrayList<>( constituents );
    }

    @Deprecated
    public Group( final String name, final List<StoreKey> constituents )
    {
        super( MavenPackageTypeDescriptor.MAVEN_PKG_KEY, StoreType.group, name );
        this.constituents = new ArrayList<>( constituents );
    }

    public Group( final String packageType, final String name, final StoreKey... constituents )
    {
        super( packageType, StoreType.group, name );
        this.constituents = new ArrayList<>( Arrays.asList( constituents ) );
    }

    @Deprecated
    public Group( final String name, final StoreKey... constituents )
    {
        super( MavenPackageTypeDescriptor.MAVEN_PKG_KEY, StoreType.group, name );
        this.constituents = new ArrayList<>( Arrays.asList( constituents ) );
    }

    public List<StoreKey> getConstituents()
    {
        return constituents == null ? Collections.emptyList() : Collections.unmodifiableList( constituents );
    }

    public void setConstituents( final List<StoreKey> constituents )
    {
        synchronized ( this.constituents )
        {
            this.constituents.clear();
            this.constituents.addAll( constituents );
        }
    }

    public boolean addConstituent( final ArtifactStore store )
    {
        if ( store == null )
        {
            return false;
        }

        return addConstituent( store.getKey() );
    }

    public boolean addConstituent( final StoreKey repository )
    {
        if ( repository == null )
        {
            return false;
        }

        synchronized ( constituents )
        {
            if ( constituents.contains( repository ) )
            {
                return false;
            }

            // We will add new repo as the first member in group if prependConstituent set to true,
            // that means this repo may contain the most recent dependencies for the subsequent build.
            if ( isPrependConstituent() )
            {
                constituents.add( 0, repository );
                return true;
            }
            else
            {

                return constituents.add( repository );
            }
        }
    }

    public boolean removeConstituent( final ArtifactStore constituent )
    {
        return constituent != null && removeConstituent( constituent.getKey() );
    }

    public boolean removeConstituent( final StoreKey repository )
    {
        synchronized ( constituents )
        {
            return constituents.remove( repository );
        }
    }

    public boolean isPrependConstituent()
    {
        return prependConstituent;
    }

    public void setPrependConstituent( boolean prependConstituent )
    {
        this.prependConstituent = prependConstituent;
    }

    @Override
    public String toString()
    {
        return String.format( "Group[%s]", getName() );
    }

    @Override
    public Group copyOf()
    {
        return copyOf( getPackageType(), getName() );
    }

    @Override
    public Group copyOf( final String packageType, final String name )
    {
        Group g = new Group( packageType, name, new ArrayList<>( getConstituents() ) );
        g.setPrependConstituent( isPrependConstituent() );
        copyBase( g );

        return g;
    }

    @Override
    public void writeExternal( final ObjectOutput out ) throws IOException
    {
        super.writeExternal( out );

        out.writeInt( STORE_VERSION );

        out.writeObject( constituents );

        out.writeBoolean( prependConstituent );
    }

    @Override
    public void readExternal( final ObjectInput in ) throws IOException, ClassNotFoundException
    {
        super.readExternal( in );

        int storeVersion = in.readInt();
        if ( storeVersion > STORE_VERSION )
        {
            throw new IOException( "Cannot deserialize. Group version in data stream is: " + storeVersion
                                                   + " but this class can only deserialize up to version: "
                                                   + STORE_VERSION );
        }

        //noinspection unchecked
        this.constituents = (List<StoreKey>) in.readObject();

        this.prependConstituent = in.readBoolean();
    }

}
