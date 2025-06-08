<<<<<<< HEAD
package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.dto.VentaCabeceraDTO;
=======
/* package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.dto.VentaCabeceraDTO;
import py.com.progweb.prueba.dto.VentaDetalleDTO;
>>>>>>> respaldo-initial
import py.com.progweb.prueba.model.VentaDetalle;

import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ejb.EJB;
<<<<<<< HEAD
=======
import py.com.progweb.prueba.utils.Mail;
>>>>>>> respaldo-initial

@Path("/ventas")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class VentaServiceRS {

    @EJB
    private VentaService ventaService;
    @EJB
    private ClienteService clienteService;
<<<<<<< HEAD
=======

>>>>>>> respaldo-initial
    @POST
    @Path("/realizarVenta/{clienteid}")
    public Response realizarVenta(@PathParam("clienteid") Long clienteId, List<VentaDetalle> detalles) {
        try {
            Cliente cliente = clienteService.getClienteById(clienteId);
            if (cliente == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Cliente no encontrado").build();
            }

            ventaService.realizarVenta(cliente, detalles);
<<<<<<< HEAD
=======
            String message = "Hola " + cliente.getNombre() + " " + cliente.getApellido() +
                    ",\n\nGracias por su compra. " +
                    ".\n\nSaludos,\nSu tienda";
            Mail.enviarCorreo(cliente.getEmail(), "Confirmacion de compra", message);
>>>>>>> respaldo-initial
            return Response.ok("Venta registrada correctamente").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
<<<<<<< HEAD
    @Path("/all")
    public List<VentaCabeceraDTO> listarVentas(@QueryParam("fecha") String fecha,
            @QueryParam("clienteId") Long clienteId) {
        return ventaService.listarVentas(fecha, clienteId);
    }
}
=======
    @Path("/listarVentas")
    public Response listarVentas(@QueryParam("fecha") String fecha,
            @QueryParam("clienteId") Long clienteId) {
        List<VentaCabeceraDTO> ventas = ventaService.listarVentas(fecha, clienteId);
        if (ventas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron ventas registradas.")
                    .build();
        }

        return Response.ok(ventas).build();
    }
    @GET
    @Path("/detalleVenta/{idVenta}")
    public Response listarDetallesVenta(@PathParam("idVenta") Long idVenta) {
        List<VentaDetalleDTO> detalles = ventaService.listarDetallesVenta(idVenta);
        if (detalles.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron detalles de la venta.")
                    .build();
        }

        return Response.ok(detalles).build();
    }
}
 */
>>>>>>> respaldo-initial
