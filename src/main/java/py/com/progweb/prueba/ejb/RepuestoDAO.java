package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Repuesto;
import java.util.List;

public interface RepuestoDAO {
    
    // Find All Repuestos
    public List<Repuesto> findAllRepuestos();

    // Find by ID
    public Repuesto findRepuestoById(Integer id);

    // Find by Nombre, Codigo
    public List<Repuesto> findRepuestoByNombre(Repuesto repuesto);
    public List<Repuesto> findRepuestoByCodigo(Repuesto repuesto);

    // Insert, Update, Delete
    public void insertRepuesto(Repuesto repuesto);
    public void updateRepuesto(Repuesto repuesto);
    public void deleteRepuesto(Repuesto repuesto);
}
