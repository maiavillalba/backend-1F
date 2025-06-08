package py.com.progweb.prueba.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Date;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "mecanico")
@JsonRootName(value = "mecanico")
@NamedQueries({
    @NamedQuery(name = "Mecanico.findAll", query = "SELECT m FROM Mecanico m"),
    @NamedQuery(name = "Mecanico.findByNombre", query = "SELECT m FROM Mecanico m WHERE m.nombre = :nombre")
})
public class Mecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mecanico")
    private Long idMecanico;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "especialidad", length = 100)
    private String especialidad;

    public Mecanico() {}

    // Getters y setters

    public Long getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(Long idMecanico) {
        this.idMecanico = idMecanico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
