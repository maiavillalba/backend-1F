package py.com.progweb.prueba.rest;

import java.util.List;
import javax.ejb.Local;
import py.com.progweb.prueba.model.Vehiculo;

@Local
public interface VehiculoService {
    // Find All
    public List<Vehiculo> listarVehiculos();
    
    // Find by ID
    public Vehiculo encontrarVehiculoPorId(Integer id);

    // Find by Cliente ID
    public List<Vehiculo> encontrarVehiculosPorClienteId(Integer idCliente);
    
    // Insert, Update, Delete
    public void registrarVehiculo(Vehiculo vehiculo);
    public void modificarVehiculo(Vehiculo vehiculo);
    public void eliminarVehiculo(Vehiculo vehiculo);
}