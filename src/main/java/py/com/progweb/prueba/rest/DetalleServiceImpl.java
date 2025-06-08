package py.com.progweb.prueba.rest;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import py.com.progweb.prueba.ejb.DetalleServicioDAO;
import py.com.progweb.prueba.model.DetalleServicio;

@Stateless
public class DetalleServiceImpl implements DetalleService {

    @Inject
    private DetalleServicioDAO detalleServicioDao;

    @Resource
    private SessionContext sessionContext;

    @Override
    public List<DetalleServicio> listarDetalles() {
        return detalleServicioDao.findAllDetalles();
    }

    @Override
    public DetalleServicio encontrarDetallePorId(Integer idDetalle) {
        return detalleServicioDao.findDetalleById(idDetalle);
    }

    @Override
    public List<DetalleServicio> encontrarDetallesPorServicioId(Integer idServicio) {
        return detalleServicioDao.findDetallesByServicioId(idServicio);
    }

    @Override
    public void registrarDetalle(DetalleServicio detalle) {
        detalleServicioDao.insertDetalle(detalle);
    }

    @Override
    public void modificarDetalle(DetalleServicio detalle) {
        detalleServicioDao.updateDetalle(detalle);
    }

    @Override
    public void eliminarDetalle(DetalleServicio detalle) {
        detalleServicioDao.deleteDetalle(detalle);
    }
}
