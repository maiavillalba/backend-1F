package py.com.progweb.prueba.ejb;

import java.util.List;
import py.com.progweb.prueba.model.DetalleServicio;

public interface DetalleServicioDAO {

    // Buscar todos los detalles
    public List<DetalleServicio> findAllDetalles();

    // Buscar detalle por ID
    public DetalleServicio findDetalleById(Integer idDetalle);

    // Buscar detalles por servicio
    public List<DetalleServicio> findDetallesByServicioId(Integer idServicio);

    // Insertar, actualizar, eliminar
    public void insertDetalle(DetalleServicio detalle);
    public void updateDetalle(DetalleServicio detalle);
    public void deleteDetalle(DetalleServicio detalle);
}
