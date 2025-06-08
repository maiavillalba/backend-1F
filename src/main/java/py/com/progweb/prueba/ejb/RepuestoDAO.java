package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Repuesto;
import java.util.List;

public interface RepuestoDAO {
    
    // Find All Repuestos
    public List<Repuesto> findAllRepuestos();

    // Find by ID
    public Repuesto findRepuestoById(Integer id);

    // Find by Nombre 
    public List<Repuesto> findRepuestoByNombre(Repuesto repuesto);

    // Find by Codigo
    public List<Repuesto> findRepuestoByCodigo(Repuesto repuesto);

    // Insert
    public void insertRepuesto(Repuesto repuesto);

    // Update
    public void updateRepuesto(Repuesto repuesto);

    // Delete
    public void deleteRepuesto(Repuesto repuesto);
}
