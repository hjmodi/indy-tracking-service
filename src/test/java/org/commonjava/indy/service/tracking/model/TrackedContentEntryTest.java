/**
 * Copyright (C) 2011-2022 Red Hat, Inc. (https://github.com/Commonjava/indy)
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

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.commonjava.indy.service.tracking.model.AccessChannel.GENERIC_PROXY;
import static org.commonjava.indy.service.tracking.model.AccessChannel.NATIVE;
import static org.commonjava.indy.service.tracking.model.StoreEffect.DOWNLOAD;
import static org.commonjava.indy.service.tracking.model.StoreType.hosted;
import static org.commonjava.indy.service.tracking.model.StoreType.remote;
import static org.commonjava.indy.service.tracking.model.pkg.PackageTypeConstants.PKG_TYPE_GENERIC_HTTP;
import static org.commonjava.indy.service.tracking.model.pkg.PackageTypeConstants.PKG_TYPE_MAVEN;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class TrackedContentEntryTest
{
    /**
     * We have to hack this test a bit in order to test the ability to deserialize this first version of
     * TrackedContentEntry.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void externalizeAsV1_readCurrentVersion() throws IOException, ClassNotFoundException
    {
        TrackedContentEntryV1 ev1 = new TrackedContentEntryV1( new TrackingKey( "test-key" ),
                                                               new StoreKey( PKG_TYPE_GENERIC_HTTP, remote,
                                                                             "some-upstream" ), GENERIC_PROXY,
                                                               "http://some.upstream.url/path/to/file", "path/to/file",
                                                               DOWNLOAD, 10101010L, "aaaafffffccccceeeeddd",
                                                               "bbbcccceeeedddaaaaa", "aaadadaaaadaeee" );

        TrackedContentEntry out = new TrackedContentEntry( new TrackingKey( "test-key2" ),
                                                           new StoreKey( PKG_TYPE_MAVEN, hosted, "some-upstream2" ),
                                                           NATIVE, "http://some.upstream.url/path/to/file2",
                                                           "path/to/file2", DOWNLOAD, 10101011L,
                                                           "aaaafffffccccceeeedddfffffff",
                                                           "bbbcccceeeedddaaaaaffffffff", "aaadadaaaadaeeeffffffff" );

        TrackedContentEntry test =
                        new TrackedContentEntry( ev1.getTrackingKey(), ev1.getStoreKey(), ev1.getAccessChannel(),
                                                 ev1.getOriginUrl(), ev1.getPath(), ev1.getEffect(), ev1.getSize(),
                                                 ev1.getMd5(), ev1.getSha1(), ev1.getSha256() );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        ev1.writeExternal( oos );
        oos.flush();

        ObjectInputStream oin = new ObjectInputStream( new ByteArrayInputStream( baos.toByteArray() ) );
        out.readExternal( oin );

        assertThat( out.getTrackingKey(), equalTo( test.getTrackingKey() ) );
        assertThat( out.getStoreKey(), equalTo( test.getStoreKey() ) );
        assertThat( out.getAccessChannel(), equalTo( test.getAccessChannel() ) );
        assertThat( out.getOriginUrl(), equalTo( test.getOriginUrl() ) );
        assertThat( out.getPath(), equalTo( test.getPath() ) );
        assertThat( out.getEffect(), equalTo( test.getEffect() ) );
        assertThat( out.getSize(), equalTo( test.getSize() ) );
        assertThat( out.getMd5(), equalTo( test.getMd5() ) );
        assertThat( out.getSha1(), equalTo( test.getSha1() ) );
        assertThat( out.getSha256(), equalTo( test.getSha256() ) );
    }

    /**
     * We have to hack this test a bit in order to test the ability to deserialize this first version of
     * TrackedContentEntry.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void externalizeAsV2_readCurrentVersion() throws IOException, ClassNotFoundException
    {
        TrackedContentEntryV2 ev2 = new TrackedContentEntryV2( new TrackingKey( "test-key" ),
                                                               new StoreKey( PKG_TYPE_GENERIC_HTTP, remote,
                                                                             "some-upstream" ), GENERIC_PROXY,
                                                               "http://some.upstream.url/path/to/file", "path/to/file",
                                                               DOWNLOAD, 10101010L, "aaaafffffccccceeeeddd",
                                                               "bbbcccceeeedddaaaaa", "aaadadaaaadaeee" );

        TrackedContentEntry out = new TrackedContentEntry( new TrackingKey( "test-key2" ),
                                                           new StoreKey( PKG_TYPE_MAVEN, hosted, "some-upstream2" ),
                                                           NATIVE, "http://some.upstream.url/path/to/file2",
                                                           "path/to/file2", DOWNLOAD, 10101011L,
                                                           "aaaafffffccccceeeedddfffffff",
                                                           "bbbcccceeeedddaaaaaffffffff", "aaadadaaaadaeeeffffffff" );

        // nullify this to make sure that reading from the old version of the object doesn't set it.
        out.setTimestamps( null );

        TrackedContentEntry test =
                        new TrackedContentEntry( ev2.getTrackingKey(), ev2.getStoreKey(), ev2.getAccessChannel(),
                                                 ev2.getOriginUrl(), ev2.getPath(), ev2.getEffect(), ev2.getSize(),
                                                 ev2.getMd5(), ev2.getSha1(), ev2.getSha256() );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        ev2.writeExternal( oos );
        oos.flush();

        ObjectInputStream oin = new ObjectInputStream( new ByteArrayInputStream( baos.toByteArray() ) );
        out.readExternal( oin );

        assertThat( out.getTrackingKey(), equalTo( test.getTrackingKey() ) );
        assertThat( out.getStoreKey(), equalTo( test.getStoreKey() ) );
        assertThat( out.getAccessChannel(), equalTo( test.getAccessChannel() ) );
        assertThat( out.getOriginUrl(), equalTo( test.getOriginUrl() ) );
        assertThat( out.getPath(), equalTo( test.getPath() ) );
        assertThat( out.getEffect(), equalTo( test.getEffect() ) );
        assertThat( out.getSize(), equalTo( test.getSize() ) );
        assertThat( out.getMd5(), equalTo( test.getMd5() ) );
        assertThat( out.getSha1(), equalTo( test.getSha1() ) );
        assertThat( out.getSha256(), equalTo( test.getSha256() ) );
        assertThat( out.getTimestamps(), nullValue() );
    }

    @Test
    public void serializeRoundTrip_CurrentVersion() throws IOException, ClassNotFoundException
    {
        TrackedContentEntry entry = new TrackedContentEntry( new TrackingKey( "test-key" ),
                                                             new StoreKey( PKG_TYPE_GENERIC_HTTP, remote,
                                                                           "some-upstream" ), GENERIC_PROXY,
                                                             "http://some.upstream.url/path/to/file", "path/to/file",
                                                             DOWNLOAD, 10101010L, "aaaafffffccccceeeeddd",
                                                             "bbbcccceeeedddaaaaa", "aaadadaaaadaeee" );

        TrackedContentEntry test =
                        new TrackedContentEntry( entry.getTrackingKey(), entry.getStoreKey(), entry.getAccessChannel(),
                                                 entry.getOriginUrl(), entry.getPath(), entry.getEffect(),
                                                 entry.getSize(), entry.getMd5(), entry.getSha1(), entry.getSha256() );

        test.setTimestamps( entry.getTimestamps() );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( entry );
        oos.flush();

        ObjectInputStream oin = new ObjectInputStream( new ByteArrayInputStream( baos.toByteArray() ) );
        TrackedContentEntry out = (TrackedContentEntry) oin.readObject();

        assertThat( out.getTrackingKey(), equalTo( test.getTrackingKey() ) );
        assertThat( out.getStoreKey(), equalTo( test.getStoreKey() ) );
        assertThat( out.getAccessChannel(), equalTo( test.getAccessChannel() ) );
        assertThat( out.getOriginUrl(), equalTo( test.getOriginUrl() ) );
        assertThat( out.getPath(), equalTo( test.getPath() ) );
        assertThat( out.getEffect(), equalTo( test.getEffect() ) );
        assertThat( out.getSize(), equalTo( test.getSize() ) );
        assertThat( out.getMd5(), equalTo( test.getMd5() ) );
        assertThat( out.getSha1(), equalTo( test.getSha1() ) );
        assertThat( out.getSha256(), equalTo( test.getSha256() ) );
        assertThat( out.getTimestamps(), equalTo( test.getTimestamps() ) );
    }
}
