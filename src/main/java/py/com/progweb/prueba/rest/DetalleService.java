package py.com.progweb.prueba.rest;

import java.util.List;
import javax.ejb.Local;
import py.com.progweb.prueba.model.DetalleServicio;

@Local
public interface DetalleService {

    // Find all
    public List<DetalleServicio> listarDetalles();

    // Find by ID
    public DetalleServicio encontrarDetallePorId(Integer idDetalle);

    // Find by Servicio ID
    public List<DetalleServicio> encontrarDetallesPorServicioId(Integer idServicio);

    // Insert, Update, Delete
    public void registrarDetalle(DetalleServicio detalle);
    public void modificarDetalle(DetalleServicio detalle);
    public void eliminarDetalle(DetalleServicio detalle);
}
