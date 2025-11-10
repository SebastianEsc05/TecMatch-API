import {
  Card,
  CardBody,
  Avatar,
  Typography,
  Button,
} from "@material-tailwind/react";
import { ChatBubbleLeftRightIcon } from "@heroicons/react/24/outline";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function MatchesCardList() {
  const baseURL = import.meta.env.VITE_API_URL;
  const navigate = useNavigate();

  const [usuarios, setUsuarios] = useState<any[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const fetchMatches = async (p = 0) => {
    try {
      const res = await fetch(
        `${baseURL}/api/usuarios/matches?id=${sessionStorage?.getItem(
          "id"
        )}&page=${p}`
      );
      const data = await res.json();

      setUsuarios(data.usuarios || []);
      setTotalPages(data.totalPaginas || 0);
      setPage(data.paginaActual || 0);
    } catch (err) {
      console.error("Error cargando matches:", err);
    }
  };

  useEffect(() => {
    fetchMatches(page);
  }, [page]);

  return (
    <section className="w-[95%] lg:w-[70%] mx-auto mt-6">
      <Typography
        placeholder={""}
        variant="h4"
        color="blue-gray"
        className="mb-6"
      >
        Tus Matches
      </Typography>

      <div className="flex flex-col gap-6">
        {usuarios.map((u) => (
          <Card
            placeholder={""}
            key={u.id}
            className="p-4 flex flex-col lg:flex-row gap-6 border border-gray-300 shadow-sm rounded-2xl"
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
                {u.carrera || "Carrera no definida"}
              </Typography>

              <Typography
                placeholder={""}
                variant="small"
                className="mt-3 text-gray-700"
              >
                <strong>Intereses:</strong>{" "}
                {u.intereses?.join(", ") || "Ninguno"}
              </Typography>
            </CardBody>

            <Button
              placeholder={""}
              onClick={() => navigate(`/chat/${u.id}`)}
              color="blue"
              className="flex items-center gap-2"
            >
              <ChatBubbleLeftRightIcon className="w-5 h-5" />
              Chat
            </Button>
          </Card>
        ))}
      </div>

      <div className="flex justify-center items-center gap-3 mt-8">
        <Button
          placeholder={""}
          disabled={page === 0}
          onClick={() => setPage((p) => p - 1)}
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
        >
          Siguiente
        </Button>
      </div>
    </section>
  );
}
