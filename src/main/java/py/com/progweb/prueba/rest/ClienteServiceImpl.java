package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.ejb.ClienteDAO;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import java.util.List;

@Stateless
public class ClienteServiceImpl implements ClienteService {

    @EJB
    private ClienteDAO clienteDAO;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteDAO.findAllClientes();
    }

    @Override
    public Cliente getClienteById(Long idCliente) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        return clienteDAO.findClienteById(cliente);
    }

    @Override
    public List<Cliente> getClienteByNombre(String nombre) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        return clienteDAO.findClienteByNombre(cliente);
    }

    @Override
    public Cliente getClienteByCedula(String cedula) {
        Cliente cliente = new Cliente();
        cliente.setCedula(cedula);
        return clienteDAO.findClienteByCedula(cliente);
    }

    @Override
    public List<Cliente> getClienteByTelefono(String telefono) {
        Cliente cliente = new Cliente();
        cliente.setTelefono(telefono);
        return clienteDAO.findClienteByTelefono(cliente);
    }

    @Override
    public List<Cliente> getClienteByTipoCliente(String tipoCliente) {
        Cliente cliente = new Cliente();
        cliente.setTipo_cliente(tipoCliente);
        return clienteDAO.findClienteByTipoCliente(cliente);
    }

    @Override
    public void addCliente(Cliente cliente) {
        clienteDAO.insertCliente(cliente);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        clienteDAO.updateCliente(cliente);
    }

    @Override
    public void deleteCliente(Cliente cliente) {
        clienteDAO.deleteCliente(cliente);
    }
}
