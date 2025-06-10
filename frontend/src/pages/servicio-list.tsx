import { useQuery } from "@tanstack/react-query"
import { Loader2, Calendar, Car, DollarSign, Wrench } from "lucide-react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { servicioService } from "../services/api"
import { useVehiculos } from "../hooks/useApiData"
import type { ServicioResponse } from "../types"

export default function ServiciosList() {
  const { data: vehiculos } = useVehiculos()

  const {
    data: servicios,
    isLoading,
    error,
  } = useQuery({
    queryKey: ["servicios"],
    queryFn: servicioService.getAll,
  })

  const getVehiculoInfo = (idVehiculo: number) => {
    return vehiculos?.find((v) => v.idVehiculo === idVehiculo)
  }

  if (isLoading) {
    return (
      <div className="flex items-center justify-center min-h-[400px]">
        <Loader2 className="h-8 w-8 animate-spin" />
        <span className="ml-2">Cargando servicios...</span>
      </div>
    )
  }

  if (error) {
    return (
      <div className="text-center py-8">
        <p className="text-destructive">Error al cargar los servicios</p>
      </div>
    )
  }

  return (
    <div className="container mx-auto py-8 px-4">
      <div className="mb-6">
        <h2 className="text-2xl font-bold">Servicios Registrados</h2>
        <p className="text-muted-foreground">Lista de todos los servicios realizados</p>
      </div>

      <div className="grid gap-6">
        {servicios?.map((servicio: ServicioResponse) => {
          const vehiculoInfo = getVehiculoInfo(servicio.idVehiculo)

          return (
            <Card key={servicio.id}>
              <CardHeader>
                <div className="flex items-center justify-between">
                  <CardTitle className="flex items-center gap-2">
                    <Wrench className="h-5 w-5" />
                    {servicio.descripcion}
                  </CardTitle>
                  <Badge variant="outline">
                    <Calendar className="h-4 w-4 mr-1" />
                    {new Date(servicio.fecha).toLocaleDateString()}
                  </Badge>
                </div>
              </CardHeader>
              <CardContent>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
                  <div className="flex items-center gap-2">
                    <Car className="h-4 w-4 text-muted-foreground" />
                    <div className="text-sm">
                      <p>
                        Vehículo:{" "}
                        {vehiculoInfo ? `${vehiculoInfo.marca} ${vehiculoInfo.modelo}` : `ID: ${servicio.idVehiculo}`}
                      </p>
                      {vehiculoInfo?.patente && (
                        <p className="text-muted-foreground">Patente: {vehiculoInfo.patente}</p>
                      )}
                    </div>
                  </div>
                  <div className="flex items-center gap-2">
                    <span className="text-sm">Kilometraje: {servicio.kilometraje?.toLocaleString()} km</span>
                  </div>
                  <div className="flex items-center gap-2">
                    <DollarSign className="h-4 w-4 text-muted-foreground" />
                    <span className="text-sm">Costo Total: ${servicio.costoTotal?.toLocaleString()}</span>
                  </div>
                </div>

                {servicio.detalles && servicio.detalles.length > 0 && (
                  <div className="space-y-2">
                    <h4 className="font-medium text-sm">Trabajos realizados:</h4>
                    {servicio.detalles.map((detalle) => (
                      <div key={detalle.id} className="bg-muted/50 p-3 rounded-lg">
                        <div className="flex justify-between items-start mb-2">
                          <p className="text-sm font-medium">{detalle.descripcionTrabajo}</p>
                          <Badge variant="secondary">${detalle.costo?.toLocaleString()}</Badge>
                        </div>
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 text-xs text-muted-foreground">
                          <div>
                            <p className="font-medium mb-1">Repuestos:</p>
                            <div className="flex flex-wrap gap-1">
                              {detalle.repuestos.map((repuesto, index) => (
                                <Badge key={index} variant="outline" className="text-xs">
                                  {repuesto}
                                </Badge>
                              ))}
                            </div>
                          </div>
                          <div>
                            <p className="font-medium mb-1">Mecánicos:</p>
                            <div className="flex flex-wrap gap-1">
                              {detalle.mecanicos.map((mecanico, index) => (
                                <Badge key={index} variant="outline" className="text-xs">
                                  {mecanico}
                                </Badge>
                              ))}
                            </div>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                )}
              </CardContent>
            </Card>
          )
        })}

        {(!servicios || servicios.length === 0) && (
          <Card>
            <CardContent className="text-center py-8">
              <p className="text-muted-foreground">No hay servicios registrados</p>
            </CardContent>
          </Card>
        )}
      </div>
    </div>
  )
}
