package org.commonjava.indy.service.tracking.handler;

import io.quarkus.test.Mock;
import org.apache.http.HttpStatus;
import org.commonjava.indy.service.tracking.client.content.ContentService;
import org.commonjava.indy.service.tracking.model.TrackedContent;
import org.commonjava.indy.service.tracking.model.TrackedContentEntry;
import org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.core.Response;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Mock
@RestClient
public class MockableContentService
                implements ContentService
{
    @Override
    public Response recalculateEntrySet( final Set<ContentTransferDTO> entries )
    {
        Set<TrackedContentEntry> newEntries = new HashSet<>();
        newEntries.add( new TrackedContentEntry() );
        return Response.status( HttpStatus.SC_OK ).entity( newEntries ).build();
    }

    @Override
    public File getZipRepository( TrackedContent record )
    {
        return new File( "testfile" );
    }

}
