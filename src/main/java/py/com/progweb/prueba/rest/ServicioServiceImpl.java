package py.com.progweb.prueba.rest;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import py.com.progweb.prueba.ejb.ServicioDAO;
import py.com.progweb.prueba.model.Servicio;

@Stateless
public class ServicioServiceImpl implements ServicioService {

    @Inject
    private ServicioDAO servicioDao;

    @Resource
    private SessionContext sessionContext;

    @Override
    public List<Servicio> listarServicios() {
        return servicioDao.findAllServicios();
    }

    @Override
    public Servicio encontrarServicioPorId(Integer id) {
        return servicioDao.findServicioById(id);
    }

    @Override
    public List<Servicio> encontrarServiciosPorVehiculoId(Integer idVehiculo) {
        return servicioDao.findServiciosByVehiculoId(idVehiculo);
    }

    @Override
    public List<Servicio> buscarServicios(Integer idCliente, Date fecha) {
        return servicioDao.buscarServicios(idCliente, fecha);
    }

    @Override
    public Servicio buscarServicioConDetalles(Integer idServicio) {
        return servicioDao.buscarServicioConDetalles(idServicio);
    }

    @Override
    public void registrarServicio(Servicio servicio) {
        servicioDao.insertServicio(servicio);
    }

    @Override
    public void modificarServicio(Servicio servicio) {
        servicioDao.updateServicio(servicio);
    }

    @Override
    public void eliminarServicio(Servicio servicio) {
        servicioDao.deleteServicio(servicio);
    }
}
