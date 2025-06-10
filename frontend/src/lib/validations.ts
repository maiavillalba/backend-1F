import { z } from "zod"

export const servicioSchema = z.object({
  fecha: z.string().min(1, "La fecha es requerida"),
  descripcion: z.string().min(1, "La descripción es requerida"),
  kilometraje: z.number().min(0, "El kilometraje debe ser mayor a 0"),
  costoTotal: z.number().min(0, "El costo total debe ser mayor a 0"),
  vehiculoId: z.number().min(1, "Debe seleccionar un vehículo"),
  detalles: z
    .array(
      z.object({
        descripcionTrabajo: z.string().min(1, "La descripción del trabajo es requerida"),
        costo: z.number().min(0, "El costo debe ser mayor a 0"),
        repuestosIds: z.array(z.number()).min(1, "Debe seleccionar al menos un repuesto"),
        mecanicosIds: z.array(z.number()).min(1, "Debe seleccionar al menos un mecánico"),
      }),
    )
    .min(1, "Debe agregar al menos un detalle"),
})

export type ServicioFormData = z.infer<typeof servicioSchema>
