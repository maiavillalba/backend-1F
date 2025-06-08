package py.com.progweb.prueba.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.progweb.prueba.model.Repuesto;

@Stateless
public class RepuestoDAOImpl implements RepuestoDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public List<Repuesto> findAllRepuestos() {
        return em.createNamedQuery("Repuesto.findAll", Repuesto.class).getResultList();
    }

    @Override
    public Repuesto findRepuestoById(Integer id) {
        Repuesto repuesto = em.find(Repuesto.class, id);
        if (repuesto == null) {
            System.out.println("No se encontró el repuesto con ID: " + id);
        }
        return repuesto;
    }

    @Override
    public List<Repuesto> findRepuestoByNombre(Repuesto repuesto) {
        List<Repuesto> repuestos = em.createNamedQuery("Repuesto.findByNombre", Repuesto.class)
                .setParameter("nombre", repuesto.getNombre())
                .getResultList();
        
        if (repuestos.isEmpty()) {
            System.out.println("No se encontraron repuestos con el nombre: " + repuesto.getNombre());
        }
        return repuestos;
    }

    @Override
    public List<Repuesto> findRepuestoByCodigo(Repuesto repuesto) {
        List<Repuesto> repuestos = em.createNamedQuery("Repuesto.findByCodigo", Repuesto.class)
                .setParameter("codigo", repuesto.getCodigo())
                .getResultList();
        
        if (repuestos.isEmpty()) {
            System.out.println("No se encontraron repuestos con el código: " + repuesto.getCodigo());
        }
        return repuestos;
    }

    @Override
    public void insertRepuesto(Repuesto repuesto) {
        em.persist(repuesto);
    }

    @Override
    public void updateRepuesto(Repuesto repuesto) {
        em.merge(repuesto);
    }

    @Override
    public void deleteRepuesto(Repuesto repuesto) {
        em.remove(em.merge(repuesto));
    }
    
}
