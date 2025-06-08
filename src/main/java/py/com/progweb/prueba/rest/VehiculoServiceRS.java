package py.com.progweb.prueba.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import py.com.progweb.prueba.model.Vehiculo;

@Path("/vehiculos")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class VehiculoServiceRS {

    @EJB
    private VehiculoService vehiculoService;

    @GET
    public Response listarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.listarVehiculos();
        if (vehiculos == null || vehiculos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vehiculos).build();
    }

    @GET
    @Path("/{id}")  
    public Response encontrarVehiculoPorId(@PathParam("id") Integer id) {
        Vehiculo vehiculo = vehiculoService.encontrarVehiculoPorId(id);
        if (vehiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vehiculo).build();
    }

    @POST
    public Response registrarVehiculo(Vehiculo vehiculo) {
        try {
            vehiculoService.registrarVehiculo(vehiculo);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modificarVehiculo(@PathParam("id") Integer id, Vehiculo vehiculoModificado) {
        try {
            Vehiculo vehiculo = vehiculoService.encontrarVehiculoPorId(id);
            if (vehiculo != null) {
                vehiculoModificado.setIdVehiculo(id);
                vehiculoService.modificarVehiculo(vehiculoModificado);
                return Response.ok().entity(vehiculoModificado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                .entity("Vehiculo con ID " + id + " no encontrado.")
                .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarVehiculoPorId(@PathParam("id") Integer id) {
        try {
            Vehiculo vehiculo = vehiculoService.encontrarVehiculoPorId(id);
            if (vehiculo != null) {
                vehiculoService.eliminarVehiculo(vehiculo);
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                .entity("Vehiculo con ID " + id + " no encontrado.")
                .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}