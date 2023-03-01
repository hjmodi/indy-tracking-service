package org.commonjava.indy.service.tracking.client;

import io.quarkus.test.junit.QuarkusTest;
import org.commonjava.indy.service.tracking.client.content.ContentService;
import org.commonjava.indy.service.tracking.model.TrackedContent;
import org.commonjava.indy.service.tracking.model.TrackedContentEntry;
import org.commonjava.indy.service.tracking.model.dto.ContentTransferDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@QuarkusTest
public class ContentServiceTest
{
    @Inject
    @RestClient
    ContentService contentService;

    @Test
    public void testRecalculateEntrySet()
    {
        Set<ContentTransferDTO> entries = new HashSet<>();
        Response response = contentService.recalculateEntrySet( entries );
        Set<TrackedContentEntry> newEntries = response.readEntity( HashSet.class );
        Assertions.assertNotNull( newEntries );
        Assertions.assertEquals( 1, newEntries.size() );
    }

    @Test
    public void testGetZipRepository()
    {
        TrackedContent record = new TrackedContent();
        File file = contentService.getZipRepository( record );
        Assertions.assertNotNull( file );
        Assertions.assertEquals( "testfile", file.getName() );
    }

}
