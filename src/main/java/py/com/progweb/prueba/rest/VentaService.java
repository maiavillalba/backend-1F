package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.VentaDetalle;
import py.com.progweb.prueba.dto.VentaCabeceraDTO;
<<<<<<< HEAD
=======
import py.com.progweb.prueba.dto.VentaDetalleDTO;
>>>>>>> respaldo-initial

import java.util.List;

public interface VentaService {
    public List<VentaCabeceraDTO> listarVentas(String fecha, Long clienteId);
<<<<<<< HEAD
=======
    public List<VentaDetalleDTO> listarDetallesVenta(Long idVenta);
>>>>>>> respaldo-initial
    public void realizarVenta(Cliente cliente, List<VentaDetalle> detalles) throws Exception;
}
