package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Mecanico;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/mecanicos")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class MecanicoServiceRS {

    @EJB
    private MecanicoService mecanicoService;

    @GET
    @Path("/all")
    public Response getAll() {
        List<Mecanico> mecanicos = mecanicoService.getAll();
        if (mecanicos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No hay mecanicos registrados").build();
        }
        return Response.ok(mecanicos).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Mecanico mecanico = mecanicoService.getById(id);
        if (mecanico == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mecanico no encontrado").build();
        }
        return Response.ok(mecanico).build();
    }

    @GET
    @Path("/nombre/{nombre}")
    public Response getByNombre(@PathParam("nombre") String nombre) {
        List<Mecanico> mecanico = mecanicoService.getByNombre(nombre);
        if (mecanico.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mecanico no encontrado").build();
        }
        return Response.ok(mecanico).build();
    }

    @POST
    @Path("/add")
    public Response add(Mecanico mecanico) {
        mecanicoService.add(mecanico);
        return Response.ok(mecanico).build();
    }

    @PUT
    @Path("/update/{id}")
    public Response update(@PathParam("id") Long id, Mecanico nuevo) {
        Mecanico actual = mecanicoService.getById(id);
        if (actual == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mecanico no encontrado").build();
        }

        if (nuevo.getNombre() != null) actual.setNombre(nuevo.getNombre());
        if (nuevo.getDireccion() != null) actual.setDireccion(nuevo.getDireccion());
        if (nuevo.getTelefono() != null) actual.setTelefono(nuevo.getTelefono());
        if (nuevo.getFechaIngreso() != null) actual.setFechaIngreso(nuevo.getFechaIngreso());
        if (nuevo.getEspecialidad() != null) actual.setEspecialidad(nuevo.getEspecialidad());

        mecanicoService.update(actual);
        return Response.ok(actual).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        Mecanico m = mecanicoService.getById(id);
        if (m == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mecanico no encontrado").build();
        }
        mecanicoService.delete(m);
        return Response.ok("Mecanico eliminado").build();
    }
}
