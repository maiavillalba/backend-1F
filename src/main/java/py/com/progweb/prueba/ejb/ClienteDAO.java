package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;
import java.util.List;

public interface ClienteDAO {

    List<Cliente> findAllClientes();
    Cliente findClienteById(Cliente cliente);
    List<Cliente> findClienteByNombre(Cliente cliente);
    Cliente findClienteByCedula(Cliente cliente);
    List<Cliente> findClienteByTelefono(Cliente cliente);
    List<Cliente> findClienteByTipoCliente(Cliente cliente);

    void insertCliente(Cliente cliente);
    void updateCliente(Cliente cliente);
    void deleteCliente(Cliente cliente);
}
