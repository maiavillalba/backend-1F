package py.com.progweb.prueba.ejb;

import java.util.List;
import java.util.Date;
import py.com.progweb.prueba.model.Servicio;

public interface ServicioDAO {

    // Find all
    public List<Servicio> findAllServicios();

    // Find by ID
    public Servicio findServicioById(Integer id);

    // Find by Vehiculo ID
    public List<Servicio> findServiciosByVehiculoId(Integer idVehiculo);

    // Filtro combinado (cliente y/o fecha)
    public List<Servicio> buscarServicios(Integer idCliente, Date fecha);

    // Buscar servicio con detalles
    public Servicio buscarServicioConDetalles(Integer idServicio);

    // Insert, Update, Delete
    public void insertServicio(Servicio servicio);
    public void updateServicio(Servicio servicio);
    public void deleteServicio(Servicio servicio);
}
