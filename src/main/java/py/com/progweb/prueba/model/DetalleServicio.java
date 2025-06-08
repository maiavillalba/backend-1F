package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name = "detalle_servicio")
@JsonRootName(value = "detalle_servicio")
@NamedQueries({
    @NamedQuery(name = "DetalleServicio.findAll", query = "SELECT d FROM DetalleServicio d"),
    @NamedQuery(name = "DetalleServicio.findByServicio", query = "SELECT d FROM DetalleServicio d WHERE d.servicio.idServicio = :idServicio")
})
public class DetalleServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @Column(name = "descripcion_trabajo", length = 255)
    private String descripcionTrabajo;

    @Column(name = "costo")
    private Double costo;

    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    @JsonBackReference
    private Servicio servicio;

    @ManyToMany
    @JoinTable(
        name = "detalle_repuesto",
        joinColumns = @JoinColumn(name = "id_detalle"),
        inverseJoinColumns = @JoinColumn(name = "id_repuesto")
    )
    private List<Repuesto> repuestos;

    @ManyToMany
    @JoinTable(
        name = "detalle_mecanico",
        joinColumns = @JoinColumn(name = "id_detalle"),
        inverseJoinColumns = @JoinColumn(name = "id_mecanico")
    )
    private List<Mecanico> mecanicos;

    public DetalleServicio() {}

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public List<Repuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<Repuesto> repuestos) {
        this.repuestos = repuestos;
    }

    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DetalleServicio)) {
            return false;
        }
        DetalleServicio other = (DetalleServicio) object;
        return (this.idDetalle != null && this.idDetalle.equals(other.idDetalle));
    }
}
