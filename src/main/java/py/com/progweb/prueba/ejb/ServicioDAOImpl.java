package py.com.progweb.prueba.ejb;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;
import java.util.Date;

import py.com.progweb.prueba.model.Servicio;

@Stateless
public class ServicioDAOImpl implements ServicioDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public List<Servicio> findAllServicios() {
        return em.createNamedQuery("Servicio.findAll", Servicio.class).getResultList();
    }

    @Override
    public Servicio findServicioById(Integer id) {
        return em.find(Servicio.class, id);
    }

    @Override
    public List<Servicio> findServiciosByVehiculoId(Integer idVehiculo) {
        List<Servicio> servicios = em.createNamedQuery("Servicio.findByVehiculo", Servicio.class)
                 .setParameter("idVehiculo", idVehiculo)
                 .getResultList();

        if (servicios.isEmpty()) {
            System.out.println("No se encontraron servicios para el vehiculo con ID: " + idVehiculo);
        }
        return servicios;
    }

    @Override
    public List<Servicio> buscarServicios(Integer idCliente, Date fecha) {
        StringBuilder sb = new StringBuilder("SELECT s FROM Servicio s WHERE 1=1 ");
        if (idCliente != null) {
            sb.append("AND s.vehiculo.cliente.idCliente = :idCliente ");
        }
        if (fecha != null) {
            sb.append("AND s.fecha = :fecha ");
        }

        TypedQuery<Servicio> query = em.createQuery(sb.toString(), Servicio.class);

        if (idCliente != null) {
            query.setParameter("idCliente", idCliente);
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
