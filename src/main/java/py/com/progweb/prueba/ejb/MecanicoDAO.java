package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Mecanico;
import java.util.List;

public interface MecanicoDAO {
    List<Mecanico> findAll();
    Mecanico findById(Long id);
    List<Mecanico> findByNombre(String nombre);
    void insert(Mecanico mecanico);
    void update(Mecanico mecanico);
    void delete(Mecanico mecanico);
}
