import { Routes, Route } from "react-router"
import { Link, Outlet } from "react-router"
import { QueryClient, QueryClientProvider } from "@tanstack/react-query"
import { Toaster } from "sonner"
import { ThemeProvider } from "@/components/theme-provider"
import { ModeToggle } from "@/components/mode-toggle"

import ServicioForm from "./pages/servicio-form"
import ServiciosList from "./pages/servicio-list"
import ConsultaServicios from "./pages/consultar-servicio"

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1,
      refetchOnWindowFocus: false,
    },
  },
})

function Layout() {
  return (
    <div className="min-h-screen bg-background">
      <header className="sticky top-0 z-50 w-full border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
        <div className="max-w-screen-xl container flex h-14 items-center m-auto px-4">
          <div className="mr-4 flex">
            <Link to="/" className="mr-6 flex items-center space-x-2 font-bold">
              <span>Sistema de Servicios Vehiculares</span>
            </Link>
            <nav className="flex items-center space-x-6 text-sm font-medium">
              <Link to="/" className="transition-colors hover:text-foreground/80">
                Lista de Servicios
              </Link>
              <Link to="/servicios/nuevo" className="transition-colors hover:text-foreground/80">
                Nuevo Servicio
              </Link>
              <Link to="/consulta" className="transition-colors hover:text-foreground/80">
                Consultar Servicios
              </Link>
            </nav>
          </div>
          <div className="ml-auto flex items-center space-x-4">
            <ModeToggle />
          </div>
        </div>
      </header>
      <main className="max-w-screen-xl container py-6 m-auto">
        <Outlet />
      </main>
    </div>
  )
}

export default function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider defaultTheme="light" storageKey="vite-ui-theme">
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<ServiciosList />} />
            <Route path="servicios/nuevo" element={<ServicioForm />} />
            <Route path="consulta" element={<ConsultaServicios />} />
          </Route>
        </Routes>
        <Toaster />
      </ThemeProvider>
    </QueryClientProvider>
  )
}
