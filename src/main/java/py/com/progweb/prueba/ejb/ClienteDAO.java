package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;
import java.util.List;

public interface ClienteDAO {

    // Find All Clientes
    List<Cliente> findAllClientes();

    // Find by ID
    Cliente findClienteById(Cliente cliente);

    // Find by Nombre, Cedula, Telefono, TipoCliente
    List<Cliente> findClienteByNombre(Cliente cliente);
    Cliente findClienteByCedula(Cliente cliente);
    List<Cliente> findClienteByTelefono(Cliente cliente);
    List<Cliente> findClienteByTipoCliente(Cliente cliente);

    // Insert, Update, Delete
    void insertCliente(Cliente cliente);
    void updateCliente(Cliente cliente);
    void deleteCliente(Cliente cliente);
}
