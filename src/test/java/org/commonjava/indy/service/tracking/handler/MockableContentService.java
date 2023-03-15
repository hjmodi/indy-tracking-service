package org.commonjava.indy.service.tracking.handler;

import io.quarkus.test.Mock;
import org.apache.http.HttpStatus;
import org.commonjava.indy.service.tracking.client.content.ContentService;
import org.commonjava.indy.service.tracking.jaxrs.ResponseHelper;
import org.commonjava.indy.service.tracking.model.AccessChannel;
import org.commonjava.indy.service.tracking.model.StoreEffect;
import org.commonjava.indy.service.tracking.model.StoreKey;
import org.commonjava.indy.service.tracking.model.StoreType;
import org.commonjava.indy.service.tracking.model.TrackedContent;
import org.commonjava.indy.service.tracking.model.TrackedContentEntry;
import org.commonjava.indy.service.tracking.model.TrackingKey;
import org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO;
import org.commonjava.indy.service.tracking.model.pkg.PackageTypeConstants;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Mock
@RestClient
public class MockableContentService
                implements ContentService
{
    @Inject
    ResponseHelper helper;

    @Override
    public Response recalculateEntrySet( final Set<ContentTransferDTO> entries )
    {
        Set<TrackedContentEntry> newEntries = new HashSet<>();
        TrackedContentEntry entry = new TrackedContentEntry();
        entry.setPath( "/path/to/file" );
        entry.setTrackingKey( new TrackingKey( "tracking-id" ) );
        entry.setStoreKey( new StoreKey( PackageTypeConstants.PKG_TYPE_MAVEN, StoreType.remote, "test" ) );
        entry.setAccessChannel( AccessChannel.GENERIC_PROXY );
        entry.setOriginUrl( "https://example.com/file" );
        entry.setEffect( StoreEffect.UPLOAD );
        entry.setMd5( "md5hash124" );
        entry.setSha1( "sha1hash124" );
        entry.setSha256( "sha256hash124" );
        newEntries.add( entry );
        return Response.status( HttpStatus.SC_OK ).entity( newEntries ).build();
    }

    @Override
    public File getZipRepository( TrackedContent record )
    {
        File file;
        try
        {
            file = File.createTempFile( "test", ".zip" );
        }
        catch ( IOException e )
        {
            return null;
        }
        return file;
    }

}
