package py.com.progweb.prueba.rest;

import java.util.List;
import javax.ejb.Local;
import py.com.progweb.prueba.model.Repuesto;

@Local
public interface RepuestoService {

    // Find All
    public List<Repuesto> listarRepuestos();

    // Find by ID
    public Repuesto encontrarRepuestoPorId(Integer id);

    // Find by Nombre
    public List<Repuesto> encontrarRepuestoPorNombre(Repuesto repuesto);

    // Find by Codigo
    public List<Repuesto> encontrarRepuestoPorCodigo(Repuesto repuesto);

    // Insert
    public void registrarRepuesto(Repuesto repuesto);

    // Update
    public void modificarRepuesto(Repuesto repuesto);

    // Delete
    public void eliminarRepuesto(Repuesto repuesto);
}
