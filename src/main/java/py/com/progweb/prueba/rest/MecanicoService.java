package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Mecanico;
import java.util.List;

public interface MecanicoService {
    List<Mecanico> getAll();
    Mecanico getById(Long id);
    List<Mecanico> getByNombre(String nombre);
    void add(Mecanico mecanico);
    void update(Mecanico mecanico);
    void delete(Mecanico mecanico);
}
