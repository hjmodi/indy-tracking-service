package org.commonjava.indy.service.tracking.client.content;

import org.commonjava.indy.service.tracking.client.CustomClientRequestFilter;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path( "/api/admin/maint/content" )
@RegisterRestClient( configKey = "content-service-api" )
@RegisterProvider( CustomClientRequestFilter.class )
public interface MaintenanceService
{
    @POST
    @Path( "/batch/delete" )
    Response doDelete( BatchDeleteRequest request );

}
