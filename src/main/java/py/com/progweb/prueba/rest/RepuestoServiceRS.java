package py.com.progweb.prueba.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import py.com.progweb.prueba.model.Repuesto;

@Path("/repuestos")
@Consumes("application/json")
@Produces("application/json")
public class RepuestoServiceRS {
    
    @Inject
    private RepuestoService repuestoService;

    @GET
    public List<Repuesto> listarRepuestos() {
        return repuestoService.listarRepuestos();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarRepuestoPorId(@PathParam("id") int id) {
        Repuesto repuesto = repuestoService.encontrarRepuestoPorId(id);

        if (repuesto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Repuesto con ID " + id + " no encontrado.")
                    .build();
        }

        return Response.ok(repuesto).build();
    }

    @POST
    public Response agregarRepuesto(Repuesto repuesto) {
        try {
            repuestoService.registrarRepuesto(repuesto);
            return Response.ok().entity(repuesto).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response modificarRepuesto(@PathParam("id") int id, Repuesto repuestoModificado) {
        try {
            Repuesto repuesto = repuestoService.encontrarRepuestoPorId(id);
            if (repuesto != null) {
                repuestoModificado.setIdRepuesto(id);
                repuestoService.modificarRepuesto(repuestoModificado);
                return Response.ok().entity(repuestoModificado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Repuesto con ID " + id + " no encontrado.")
                    .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminarRepuestoPorId(@PathParam("id") int id) {
        try {
            Repuesto repuesto = repuestoService.encontrarRepuestoPorId(id);
            if (repuesto != null) {
                repuestoService.eliminarRepuesto(repuesto);
                return Response.ok().entity("Repuesto con ID " + id + " eliminado.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Repuesto con ID " + id + " no encontrado.")
                    .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
