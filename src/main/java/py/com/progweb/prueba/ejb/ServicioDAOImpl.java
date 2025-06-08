package py.com.progweb.prueba.ejb;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;
import java.util.Date;

import py.com.progweb.prueba.model.DetalleServicio;
import py.com.progweb.prueba.model.Servicio;

@Stateless
public class ServicioDAOImpl implements ServicioDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public List<Servicio> findAllServicios() {
        return em.createQuery(
            "SELECT DISTINCT s FROM Servicio s " +
            "LEFT JOIN FETCH s.detalles d", Servicio.class
        ).getResultList();
    }



    @Override
    public Servicio findServicioById(Integer id) {
        try {
            return em.createQuery(
                "SELECT DISTINCT s FROM Servicio s " +
                "LEFT JOIN FETCH s.detalles d " +
                "LEFT JOIN FETCH d.repuestos " +
                "LEFT JOIN FETCH d.mecanicos " +
                "WHERE s.idServicio = :id", Servicio.class)
                .setParameter("id", id)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Servicio> findServiciosByVehiculoId(Integer idVehiculo) {
        return em.createQuery(
            "SELECT DISTINCT s FROM Servicio s " +
            "LEFT JOIN FETCH s.detalles d " +
            "WHERE s.vehiculo.idVehiculo = :idVehiculo", Servicio.class)
            .setParameter("idVehiculo", idVehiculo)
            .getResultList();
    }


    @Override
    public List<Servicio> buscarServicios(Integer idCliente, Date fecha) {
        StringBuilder sb = new StringBuilder(
            "SELECT DISTINCT s FROM Servicio s LEFT JOIN FETCH s.detalles d WHERE 1=1 "
        );
        if (idCliente != null) {
            sb.append("AND s.vehiculo.cliente.idCliente = :idCliente ");
        }
        if (fecha != null) {
            sb.append("AND s.fecha = :fecha ");
        }

        TypedQuery<Servicio> query = em.createQuery(sb.toString(), Servicio.class);

        if (idCliente != null) {
            query.setParameter("idCliente", Long.valueOf(idCliente));
        }
        if (fecha != null) {
            query.setParameter("fecha", fecha, TemporalType.DATE);
        }

        return query.getResultList();
    }


    public Servicio buscarServicioConDetalles(Integer idServicio) {
        return em.createQuery(
            "SELECT s FROM Servicio s LEFT JOIN FETCH s.detalles WHERE s.idServicio = :idServicio", Servicio.class)
            .setParameter("idServicio", idServicio)
            .getSingleResult();
    }


    @Override
    public void insertServicio(Servicio servicio) {
        if (servicio.getDetalles() != null) {
            for (DetalleServicio detalle : servicio.getDetalles()) {
                detalle.setServicio(servicio);
            }
        }
        em.persist(servicio);
    }


    @Override
    public void updateServicio(Servicio servicio) {
        em.merge(servicio);
    }

    @Override
    public void deleteServicio(Servicio servicio) {
        Servicio s = em.find(Servicio.class, servicio.getIdServicio());
        if (s != null) {
            em.remove(s);
        }
    }
}
