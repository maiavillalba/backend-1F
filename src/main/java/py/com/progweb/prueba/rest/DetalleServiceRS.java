package py.com.progweb.prueba.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import py.com.progweb.prueba.model.DetalleServicio;

@Path("/detalles-servicio")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class DetalleServiceRS {

    @EJB
    private DetalleService detalleServicioService;

    @GET
    public Response listarDetalles() {
        List<DetalleServicio> detalles = detalleServicioService.listarDetalles();
        if (detalles == null || detalles.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(detalles).build();
    }

    @GET
    @Path("/{id}")
    public Response encontrarDetallePorId(@PathParam("id") Integer id) {
        DetalleServicio detalle = detalleServicioService.encontrarDetallePorId(id);
        if (detalle == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(detalle).build();
    }

    @GET
    @Path("/servicio/{idServicio}")
    public Response encontrarDetallesPorServicioId(@PathParam("idServicio") Integer idServicio) {
        List<DetalleServicio> detalles = detalleServicioService.encontrarDetallesPorServicioId(idServicio);
        if (detalles == null || detalles.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(detalles).build();
    }

    @POST
    public Response registrarDetalle(DetalleServicio detalle) {
        try {
            detalleServicioService.registrarDetalle(detalle);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modificarDetalle(@PathParam("id") Integer id, DetalleServicio detalleModificado) {
        try {
            DetalleServicio existente = detalleServicioService.encontrarDetallePorId(id);
            if (existente != null) {
                detalleModificado.setIdDetalle(id);
                detalleServicioService.modificarDetalle(detalleModificado);
                return Response.ok(detalleModificado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Detalle de servicio con ID " + id + " no encontrado.")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarDetalle(@PathParam("id") Integer id) {
        try {
            DetalleServicio detalle = detalleServicioService.encontrarDetallePorId(id);
            if (detalle != null) {
                detalleServicioService.eliminarDetalle(detalle);
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Detalle de servicio con ID " + id + " no encontrado.")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
