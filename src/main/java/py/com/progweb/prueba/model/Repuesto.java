package py.com.progweb.prueba.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name = "repuesto")
@JsonRootName(value = "repuesto")
@NamedQueries({
    @NamedQuery(name = "Repuesto.findAll", query = "SELECT r FROM Repuesto r"),
    @NamedQuery(name = "Repuesto.findByIdRepuesto", query = "SELECT r FROM Repuesto r WHERE r.idRepuesto = :idRepuesto"),
    @NamedQuery(name = "Repuesto.findByNombre", query = "SELECT r FROM Repuesto r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Repuesto.findByCodigo", query = "SELECT r FROM Repuesto r WHERE r.codigo = :codigo")
})
public class Repuesto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_repuesto", length = 50)
    private Integer idRepuesto;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "codigo", length = 50, unique = true, nullable = false)
    private String codigo;

    public Repuesto() {}

    public Repuesto(Integer idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public Integer getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(Integer idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Repuesto)) {
            return false;
        }
        Repuesto other = (Repuesto) object;
        if ((this.idRepuesto == null && other.idRepuesto != null) || (this.idRepuesto != null && !this.idRepuesto.equals(other.idRepuesto))) {
            return false;
        }
        return true;
    }
}
