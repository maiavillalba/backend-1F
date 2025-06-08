package py.com.progweb.prueba.ejb;

import java.util.List;
import py.com.progweb.prueba.model.Vehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VehiculoDAOImpl implements VehiculoDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public Vehiculo findVehiculoById(Integer id) {
        return em.find(Vehiculo.class, id);
    }

    @Override
    public List<Vehiculo> findAllVehiculos() {
        return em.createNamedQuery("Vehiculo.findAll", Vehiculo.class).getResultList();
    }

    @Override
    public List<Vehiculo> findVehiculoByNumeroChapa(String numeroChapa) {
        List<Vehiculo> vehiculos = em.createNamedQuery("Vehiculo.findByNumeroChapa", Vehiculo.class)
                 .setParameter("numeroChapa", numeroChapa)
                 .getResultList();

        if (vehiculos.isEmpty()) {
            System.out.println("No se encontraron vehiculos con el numero de chapa: " + numeroChapa);
        }

        return vehiculos;
    }

    @Override
    public List<Vehiculo> findVehiculoByClienteId(Integer idCliente) {
        List<Vehiculo> vehiculos = em.createQuery("SELECT v FROM Vehiculo v WHERE v.cliente.idCliente = :idCliente", Vehiculo.class)
                .setParameter("idCliente", idCliente)
                .getResultList();

        if (vehiculos.isEmpty()) {
            System.out.println("No se encontraron vehiculos para el cliente con ID: " + idCliente);
        }

        return vehiculos;
    }

    @Override
    public void insertVehiculo(Vehiculo vehiculo) {
        em.persist(vehiculo);
    }

    @Override
    public void updateVehiculo(Vehiculo vehiculo) {
        em.merge(vehiculo);
    }

    @Override
    public void deleteVehiculo(Vehiculo vehiculo) {
        em.remove(em.merge(vehiculo));
    }
}
