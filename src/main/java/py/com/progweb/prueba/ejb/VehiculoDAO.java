package py.com.progweb.prueba.ejb;

import java.util.List;
import py.com.progweb.prueba.model.Vehiculo;

public interface VehiculoDAO {

    // Find All 
    public List<Vehiculo> findAllVehiculos();

    // Find by ID
    public Vehiculo findVehiculoById(Integer id);
    
    // Find by Numero Chapa
    public List<Vehiculo> findVehiculoByNumeroChapa(String numeroChapa);

    // Find by Cliente ID
    public List<Vehiculo> findVehiculoByClienteId(Integer idCliente);
    
    // Insert, Update, Delete
    public void insertVehiculo(Vehiculo vehiculo);
    public void updateVehiculo(Vehiculo vehiculo);
    public void deleteVehiculo(Vehiculo vehiculo);
}