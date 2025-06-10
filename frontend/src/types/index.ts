export interface Vehiculo {
  idVehiculo: number
  marca?: string
  modelo?: string
  patente?: string
  cliente?: string
}

export interface Repuesto {
  idRepuesto: number
  nombre?: string
  precio?: number
}

export interface Mecanico {
  idMecanico: number
  nombre?: string
  especialidad?: string
}

export interface Cliente {
  idCliente: number
  nombre: string
  direccion: string
  telefono: string
  cedula: string
  tipo_cliente: string
}

export interface ServicioDetalle {
  descripcionTrabajo: string
  costo: number
  repuestos: { idRepuesto: number }[]
  mecanicos: { idMecanico: number }[]
}

export interface ServicioPayload {
  fecha: string
  descripcion: string
  kilometraje: number
  costoTotal: number
  vehiculo: { idVehiculo: number }
  detalles: ServicioDetalle[]
}

export interface ServicioFormData {
  fecha: string
  descripcion: string
  kilometraje: number
  costoTotal: number
  vehiculoId: number
  detalles: {
    descripcionTrabajo: string
    costo: number
    repuestosIds: number[]
    mecanicosIds: number[]
  }[]
}

// API Response types (what we actually receive)
export interface ServicioDetalleResponse {
  id: number
  descripcionTrabajo: string
  costo: number
  repuestos: string[] // Array of repuesto names
  mecanicos: string[] // Array of mecanico names
}

export interface ServicioResponse {
  id: number
  fecha: string
  descripcion: string
  kilometraje: number
  costoTotal: number
  idVehiculo: number
  detalles: ServicioDetalleResponse[]
}
