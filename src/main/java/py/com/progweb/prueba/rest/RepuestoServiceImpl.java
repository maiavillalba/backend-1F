package py.com.progweb.prueba.rest;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.progweb.prueba.ejb.RepuestoDAO;
import py.com.progweb.prueba.model.Repuesto;

@Stateless
public class RepuestoServiceImpl implements RepuestoService {

    @Inject
    private RepuestoDAO repuestoDAO;

    @Resource
    private SessionContext sessionContext;

    public List<Repuesto> listarRepuestos() {
        return repuestoDAO.findAllRepuestos();
    }

    public Repuesto encontrarRepuestoPorId(Integer id) {
        return repuestoDAO.findRepuestoById(id);
    }

    public List<Repuesto> encontrarRepuestoPorNombre(Repuesto repuesto) {
        return repuestoDAO.findRepuestoByNombre(repuesto);
    }

    public List<Repuesto> encontrarRepuestoPorCodigo(Repuesto repuesto) {
        return repuestoDAO.findRepuestoByCodigo(repuesto);
    }

    public void registrarRepuesto(Repuesto repuesto) {
        repuestoDAO.insertRepuesto(repuesto);
    }

    public void modificarRepuesto(Repuesto repuesto) {
        repuestoDAO.updateRepuesto(repuesto);
    }

    public void eliminarRepuesto(Repuesto repuesto) {
        repuestoDAO.deleteRepuesto(repuesto);
    }
    
}
