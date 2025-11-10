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

export default function Matches() {
  const baseURL = import.meta.env.VITE_API_URL;
  const navigate = useNavigate();

  const [usuarios, setUsuarios] = useState<any[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);
  const [cooldown, setCooldown] = useState(false);

  const startCooldown = () => {
    setCooldown(true);
    setTimeout(() => setCooldown(false), 2000);
  };

  const fetchMatches = async (p = 0) => {
    if (loading || cooldown) return;
    
    setLoading(true);
    try {
      const userId = sessionStorage.getItem("id");
      const token = sessionStorage.getItem("token");
      
      if (!userId || !token) {
        alert("Necesitas iniciar sesión primero");
        navigate("/login");
        return;
      }

      console.log("Cargando matches página:", p);

      const res = await fetch(
        `${baseURL}/api/matches/${userId}?page=${p}`,
        {
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
          },
        }
      );

      console.log("Response status:", res.status);

      if (res.status === 403) {
        alert("Token expirado. Por favor inicia sesión nuevamente.");
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("id");
        navigate("/login");
        return;
      }

      if (!res.ok) {
        throw new Error(`Error ${res.status}`);
      }

      const data = await res.json();
      console.log("Matches recibidos:", data);

      setUsuarios(data || []);
      setTotalPages(data.totalPages || 1);
      setPage(p);
      
    } catch (err) {
      console.error("Error cargando matches:", err);
      alert("Error al cargar matches. Revisa la consola para más detalles.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchMatches(0);
  }, []);

  const handlePageChange = (newPage: number) => {
    if (cooldown || loading) return;
    
    startCooldown();
    setPage(newPage);
    fetchMatches(newPage);
  };

  const handleChat = (usuario: any) => {
    navigate(`/chat/${usuario.id}`, { 
      state: { 
        usuario,
        chatId: usuario.chatId
      } 
    });
  };

  return (
    <section className="w-[95%] lg:w-[70%] mx-auto mt-6">
      <Typography
        placeholder={""}
        variant="h4"
        color="blue-gray"
        className="mb-6"
      >
        Tus Amigos
      </Typography>

      {loading && (
        <div className="text-center py-8">
          <Typography placeholder={""} variant="h6" color="gray">
            Cargando matches...
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

      <div className="flex flex-col gap-6">
        {usuarios.length === 0 && !loading ? (
          <div className="text-center py-12">
            <Typography placeholder={""} variant="h5" color="gray">
              {page === 0 
                ? "Aún no tienes matches. ¡Sigue explorando!" 
                : "No hay más matches en esta página"
              }
            </Typography>
            {page > 0 && (
              <Button
                placeholder={""}
                variant="outlined"
                className="mt-4"
                onClick={() => handlePageChange(0)}
                disabled={cooldown}
              >
                Volver a la primera página
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
                src={u.rutaFotoPerfil || "/default-avatar.png"}
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
                onClick={() => handleChat(u)}
                color="blue"
                className="flex items-center gap-2 mt-4 lg:mt-0"
                disabled={loading}
              >
                <ChatBubbleLeftRightIcon className="w-5 h-5" />
                Iniciar Chat
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