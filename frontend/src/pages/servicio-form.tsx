import { useState } from "react"
import { useForm, useFieldArray } from "react-hook-form"
import { zodResolver } from "@hookform/resolvers/zod"
import { useMutation, useQueryClient } from "@tanstack/react-query"
import { Plus, Trash2, Loader2 } from "lucide-react"
import { toast } from "sonner"

import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Separator } from "@/components/ui/separator"
import { Badge } from "@/components/ui/badge"

import { servicioSchema, type ServicioFormData } from "../lib/validations"
import { useVehiculos, useRepuestos, useMecanicos } from "../hooks/useApiData"
import { servicioService } from "../services/api"
import type { ServicioPayload } from "../types"

export default function ServicioForm() {
  const queryClient = useQueryClient()
  const [selectedRepuestos, setSelectedRepuestos] = useState<{ [key: number]: number[] }>({})
  const [selectedMecanicos, setSelectedMecanicos] = useState<{ [key: number]: number[] }>({})

  const { data: vehiculos, isLoading: loadingVehiculos } = useVehiculos()
  const { data: repuestos, isLoading: loadingRepuestos } = useRepuestos()
  const { data: mecanicos, isLoading: loadingMecanicos } = useMecanicos()

  const form = useForm<ServicioFormData>({
    resolver: zodResolver(servicioSchema),
    defaultValues: {
      fecha: new Date().toISOString().split("T")[0],
      descripcion: "",
      kilometraje: undefined,
      costoTotal: undefined,
      vehiculoId: undefined,
      detalles: [
        {
          descripcionTrabajo: "",
          costo: undefined,
          repuestosIds: [],
          mecanicosIds: [],
        },
      ],
    },
  })

  const { fields, append, remove } = useFieldArray({
    control: form.control,
    name: "detalles",
  })

  const createServicioMutation = useMutation({
    mutationFn: (data: ServicioPayload) => servicioService.create(data),
    onSuccess: () => {
      toast.success("Servicio creado exitosamente")
      queryClient.invalidateQueries({ queryKey: ["servicios"] })
      form.reset()
      setSelectedRepuestos({})
      setSelectedMecanicos({})
    },
    onError: (error) => {
      toast.error("Error al crear el servicio")
      console.error("Error:", error)
    },
  })

  const onSubmit = (data: ServicioFormData) => {
    const payload: ServicioPayload = {
      fecha: data.fecha,
      descripcion: data.descripcion,
      kilometraje: data.kilometraje,
      costoTotal: data.costoTotal,
      vehiculo: { idVehiculo: data.vehiculoId },
      detalles: data.detalles.map((detalle) => ({
        descripcionTrabajo: detalle.descripcionTrabajo,
        costo: detalle.costo,
        repuestos: detalle.repuestosIds.map((id) => ({ idRepuesto: id })),
        mecanicos: detalle.mecanicosIds.map((id) => ({ idMecanico: id })),
      })),
    }

    createServicioMutation.mutate(payload)
  }

  const addRepuesto = (detalleIndex: number, repuestoId: number) => {
    const current = selectedRepuestos[detalleIndex] || []
    if (!current.includes(repuestoId)) {
      const updated = [...current, repuestoId]
      setSelectedRepuestos((prev) => ({ ...prev, [detalleIndex]: updated }))
      form.setValue(`detalles.${detalleIndex}.repuestosIds`, updated)
    }
  }

  const removeRepuesto = (detalleIndex: number, repuestoId: number) => {
    const current = selectedRepuestos[detalleIndex] || []
    const updated = current.filter((id) => id !== repuestoId)
    setSelectedRepuestos((prev) => ({ ...prev, [detalleIndex]: updated }))
    form.setValue(`detalles.${detalleIndex}.repuestosIds`, updated)
  }

  const addMecanico = (detalleIndex: number, mecanicoId: number) => {
    const current = selectedMecanicos[detalleIndex] || []
    if (!current.includes(mecanicoId)) {
      const updated = [...current, mecanicoId]
      setSelectedMecanicos((prev) => ({ ...prev, [detalleIndex]: updated }))
      form.setValue(`detalles.${detalleIndex}.mecanicosIds`, updated)
    }
  }

  const removeMecanico = (detalleIndex: number, mecanicoId: number) => {
    const current = selectedMecanicos[detalleIndex] || []
    const updated = current.filter((id) => id !== mecanicoId)
    setSelectedMecanicos((prev) => ({ ...prev, [detalleIndex]: updated }))
    form.setValue(`detalles.${detalleIndex}.mecanicosIds`, updated)
  }

  if (loadingVehiculos || loadingRepuestos || loadingMecanicos) {
    return (
      <div className="flex items-center justify-center min-h-[400px]">
        <Loader2 className="h-8 w-8 animate-spin" />
        <span className="ml-2">Cargando datos...</span>
      </div>
    )
  }

  return (
    <div className="container mx-auto py-8 px-4">
      <Card>
        <CardHeader>
          <CardTitle className="text-2xl font-bold">Registrar Nuevo Servicio</CardTitle>
        </CardHeader>
        <CardContent>
          <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
              {/* Información General del Servicio */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <FormField
                  control={form.control}
                  name="fecha"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Fecha del Servicio</FormLabel>
                      <FormControl>
                        <Input type="date" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                <FormField
                  control={form.control}
                  name="vehiculoId"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Vehículo</FormLabel>
                      <Select onValueChange={(value) => field.onChange(Number.parseInt(value))}>
                        <FormControl>
                          <SelectTrigger>
                            <SelectValue placeholder="Seleccionar vehículo" />
                          </SelectTrigger>
                        </FormControl>
                        <SelectContent>
                          {vehiculos?.map((vehiculo) => (
                            <SelectItem key={vehiculo.idVehiculo} value={vehiculo.idVehiculo.toString()}>
                              {vehiculo.marca} {vehiculo.modelo}
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                <FormField
                  control={form.control}
                  name="kilometraje"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Kilometraje Actual</FormLabel>
                      <FormControl>
                        <Input
                          type="number"
                          {...field}
                          onChange={(e) => field.onChange(Number.parseInt(e.target.value) || 0)}
                        />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                <FormField
                  control={form.control}
                  name="costoTotal"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Costo Total</FormLabel>
                      <FormControl>
                        <Input
                          type="number"
                          step="0.01"
                          {...field}
                          onChange={(e) => field.onChange(Number.parseFloat(e.target.value) || 0)}
                        />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
              </div>

              <FormField
                control={form.control}
                name="descripcion"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Descripción General del Trabajo</FormLabel>
                    <FormControl>
                      <Textarea {...field} rows={3} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />

              <Separator />

              {/* Detalles del Servicio */}
              <div className="space-y-4">
                <div className="flex items-center justify-between">
                  <h3 className="text-lg font-semibold">Detalles del Servicio</h3>
                  <Button
                    type="button"
                    variant="outline"
                    size="sm"
                    onClick={() =>
                      append({
                        descripcionTrabajo: "",
                        costo: 0,
                        repuestosIds: [],
                        mecanicosIds: [],
                      })
                    }
                  >
                    <Plus className="h-4 w-4 mr-2" />
                    Agregar Detalle
                  </Button>
                </div>

                {fields.map((field, index) => (
                  <Card key={field.id} className="p-4 gap-4">
                    <div className="flex items-center justify-between">
                      <h4 className="font-medium">Detalle {index + 1}</h4>
                      {fields.length > 1 && (
                        <Button
                          type="button"
                          variant="destructive"
                          size="sm"
                          onClick={() => {
                            remove(index)
                            // Clean up selected items for this index
                            const newSelectedRepuestos = { ...selectedRepuestos }
                            const newSelectedMecanicos = { ...selectedMecanicos }
                            delete newSelectedRepuestos[index]
                            delete newSelectedMecanicos[index]
                            setSelectedRepuestos(newSelectedRepuestos)
                            setSelectedMecanicos(newSelectedMecanicos)
                          }}
                        >
                          <Trash2 className="h-4 w-4" />
                        </Button>
                      )}
                    </div>

                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-2">
                      <FormField
                        control={form.control}
                        name={`detalles.${index}.descripcionTrabajo`}
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Descripción del Trabajo</FormLabel>
                            <FormControl>
                              <Textarea {...field} rows={2} />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />

                      <FormField
                        control={form.control}
                        name={`detalles.${index}.costo`}
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Costo</FormLabel>
                            <FormControl>
                              <Input
                                type="number"
                                step="0.01"
                                {...field}
                                onChange={(e) => field.onChange(Number.parseFloat(e.target.value) || 0)}
                              />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                    </div>

                    {/* Repuestos */}
                    <div className="mb-2">
                      <FormLabel>Repuestos Utilizados</FormLabel>
                      <Select onValueChange={(value) => addRepuesto(index, Number.parseInt(value))}>
                        <SelectTrigger className="mt-2">
                          <SelectValue placeholder="Seleccionar repuesto" />
                        </SelectTrigger>
                        <SelectContent>
                          {repuestos?.map((repuesto) => (
                            <SelectItem key={repuesto.idRepuesto} value={repuesto.idRepuesto.toString()}>
                              {repuesto.nombre} - ${repuesto.precio}
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                      <div className="flex flex-wrap gap-2 mt-2">
                        {(selectedRepuestos[index] || []).map((repuestoId) => {
                          const repuesto = repuestos?.find((r) => r.idRepuesto === repuestoId)
                          return (
                            <Badge key={repuestoId} variant="secondary" className="flex items-center gap-1">
                              {repuesto?.nombre}
                              <button
                                type="button"
                                onClick={() => removeRepuesto(index, repuestoId)}
                                className="ml-1 hover:text-destructive"
                              >
                                ×
                              </button>
                            </Badge>
                          )
                        })}
                      </div>
                      <FormMessage />
                    </div>

                    {/* Mecánicos */}
                    <div>
                      <FormLabel>Mecánicos Intervinientes</FormLabel>
                      <Select onValueChange={(value) => addMecanico(index, Number.parseInt(value))}>
                        <SelectTrigger className="mt-2">
                          <SelectValue placeholder="Seleccionar mecánico" />
                        </SelectTrigger>
                        <SelectContent>
                          {mecanicos?.map((mecanico) => (
                            <SelectItem key={mecanico.idMecanico} value={mecanico.idMecanico.toString()}>
                              {mecanico.nombre} - {mecanico.especialidad}
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                      <div className="flex flex-wrap gap-2 mt-2">
                        {(selectedMecanicos[index] || []).map((mecanicoId) => {
                          const mecanico = mecanicos?.find((m) => m.idMecanico === mecanicoId)
                          return (
                            <Badge key={mecanicoId} variant="secondary" className="flex items-center gap-1">
                              {mecanico?.nombre}
                              <button
                                type="button"
                                onClick={() => removeMecanico(index, mecanicoId)}
                                className="ml-1 hover:text-destructive"
                              >
                                ×
                              </button>
                            </Badge>
                          )
                        })}
                      </div>
                      <FormMessage />
                    </div>
                  </Card>
                ))}
              </div>

              <div className="flex justify-end space-x-4">
                <Button
                  type="button"
                  variant="outline"
                  onClick={() => {
                    form.reset()
                    setSelectedRepuestos({})
                    setSelectedMecanicos({})
                  }}
                >
                  Cancelar
                </Button>
                <Button type="submit" disabled={createServicioMutation.isPending}>
                  {createServicioMutation.isPending && <Loader2 className="mr-2 h-4 w-4 animate-spin" />}
                  Registrar Servicio
                </Button>
              </div>
            </form>
          </Form>
        </CardContent>
      </Card>
    </div>
  )
}
