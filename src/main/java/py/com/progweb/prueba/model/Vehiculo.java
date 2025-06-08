package py.com.progweb.prueba.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name = "vehiculo")
@JsonRootName(value = "vehiculo")
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByIdVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "Vehiculo.findByNumeroChapa", query = "SELECT v FROM Vehiculo v WHERE v.numeroChapa = :numeroChapa"),
    @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca")
})
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVehiculo", length = 50)
    private Integer idVehiculo;

    @Column(name = "marca", length = 100)
    private String marca;

    @Column(name = "numero_chapa", length = 50, unique = true)
    private String numeroChapa;

    @Column(name = "modelo", length = 100)
    private String modelo;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "tipo", length = 50)
    private String tipo;

    public Vehiculo() {}

    public Vehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    
    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumeroChapa() {
        return numeroChapa;
    }

    public void setNumeroChapa(String numeroChapa) {
        this.numeroChapa = numeroChapa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
