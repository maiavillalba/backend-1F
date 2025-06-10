
import { useState } from "react"
import { useQuery } from "@tanstack/react-query"
import { Search, Calendar, User, ChevronDown, ChevronRight, Loader2 } from "lucide-react"

import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Badge } from "@/components/ui/badge"
import { Collapsible, CollapsibleContent, CollapsibleTrigger } from "@/components/ui/collapsible"

import { servicioService } from "../services/api"
import { useVehiculos, useClientes } from "../hooks/useApiData"
import type { ServicioResponse } from "../types"

export default function ConsultaServicios() {
  const [filtros, setFiltros] = useState({
    idCliente: "",
    fecha: "",
  })
  const [serviciosExpandidos, setServiciosExpandidos] = useState<Set<number>>(new Set())

  const { data: vehiculos } = useVehiculos()
  const { data: clientes } = useClientes()

  const {
    data: servicios,
    isLoading,
    error,
    refetch,
  } = useQuery({
    queryKey: ["servicios-consulta", filtros],
    queryFn: async () => {
      const filtrosApi: { idCliente?: number; fecha?: string } = {}

      if (filtros.idCliente && filtros.idCliente !== "all") {
        filtrosApi.idCliente = Number.parseInt(filtros.idCliente)
      }

      if (filtros.fecha) {
        filtrosApi.fecha = filtros.fecha
      }

      return await servicioService.getByFilters(filtrosApi)
    },
    enabled: false, // Solo ejecutar cuando se haga búsqueda manual
  })

  const handleBuscar = () => {
    refetch()
  }

  const handleLimpiarFiltros = () => {
    setFiltros({
      idCliente: "",
      fecha: "",
    })
  }

  const toggleServicioExpandido = (servicioId: number) => {
    const nuevosExpandidos = new Set(serviciosExpandidos)
    if (nuevosExpandidos.has(servicioId)) {
      nuevosExpandidos.delete(servicioId)
    } else {
      nuevosExpandidos.add(servicioId)
    }
    setServiciosExpandidos(nuevosExpandidos)
  }

  const getVehiculoInfo = (idVehiculo: number) => {
    return vehiculos?.find((v) => v.idVehiculo === idVehiculo)
  }

  return (
    <div className="space-y-6 px-4">
      <div className="flex items-center gap-2">
        <Search className="h-5 w-5" />
        <h1 className="text-2xl font-bold">Consulta de Servicios</h1>
      </div>

      {/* Filtros de Búsqueda */}
      <Card>
        <CardHeader>
          <CardTitle>Filtros de Búsqueda</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="cliente">Cliente</Label>
              <div className="relative">
                <User className="absolute left-3 top-3 h-4 w-4 text-muted-foreground z-10" />
                <Select
                  value={filtros.idCliente}
                  onValueChange={(value) => setFiltros({ ...filtros, idCliente: value })}
                >
                  <SelectTrigger className="pl-10">
                    <SelectValue placeholder="Seleccionar cliente (opcional)" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="all">Todos los clientes</SelectItem>
                    {clientes?.map((cliente) => (
                      <SelectItem key={cliente.idCliente} value={cliente.idCliente.toString()}>
                        {cliente.nombre} - {cliente.cedula}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
            </div>

            <div className="space-y-2">
              <Label htmlFor="fecha">Fecha del Servicio</Label>
              <div className="relative">
                <Calendar className="absolute left-3 top-3 h-4 w-4 text-muted-foreground z-10" />
                <Input
                  id="fecha"
                  type="date"
                  value={filtros.fecha}
                  onChange={(e) => setFiltros({ ...filtros, fecha: e.target.value })}
                  className="pl-10"
                  placeholder="Fecha opcional"
                />
              </div>
            </div>
          </div>

          <div className="flex justify-end gap-2 mt-4">
            <Button variant="outline" onClick={handleLimpiarFiltros}>
              Limpiar Filtros
            </Button>
            <Button onClick={handleBuscar} disabled={isLoading}>
              {isLoading && <Loader2 className="mr-2 h-4 w-4 animate-spin" />}
              <Search className="mr-2 h-4 w-4" />
              Buscar Servicios
            </Button>
          </div>

        </CardContent>
      </Card>

      {/* Resultados */}
      {error && (
        <Card>
          <CardContent className="text-center py-8">
            <p className="text">No se han encontrado resultados</p>
          </CardContent>
        </Card>
      )}

      {servicios && (
        <div className="space-y-4">
          <div className="flex items-center justify-between">
            <h2 className="text-lg font-semibold">
              Resultados ({servicios.length} servicio{servicios.length !== 1 ? "s" : ""} encontrado
              {servicios.length !== 1 ? "s" : ""})
            </h2>
          </div>

          {servicios.length === 0 ? (
            <Card>
              <CardContent className="text-center py-8">
                <p className="text-muted-foreground">No se encontraron servicios con los filtros aplicados</p>
                <p className="text-sm text-muted-foreground mt-2">
                  Intenta ajustar los filtros o realizar una búsqueda sin filtros
                </p>
              </CardContent>
            </Card>
          ) : (
            <div className="space-y-4">
              {servicios.map((servicio: ServicioResponse) => {
                const vehiculoInfo = getVehiculoInfo(servicio.idVehiculo)

                return (
                  <Card key={servicio.id}>
                    <Collapsible
                      open={serviciosExpandidos.has(servicio.id)}
                      onOpenChange={() => toggleServicioExpandido(servicio.id)}
                    >
                      <CollapsibleTrigger asChild>
                        <CardHeader className="cursor-pointer hover:bg-muted/50 transition-colors">
                          <div className="flex items-center justify-between">
                            <div className="flex items-center gap-4">
                              {serviciosExpandidos.has(servicio.id) ? (
                                <ChevronDown className="h-4 w-4" />
                              ) : (
                                <ChevronRight className="h-4 w-4" />
                              )}
                              <div>
                                <CardTitle className="text-lg">{servicio.descripcion}</CardTitle>
                                <div className="flex items-center gap-4 text-sm text-muted-foreground mt-1">
                                  <span>📅 {new Date(servicio.fecha).toLocaleDateString()}</span>
                                  <span>
                                    🚗{" "}
                                    {vehiculoInfo
                                      ? `${vehiculoInfo.marca} ${vehiculoInfo.modelo}`
                                      : `Vehículo ID: ${servicio.idVehiculo}`}
                                  </span>
                                  {vehiculoInfo?.numeroChapa && <span>🏷️ {vehiculoInfo.numeroChapa}</span>}
                                </div>
                              </div>
                            </div>
                            <div className="text-right">
                              <Badge variant="secondary" className="text-lg">
                                ${servicio.costoTotal?.toLocaleString()}
                              </Badge>
                              <p className="text-sm text-muted-foreground mt-1">
                                {servicio.kilometraje?.toLocaleString()} km
                              </p>
                            </div>
                          </div>
                        </CardHeader>
                      </CollapsibleTrigger>

                      <CollapsibleContent>
                        <CardContent className="pt-0">
                          <div className="space-y-4">
                            <div className="grid grid-cols-1 md:grid-cols-3 gap-4 p-4 bg-muted/30 rounded-lg">
                              <div>
                                <p className="text-sm font-medium text-muted-foreground py-2">Vehículo</p>
                                <p className="font-medium">
                                  {vehiculoInfo
                                    ? `${vehiculoInfo.marca} ${vehiculoInfo.modelo}`
                                    : `ID: ${servicio.idVehiculo}`}
                                </p>
                              </div>
                              <div>
                                <p className="text-sm font-medium text-muted-foreground">Fecha del Servicio</p>
                                <p className="font-medium">{new Date(servicio.fecha).toLocaleDateString()}</p>
                                <p className="text-sm text-muted-foreground">
                                  {new Date(servicio.fecha).toLocaleTimeString()}
                                </p>
                              </div>
                              <div>
                                <p className="text-sm font-medium text-muted-foreground">Kilometraje</p>
                                <p className="font-medium">{servicio.kilometraje?.toLocaleString()} km</p>
                              </div>
                            </div>

                            {servicio.detalles && servicio.detalles.length > 0 && (
                              <div>
                                <h4 className="font-semibold mb-3">Detalles del Servicio</h4>
                                <div className="space-y-3">
                                  {servicio.detalles.map((detalle) => (
                                    <div key={detalle.id} className="border rounded-lg p-4">
                                      <div className="flex justify-between items-start mb-3">
                                        <h5 className="font-medium">{detalle.descripcionTrabajo}</h5>
                                        <Badge variant="outline">${detalle.costo?.toLocaleString()}</Badge>
                                      </div>

                                      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                        <div>
                                          <p className="text-sm font-medium text-muted-foreground mb-2">
                                            Repuestos Utilizados
                                          </p>
                                          <div className="flex flex-wrap gap-1">
                                            {detalle.repuestos.length > 0 ? (
                                              detalle.repuestos.map((repuesto, index) => (
                                                <Badge key={index} variant="secondary" className="text-xs">
                                                  {repuesto}
                                                </Badge>
                                              ))
                                            ) : (
                                              <span className="text-sm text-muted-foreground">Sin repuestos</span>
                                            )}
                                          </div>
                                        </div>

                                        <div>
                                          <p className="text-sm font-medium text-muted-foreground mb-2">
                                            Mecánicos Intervinientes
                                          </p>
                                          <div className="flex flex-wrap gap-1">
                                            {detalle.mecanicos.length > 0 ? (
                                              detalle.mecanicos.map((mecanico, index) => (
                                                <Badge key={index} variant="outline" className="text-xs">
                                                  {mecanico}
                                                </Badge>
                                              ))
                                            ) : (
                                              <span className="text-sm text-muted-foreground">Sin mecánicos</span>
                                            )}
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                  ))}
                                </div>
                              </div>
                            )}
                          </div>
                        </CardContent>
                      </CollapsibleContent>
                    </Collapsible>
                  </Card>
                )
              })}
            </div>
          )}
        </div>
      )}
    </div>
  )
}
