package py.com.progweb.prueba.dto;

import java.util.List;

public class DetalleDTO {
    private Integer id;
    private String descripcionTrabajo;
    private Double costo;
    private List<String> repuestos;
    private List<String> mecanicos;

    public DetalleDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<String> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<String> repuestos) {
        this.repuestos = repuestos;
    }

    public List<String> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<String> mecanicos) {
        this.mecanicos = mecanicos;
    }
}
