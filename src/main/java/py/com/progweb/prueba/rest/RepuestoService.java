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

    // Find by Nombre, Codigo
    public List<Repuesto> encontrarRepuestoPorNombre(Repuesto repuesto);
    public List<Repuesto> encontrarRepuestoPorCodigo(Repuesto repuesto);

    // Insert, Update, Delete
    public void registrarRepuesto(Repuesto repuesto);
    public void modificarRepuesto(Repuesto repuesto);
    public void eliminarRepuesto(Repuesto repuesto);
}
