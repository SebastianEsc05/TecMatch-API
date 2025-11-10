import {
  Card,
  CardBody,
  Avatar,
  Typography,
  Button,
  Menu,
  MenuList,
  MenuHandler,
  MenuItem,
  Input,
} from "@material-tailwind/react";
import {
  ChevronDownIcon,
  HeartIcon,
  MagnifyingGlassIcon,
} from "@heroicons/react/24/outline";
import { useEffect, useState } from "react";

export default function Explore() {
  const baseURL = import.meta.env.VITE_API_URL;
  const token = sessionStorage.getItem("token");
  const [usuarios, setUsuarios] = useState<any[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [filtro, setFiltro] = useState<string | null>(null);
  const [valorFiltro, setValorFiltro] = useState("");
  const [loading, setLoading] = useState(false);
  const [cooldown, setCooldown] = useState(false);

  const startCooldown = () => {
    setCooldown(true);
    setTimeout(() => setCooldown(false), 2000);
  };

  const fetchUsuarios = async (p = 0) => {
    if (loading || cooldown) return;
    
    setLoading(true);
    try {
      const params = new URLSearchParams();
      params.append("page", p.toString());
      params.append("size", "5");
      
      if (filtro && valorFiltro.trim()) {
        params.append("filtro", filtro);
        params.append("valor", valorFiltro.trim());
      }

      console.log("Buscando con:", { filtro, valor: valorFiltro, page: p });

      const res = await fetch(
        `${baseURL}/api/usuarios/explorar?${params.toString()}`,
        {
          headers: { 
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}` 
          },
        }
      );

      if (!res.ok) {
        throw new Error(`Error ${res.status}`);
      }

      const data = await res.json();
      setUsuarios(data.usuarios || []);
      setTotalPages(data.totalPaginas || 0);
      setPage(data.paginaActual || 0);
      
    } catch (err) {
      console.error("Error cargando usuarios:", err);
      alert("Error al cargar usuarios");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const timeoutId = setTimeout(() => {
      setPage(0);
      fetchUsuarios(0);
    }, 500);

    return () => clearTimeout(timeoutId);
  }, [filtro, valorFiltro]);


  useEffect(() => {
    fetchUsuarios(0);
  }, []);

  const handlePageChange = (newPage: number) => {
    if (cooldown || loading) return;
    
    startCooldown();
    setPage(newPage);
    fetchUsuarios(newPage);
  };

  const handleLike = async (usuarioId: number) => {
    try {
      const res = await fetch(`${baseURL}/api/interacciones/likes`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ usuarioQueRecibeLikeId: usuarioId }),
      });
      
      if (!res.ok) {
        throw new Error(`Error ${res.status}`);
      }
      
      const result = await res.json();
      if (result.hayMatch) {
        alert("¡Hay Match!");
      } else {
        alert("Like enviado");
      }
    } catch (err) {
      console.error("Error enviando like:", err);
      alert("Error al enviar like");
    }
  };

  const handleFilterSelect = (nuevoFiltro: string) => {
    setFiltro(nuevoFiltro);
    setValorFiltro("");
  };

  const getFilterPlaceholder = () => {
    switch (filtro) {
      case "carrera": return "Ej: Ingeniería en Software";
      case "hobbie": return "Ej: Fútbol, Videojuegos";
      case "interes": return "Ej: Tecnología, Deportes";
      default: return "Selecciona un filtro primero";
    }
  };

  const filtrosDisponibles = [
    { value: "carrera", label: "Carrera", icon: "" },
    { value: "hobbie", label: "Hobbies", icon: "" },
    { value: "interes", label: "Intereses", icon: "" },
  ];

  return (
    <section className="w-[95%] lg:w-[70%] mx-auto">
      <div className="flex flex-wrap justify-between items-center mb-6">
        <Typography placeholder={""} variant="h4" color="blue-gray">
          Explora y Haz Nuevos Amigos
        </Typography>

        <div className="flex gap-3 flex-wrap">
          <div className="flex gap-2">
            <Input
              crossOrigin
              label={getFilterPlaceholder()}
              value={valorFiltro}
              onChange={(e) => setValorFiltro(e.target.value)}
              icon={<MagnifyingGlassIcon className="h-5 w-5" />}
              disabled={!filtro}
              className="min-w-[200px]"
            />
            
            <Menu>
              <MenuHandler>
                <Button
                  placeholder={""}
                  variant="outlined"
                  className="flex items-center gap-2 border-gray-300"
                  disabled={loading}
                >
                  {filtro ? `Filtro: ${filtro}` : "Filtrar"} 
                  <ChevronDownIcon className="h-3 w-3" />
                </Button>
              </MenuHandler>
              <MenuList placeholder={""}>
                {filtrosDisponibles.map((filtroItem) => (
                  <MenuItem 
                    key={filtroItem.value}
                    placeholder={""} 
                    onClick={() => handleFilterSelect(filtroItem.value)}
                  >
                    {filtroItem.label}
                  </MenuItem>
                ))}
                <hr className="my-2" />
                <MenuItem 
                  placeholder={""} 
                  onClick={() => {
                    setFiltro(null);
                    setValorFiltro("");
                    fetchUsuarios(0);
                  }}
                >
                  Limpiar filtros
                </MenuItem>
              </MenuList>
            </Menu>
          </div>
        </div>
      </div>


      {loading && (
        <div className="text-center py-8">
          <Typography placeholder={""} variant="h6" color="gray">
            Buscando usuarios...
          </Typography>
        </div>
      )}
      {cooldown && (
        <div className="text-center py-2">
          <Typography placeholder={""} variant="small" color="gray">
            Espera 2 segundos antes de cambiar de página...
          </Typography>
        </div>
      )}

      {filtro && valorFiltro && (
        <div className="mb-4 p-3 bg-blue-50 rounded-lg">
          <Typography placeholder={""} variant="small">
            Filtrado por <strong>{filtro}</strong>: "{valorFiltro}"
            <Button
              placeholder={""}
              variant="text"
              size="sm"
              className="ml-2"
              onClick={() => {
                setFiltro(null);
                setValorFiltro("");
                fetchUsuarios(0);
              }}
            >
              ✕
            </Button>
          </Typography>
        </div>
      )}

      <div className="flex flex-col gap-6">
        {usuarios.length === 0 && !loading ? (
          <div className="text-center py-12">
            <Typography placeholder={""} variant="h5" color="gray">
              {filtro && valorFiltro 
                ? "No se encontraron usuarios con esos criterios" 
                : "No hay usuarios para mostrar"
              }
            </Typography>
            {!filtro && (
              <Button
                placeholder={""}
                variant="outlined"
                className="mt-4"
                onClick={() => fetchUsuarios(0)}
              >
                Reintentar
              </Button>
            )}
          </div>
        ) : (
          usuarios.map((u) => (
            <Card
              placeholder={""}
              key={u.id}
              className="p-4 flex flex-col lg:flex-row items-start gap-6 border border-gray-300 shadow-sm rounded-2xl hover:shadow-md transition-shadow"
            >
              <Avatar
                placeholder={""}
                src={u.rutaFotoPerfl || "/default-avatar.png"}
                alt={u.nombre}
                variant="rounded"
                className="w-24 h-24 object-cover"
              />
              <CardBody placeholder={""} className="flex-1">
                <Typography placeholder={""} variant="h6" color="blue-gray">
                  {u.nombre}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  color="gray"
                  className="mt-1"
                >
                  {u.correo}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="mt-3 text-gray-700"
                >
                  <strong>Descripción:</strong>{" "}
                  {u.descripcion || "Sin descripción"}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="mt-1 text-gray-700"
                >
                  <strong>Carrera:</strong> {u.carrera || "Sin carrera"}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="mt-1 text-gray-700"
                >
                  <strong>Hobbies:</strong> {u.hobbies?.join(", ") || "Ninguno"}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="mt-1 text-gray-700"
                >
                  <strong>Intereses:</strong>{" "}
                  {u.intereses?.join(", ") || "Ninguno"}
                </Typography>
              </CardBody>

              <Button
                placeholder={""}
                onClick={() => handleLike(u.id)}
                color="red"
                className="flex items-center gap-2 mt-4 lg:mt-0"
                disabled={loading}
              >
                <HeartIcon className="w-5 h-5" /> Like
              </Button>
            </Card>
          ))
        )}
      </div>

      {totalPages > 1 && (
        <div className="flex justify-center items-center gap-3 mt-8">
          <Button
            placeholder={""}
            disabled={page === 0 || loading || cooldown}
            onClick={() => handlePageChange(page - 1)}
            variant="outlined"
          >
            ← Anterior
          </Button>
          
          <Typography placeholder={""} variant="small">
            Página {page + 1} de {totalPages}
          </Typography>
          
          <Button
            placeholder={""}
            disabled={page + 1 >= totalPages || loading || cooldown}
            onClick={() => handlePageChange(page + 1)}
            variant="outlined"
          >
            Siguiente →
          </Button>
        </div>
      )}
    </section>
  );
}