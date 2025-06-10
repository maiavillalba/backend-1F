import { useQuery } from "@tanstack/react-query"
import { vehiculoService, repuestoService, mecanicoService, clienteService } from "../services/api"

export const useVehiculos = () => {
  return useQuery({
    queryKey: ["vehiculos"],
    queryFn: vehiculoService.getAll,
  })
}

export const useRepuestos = () => {
  return useQuery({
    queryKey: ["repuestos"],
    queryFn: repuestoService.getAll,
  })
}

export const useMecanicos = () => {
  return useQuery({
    queryKey: ["mecanicos"],
    queryFn: mecanicoService.getAll,
  })
}

export const useClientes = () => {
  return useQuery({
    queryKey: ["clientes"],
    queryFn: clienteService.getAll,
  })
}
