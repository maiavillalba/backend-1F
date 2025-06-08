package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Mecanico;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class MecanicoDAOImpl implements MecanicoDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public List<Mecanico> findAll() {
        return em.createNamedQuery("Mecanico.findAll", Mecanico.class).getResultList();
    }

    @Override
    public Mecanico findById(Long id) {
        return em.find(Mecanico.class, id);
    }

    @Override
    public List<Mecanico> findByNombre(String nombre) {
        return em.createNamedQuery("Mecanico.findByNombre", Mecanico.class)
                 .setParameter("nombre", nombre).getResultList();
    }

    @Override
    public void insert(Mecanico mecanico) {
        em.persist(mecanico);
    }

    @Override
    public void update(Mecanico mecanico) {
        em.merge(mecanico);
    }

    @Override
    public void delete(Mecanico mecanico) {
        em.remove(em.merge(mecanico));
    }
}
