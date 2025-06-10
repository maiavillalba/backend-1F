import axios from "axios"
import type { ServicioPayload, Vehiculo, Repuesto, Mecanico, Cliente } from "../types"

const api = axios.create({
  baseURL: "http://localhost:8080/prueba",
  headers: {
    "Content-Type": "application/json",
  },
})

export const vehiculoService = {
  getAll: async (): Promise<Vehiculo[]> => {
    const response = await api.get("/vehiculos")
    return response.data
  },
}

export const repuestoService = {
  getAll: async (): Promise<Repuesto[]> => {
    const response = await api.get("/repuestos")
    return response.data
  },
}

export const mecanicoService = {
  getAll: async (): Promise<Mecanico[]> => {
    const response = await api.get("/mecanicos")
    return response.data
  },
}

export const clienteService = {
  getAll: async (): Promise<Cliente[]> => {
    const response = await api.get("/clientes/all")
    return response.data
  },
}

export const servicioService = {
  create: async (servicio: ServicioPayload): Promise<any> => {
    const response = await api.post("/servicios/", servicio)
    return response.data
  },
  getAll: async (): Promise<any[]> => {
    const response = await api.get("/servicios")
    return response.data
  },
  getByFilters: async (filtros: {
    idCliente?: number
    fecha?: string
  }): Promise<any[]> => {
    const params = new URLSearchParams()

    if (filtros.idCliente) params.append("idCliente", filtros.idCliente.toString())
    if (filtros.fecha) params.append("fecha", filtros.fecha)

    const response = await api.get(`/servicios/filtros?${params.toString()}`)
    return response.data
  },
}
