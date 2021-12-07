package org.acme.boundary;

import org.acme.control.KundenService;
import org.acme.entity.Adresse;
import org.acme.entity.Kunde;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/kunden/{id}/adresse")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdressenResource {

    @Inject
    KundenService kundenService;

    @GET
    public Response getAdresse(@PathParam("id") long id) {
        Adresse entity = kundenService.adresseAbfragen(id);
        return Response.ok(entity).build();
    }

    @POST
    @Transactional
    public Response createAdresse(@PathParam("id") long id, Adresse adresse) {
        Adresse entity = kundenService.adresseAnlegen(id, adresse);
        return Response.ok(entity).build();
    }

    @PUT
    @Transactional
    public Response updateAdresse(@PathParam("id") long id, Adresse adresse) {
        Adresse entity = kundenService.adresseAendern(id, adresse);
        return Response.ok(entity).build();
    }

    @DELETE
    @Transactional
    public Response deleteAdresse(@PathParam("id") long id) {
        Adresse entity = kundenService.adresseLoeschen(id);
        return Response.ok(entity).build();
    }
}
