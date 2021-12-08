package org.acme.boundary;

import org.acme.control.KundenService;
import org.acme.entity.Kunde;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/kunden")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class KundenResource {

    @Inject
    KundenService kundenService;

    @GET
    public Response getAllCustomers() {
        Collection<Kunde> kunden = kundenService.kundenAbfragen();
        return Response.ok(kunden).build();
    }

    @GET
    @Path("{id}")
    public Response getCustomer(@PathParam("id") long id) {
        Kunde kunde = kundenService.kundeAbfragen(id);
        return Response.ok(kunde).build();
    }


    @POST
    @Transactional
    public Response createCustomer(Kunde kunde) {
        Kunde neuerKunde = kundenService.kundeAnlegen(kunde);
        return Response.ok(neuerKunde).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") long id, Kunde kunde) {
        Kunde neuerKunde = kundenService.kundeUpdaten(id, kunde);
        return Response.ok(neuerKunde).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") long id) {
        Kunde neuerKunde = kundenService.kundeLoeschen(id);
        return Response.ok(neuerKunde).build();
    }
}
