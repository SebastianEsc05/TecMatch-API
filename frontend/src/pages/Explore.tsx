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

  const fetchUsuarios = async (p = 0) => {
    try {
      const params = new URLSearchParams();
      params.append("page", p.toString());
      params.append("size", "5");
      if (filtro && valorFiltro) {
        params.append("filtro", filtro);
        params.append("valor", valorFiltro);
      }

      const res = await fetch(
        `${baseURL}/api/usuarios/explorar?${params.toString()}`,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      const data = await res.json();
      setUsuarios(data.usuarios || []);
      setTotalPages(data.totalPaginas || 0);
      setPage(data.paginaActual || 0);
    } catch (err) {
      console.error("Error cargando usuarios:", err);
    }
  };

  useEffect(() => {
    fetchUsuarios(page);
  }, [page, filtro, valorFiltro]);

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
      const result = await res.json();
      if (result.hayMatch) {
        alert("¡Hay Match! 🎉");
      } else {
        alert("Like enviado ❤️");
      }
    } catch (err) {
      console.error("Error enviando like:", err);
    }
  };

  return (
    <section className="w-[95%] lg:w-[70%] mx-auto">
      <div className="flex flex-wrap justify-between items-center mb-6">
        <Typography placeholder={""} variant="h4" color="blue-gray">
          Explora y Haz Nuevos Amigos
        </Typography>

        <div className="flex gap-3">
          <Input
            crossOrigin
            label="Buscar"
            value={valorFiltro}
            onChange={(e) => setValorFiltro(e.target.value)}
            icon={<MagnifyingGlassIcon className="h-5 w-5" />}
          />
          <Menu>
            <MenuHandler>
              <Button
                placeholder={""}
                variant="outlined"
                className="flex items-center gap-2 border-gray-300"
              >
                Filtrar <ChevronDownIcon className="h-3 w-3" />
              </Button>
            </MenuHandler>
            <MenuList placeholder={""}>
              <MenuItem placeholder={""} onClick={() => setFiltro("carrera")}>
                Carrera
              </MenuItem>
              <MenuItem placeholder={""} onClick={() => setFiltro("hobbie")}>
                Hobbies
              </MenuItem>
              <MenuItem placeholder={""} onClick={() => setFiltro("interes")}>
                Intereses
              </MenuItem>
            </MenuList>
          </Menu>
        </div>
      </div>

      <div className="flex flex-col gap-6">
        {usuarios.map((u) => (
          <Card
            placeholder={""}
            key={u.id}
            className="p-4 flex flex-col lg:flex-row items-start gap-6 border border-gray-300 shadow-sm rounded-2xl"
          >
            <Avatar
              placeholder={""}
              src={u.rutaFotoPerfil}
              alt={u.nombre}
              variant="rounded"
              className="w-24 h-24"
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
            >
              <HeartIcon className="w-5 h-5" /> Like
            </Button>
          </Card>
        ))}
      </div>

      <div className="flex justify-center items-center gap-3 mt-8">
        <Button
          placeholder={""}
          disabled={page === 0}
          onClick={() => setPage((p) => p - 1)}
          variant="outlined"
        >
          Anterior
        </Button>
        <Typography placeholder={""} variant="small">
          Página {page + 1} de {totalPages}
        </Typography>
        <Button
          placeholder={""}
          disabled={page + 1 >= totalPages}
          onClick={() => setPage((p) => p + 1)}
          variant="outlined"
        >
          Siguiente
        </Button>
      </div>
    </section>
  );
}
