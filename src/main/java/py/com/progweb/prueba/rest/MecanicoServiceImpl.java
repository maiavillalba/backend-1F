package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Mecanico;
import py.com.progweb.prueba.ejb.MecanicoDAO;

import javax.ejb.Stateless;
import javax.ejb.EJB;
import java.util.List;

@Stateless
public class MecanicoServiceImpl implements MecanicoService {

    @EJB
    private MecanicoDAO mecanicoDAO;

    public List<Mecanico> getAll() {
        return mecanicoDAO.findAll();
    }

    public Mecanico getById(Long id) {
        return mecanicoDAO.findById(id);
    }

    public List<Mecanico> getByNombre(String nombre) {
        return mecanicoDAO.findByNombre(nombre);
    }

    public void add(Mecanico mecanico) {
        mecanicoDAO.insert(mecanico);
    }

    public void update(Mecanico mecanico) {
        mecanicoDAO.update(mecanico);
    }

    public void delete(Mecanico mecanico) {
        mecanicoDAO.delete(mecanico);
    }
}
