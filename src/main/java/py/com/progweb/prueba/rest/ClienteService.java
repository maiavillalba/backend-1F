package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import java.util.List;

public interface ClienteService {

    // Find All
    public List<Cliente> getAllClientes();

    // Find by ID
    public Cliente getClienteById(Long idCliente);

    // Find by Nombre, Cedula, Telefono, TipoCliente
    public List<Cliente> getClienteByNombre(String nombre);
    public Cliente getClienteByCedula(String cedula);
    public List<Cliente> getClienteByTelefono(String telefono);
    public List<Cliente> getClienteByTipoCliente(String tipoCliente);

    // Insert, Update, Delete
    public void addCliente(Cliente cliente);
    public void updateCliente(Cliente cliente);
    public void deleteCliente(Cliente cliente);
}
