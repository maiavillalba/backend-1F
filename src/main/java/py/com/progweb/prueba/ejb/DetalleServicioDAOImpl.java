package py.com.progweb.prueba.ejb;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

import py.com.progweb.prueba.model.DetalleServicio;

@Stateless
public class DetalleServicioDAOImpl implements DetalleServicioDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public List<DetalleServicio> findAllDetalles() {
        List<DetalleServicio> detalleServicios = em.createNamedQuery("DetalleServicio.findAll", DetalleServicio.class).getResultList();
        if (detalleServicios == null || detalleServicios.isEmpty()) {
            System.out.println("No se encontraron detalles de servicio.");
        }
        return detalleServicios;
    }

    @Override
    public DetalleServicio findDetalleById(Integer idDetalle) {
        DetalleServicio detalle = em.find(DetalleServicio.class, idDetalle);
        if (detalle == null) {
            System.out.println("No se encontró el detalle de servicio con ID: " + idDetalle);
        }
        return detalle;
    }

    @Override
    public List<DetalleServicio> findDetallesByServicioId(Integer idServicio) {
        List<DetalleServicio> detalles = em.createNamedQuery("DetalleServicio.findByServicio", DetalleServicio.class)
                 .setParameter("idServicio", idServicio)
                 .getResultList();
        if (detalles == null || detalles.isEmpty()) {
            System.out.println("No se encontraron detalles de servicio para el servicio con ID: " + idServicio);
        }
        return detalles;
    }

    @Override
    public void insertDetalle(DetalleServicio detalle) {
        em.persist(detalle);
    }

    @Override
    public void updateDetalle(DetalleServicio detalle) {
        em.merge(detalle);
    }

    @Override
    public void deleteDetalle(DetalleServicio detalle) {
        DetalleServicio d = em.find(DetalleServicio.class, detalle.getIdDetalle());
        if (d != null) {
            em.remove(d);
        }
    }
}
