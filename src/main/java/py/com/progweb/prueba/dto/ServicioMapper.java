package py.com.progweb.prueba.dto;

import java.util.*;
import java.util.stream.Collectors;
import py.com.progweb.prueba.model.*;

public class ServicioMapper {

    public static ServicioDTO toDTO(Servicio servicio) {
        ServicioDTO dto = new ServicioDTO();
        dto.setId(servicio.getIdServicio());
        dto.setFecha(servicio.getFecha());
        dto.setDescripcion(servicio.getDescripcion());
        dto.setKilometraje(servicio.getKilometraje());
        dto.setCostoTotal(servicio.getCostoTotal());
        dto.setIdVehiculo(servicio.getVehiculo().getIdVehiculo());

        List<DetalleDTO> detallesDTO = new ArrayList<>();
        for (DetalleServicio d : servicio.getDetalles()) {
            DetalleDTO detalle = new DetalleDTO();
            detalle.setId(d.getIdDetalle());
            detalle.setDescripcionTrabajo(d.getDescripcionTrabajo());
            detalle.setCosto(d.getCosto());

            List<String> repuestos = d.getRepuestos().stream()
                .map(r -> r.getNombre())
                .collect(Collectors.toList());
            detalle.setRepuestos(repuestos);

            List<String> mecanicos = d.getMecanicos().stream()
                .map(m -> m.getNombre())
                .collect(Collectors.toList());
            detalle.setMecanicos(mecanicos);

            detallesDTO.add(detalle);
        }
        dto.setDetalles(detallesDTO);

        return dto;
    }
}

