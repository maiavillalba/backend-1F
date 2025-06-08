package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ejb.EJB;

@Path("/clientes")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class ClienteServiceRS {

    @EJB
    private ClienteService clienteService;

    @GET
    @Path("/all")
    public Response getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        if (clientes == null || clientes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No existe una lista de clientes")
                    .build();
        }
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{id}")
    public Response getClienteById(@PathParam("id") Long idCliente) {
        Cliente cliente = clienteService.getClienteById(idCliente);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente con id " + idCliente + " no encontrado")
                    .build();
        }
        return Response.ok(cliente).build();
    }

    @POST
    @Path("/add")
    public Response addCliente(Cliente cliente) {
        try {
            clienteService.addCliente(cliente);
            return Response.ok().entity(cliente).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear el cliente").build();
        }
    }

    @PUT
    @Path("/update/{id}")
    public Response updateCliente(@PathParam("id") Long idCliente, Cliente clienteModificado) {
        try {
            Cliente clienteExistente = clienteService.getClienteById(idCliente);
            if (clienteExistente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Cliente con ID " + idCliente + " no encontrado").build();
            }

            // Actualizar solo los campos no nulos
            if (clienteModificado.getNombre() != null) {
                clienteExistente.setNombre(clienteModificado.getNombre());
            }
            if (clienteModificado.getCedula() != null) {
                clienteExistente.setCedula(clienteModificado.getCedula());
            }
            if(clienteModificado.getDireccion() != null) {
                clienteExistente.setDireccion(clienteModificado.getDireccion());
            }
            if (clienteModificado.getTelefono() != null) {
                clienteExistente.setTelefono(clienteModificado.getTelefono());
            }
            if (clienteModificado.getTipo_cliente() != null) {
                clienteExistente.setTipo_cliente(clienteModificado.getTipo_cliente());
            }

            clienteService.updateCliente(clienteExistente);
            return Response.ok(clienteExistente).build();

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el cliente")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteCliente(@PathParam("id") Long idCliente) {
        try {
            Cliente cliente = clienteService.getClienteById(idCliente);
            if (cliente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Cliente con ID " + idCliente + " no encontrado.")
                        .build();
            }

            clienteService.deleteCliente(cliente);
            String mensaje = "Cliente " + cliente.getNombre() + " eliminado correctamente.";
            return Response.status(Response.Status.OK)
                    .entity(mensaje)
                    .build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar el cliente")
                    .build();
        }
    }

    
    @GET
    @Path("/nombre/{nombre}")
    public Response getClienteByNombre(@PathParam("nombre") String nombre) {
        return Response.ok(clienteService.getClienteByNombre(nombre)).build();
    }

    @GET
    @Path("/cedula/{cedula}")
    public Response getClienteByCedula(@PathParam("cedula") String cedula) {
        return Response.ok(clienteService.getClienteByCedula(cedula)).build();
    }

    @GET
    @Path("/telefono/{telefono}")
    public Response getClienteByTelefono(@PathParam("telefono") String telefono) {
        return Response.ok(clienteService.getClienteByTelefono(telefono)).build();
    }

    @GET
    @Path("/tipo/{tipo}")
    public Response getClienteByTipoCliente(@PathParam("tipo") String tipoCliente) {
        return Response.ok(clienteService.getClienteByTipoCliente(tipoCliente)).build();
    }
}
