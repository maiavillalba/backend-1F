package py.com.progweb.prueba.rest;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import py.com.progweb.prueba.model.Servicio;

@Path("/servicios")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class ServicioServiceRS {

    @EJB
    private ServicioService servicioService;

    @GET
    public Response listarServicios() {
        List<Servicio> servicios = servicioService.listarServicios();
        if (servicios == null || servicios.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(servicios).build();
    }

    @GET
    @Path("/{id}")
    public Response encontrarServicioPorId(@PathParam("id") Integer id) {
        Servicio servicio = servicioService.encontrarServicioPorId(id);
        if (servicio == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(servicio).build();
    }

    @GET
    @Path("/vehiculo/{idVehiculo}")
    public Response encontrarServiciosPorVehiculoId(@PathParam("idVehiculo") Integer idVehiculo) {
        List<Servicio> servicios = servicioService.encontrarServiciosPorVehiculoId(idVehiculo);
        if (servicios == null || servicios.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(servicios).build();
    }

    @GET
    @Path("/filtros")
    public Response buscarServiciosConFiltros(@QueryParam("idCliente") Integer idCliente,
                                              @QueryParam("fecha") String fechaStr) {
        try {
            Date fecha = null;
            if (fechaStr != null && !fechaStr.isEmpty()) {
                fecha = javax.xml.bind.DatatypeConverter.parseDate(fechaStr).getTime();
            }

            List<Servicio> resultados = servicioService.buscarServicios(idCliente, fecha);
            if (resultados.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(resultados).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error al procesar la fecha. Formato esperado: yyyy-MM-dd")
                    .build();
        }
    }

    @GET
    @Path("/{id}/detalles")
    public Response buscarServicioConDetalles(@PathParam("id") Integer id) {
        try {
            Servicio servicio = servicioService.buscarServicioConDetalles(id);
            if (servicio != null) {
                return Response.ok(servicio).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Servicio con ID " + id + " no encontrado.")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    public Response registrarServicio(Servicio servicio) {
        try {
            servicioService.registrarServicio(servicio);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modificarServicio(@PathParam("id") Integer id, Servicio servicioModificado) {
        try {
            Servicio servicioExistente = servicioService.encontrarServicioPorId(id);
            if (servicioExistente != null) {
                servicioModificado.setIdServicio(id);
                servicioService.modificarServicio(servicioModificado);
                return Response.ok(servicioModificado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Servicio con ID " + id + " no encontrado.")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarServicio(@PathParam("id") Integer id) {
        try {
            Servicio servicio = servicioService.encontrarServicioPorId(id);
            if (servicio != null) {
                servicioService.eliminarServicio(servicio);
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Servicio con ID " + id + " no encontrado.")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
