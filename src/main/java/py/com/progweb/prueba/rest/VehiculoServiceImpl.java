package py.com.progweb.prueba.rest;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.progweb.prueba.ejb.VehiculoDAO;
import py.com.progweb.prueba.model.Vehiculo;

@Stateless
public class VehiculoServiceImpl implements VehiculoService {

    @Inject
    private VehiculoDAO vehiculoDao;

    @Resource
    private SessionContext sessionContext;

    @Override
    public List<Vehiculo> listarVehiculos() {
        return vehiculoDao.findAllVehiculos();
    }

    @Override
    public Vehiculo encontrarVehiculoPorId(Integer id) {
        return vehiculoDao.findVehiculoById(id);
    }

    @Override
    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculoDao.insertVehiculo(vehiculo);
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) {
        vehiculoDao.updateVehiculo(vehiculo);
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) {
        vehiculoDao.deleteVehiculo(vehiculo);
    }
}