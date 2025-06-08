package py.com.progweb.prueba.rest;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import py.com.progweb.prueba.model.Servicio;

@Local
public interface ServicioService {

    // Find All
    public List<Servicio> listarServicios();

    // Find by ID
    public Servicio encontrarServicioPorId(Integer id);

    // Find by Vehiculo ID
    public List<Servicio> encontrarServiciosPorVehiculoId(Integer idVehiculo);

    // Buscar servicios con filtros de cliente y fecha (ambos opcionales)
    public List<Servicio> buscarServicios(Integer idCliente, Date fecha);

    // Buscar servicio con detalles
    public Servicio buscarServicioConDetalles(Integer idServicio);

    // Insert, Update, Delete
    public void registrarServicio(Servicio servicio);
    public void modificarServicio(Servicio servicio);
    public void eliminarServicio(Servicio servicio);
}
