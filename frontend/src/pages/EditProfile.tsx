import {
  Avatar,
  Button,
  Card,
  CardBody,
  Typography,
  Textarea,
  Select,
  Option,
} from "@material-tailwind/react";
import Footer from "../components/Footer";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Hobbies from "../components/Hobbies";
import Intereses from "../components/Interests";

export default function EditProfile() {
  const baseURL = import.meta.env.VITE_API_URL;
  const [preview, setPreview] = useState<string | null>(null);
  const [user, setUser] = useState<any>(null);
  const navigate = useNavigate();
  const [degree, setDegree] = useState("");
  const [description, setDescription] = useState("");
  const [hobbies, setHobbie] = useState<string[]>([]);
  const [interests, setInterests] = useState<string[]>([]);
  const [image, setImage] = useState<File | null>(null);

  const handleEditProfile = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const response = await fetch(
        `${baseURL}/api/usuarios/${sessionStorage.getItem("id")}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${sessionStorage.getItem("token")}`,
          },
          body: JSON.stringify({
            carrera: degree || null,
            descripcion: description || null,
            hobbies: hobbies.length > 0 ? hobbies : null,
            intereses: interests.length > 0 ? interests : null,
          }),
        }
      );

      if (!response.ok) {
        alert("No se ha podido actualizar el perfil");
        return;
      }
      alert("Perfil actualizado con éxito");
    } catch (err) {
      alert("Error de conexión con el servidor");
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const token = sessionStorage.getItem("token");
        const response = await fetch(`${baseURL}/api/usuarios/me`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        });
        const user = await response.json();
        setUser(user);
      } catch (err) {
        console.error(err);
      }
    };
    fetchData();
  }, []);

  const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    setPreview(URL.createObjectURL(file));

    const formData = new FormData();
    formData.append("file", file);

    try {
      const userId = sessionStorage.getItem("id");
      const token = sessionStorage.getItem("token");

      const backendRes = await fetch(
        `${baseURL}/api/usuarios/${userId}/foto-perfil`,
        {
          method: "POST",
          headers: {
            Authorization: `Bearer ${token}`,
          },
          body: formData,
        }
      );

      if (!backendRes.ok) {
        alert("Error al guardar la imagen en el servidor");
        return;
      }

      const data = await backendRes.json();
      alert("Imagen actualizada correctamente");
      setUser((prev: any) => ({ ...prev, rutaFotoPerfil: data.url }));
    } catch (error) {
      console.error("Error al subir la imagen:", error);
      alert("Error al subir la imagen");
    }
  };

  return (
    <>
      <section className="container mx-auto px-8 py-10 lg:mb-60">
        <Card
          placeholder={""}
          shadow={false}
          className="border border-gray-300 rounded-2xl"
        >
          <CardBody placeholder={""}>
            <div className="flex lg:gap-0 gap-6 flex-wrap justify-between items-center">
              <div className="flex items-center gap-3">
                <Avatar
                  placeholder={""}
                  src={preview || user?.rutaFotoPerfl}
                  alt="Potronet Avatar"
                  variant="rounded"
                  className="w-20 h-20"
                />
                <div>
                  <Typography placeholder={""} color="blue-gray" variant="h6">
                    {user?.nombre}
                  </Typography>
                  <p className="text-gray-700 break-all">
                    {user?.correo && (
                      <>
                        <span>{user.correo.split("@")[0]}</span>
                        <span className="hidden sm:inline">
                          @{user.correo.split("@")[1]}
                        </span>
                      </>
                    )}
                  </p>
                </div>
              </div>
              <div className="flex flex-wrap items-center gap-2">
                <input
                  type="file"
                  accept="image/*"
                  id="fileInput"
                  className="hidden"
                  onChange={handleFileChange}
                />
                <Button
                  placeholder={""}
                  variant="outlined"
                  className="border-gray-300 flex items-center gap-2"
                  onClick={() => document.getElementById("fileInput")?.click()}
                >
                  <i className="fa-solid fa-camera" />
                  Foto
                </Button>
                <form onSubmit={handleEditProfile}>
                  <Button
                    type="submit"
                    placeholder={""}
                    variant="outlined"
                    className="border-gray-300 flex items-center gap-2"
                  >
                    <i className="fa-solid fa-cloud-arrow-up" />
                    Guardar
                  </Button>
                </form>
              </div>
            </div>
            <div className="w-[100%] lg:flex">
              <div className="w-full lg:w-1/2">
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <div className="max-w-96 mb-5">
                    <Textarea
                      className="!border-gray-500"
                      label={"Biografía"}
                      labelProps={{
                        className: "!text-gray-500",
                      }}
                      value={description}
                      onChange={(e) => setDescription(e.target.value)}
                    />
                  </div>
                </Typography>

                <div className="max-w-96">
                  <Select
                    className="!border-gray-500"
                    labelProps={{
                      className: "!text-gray-500",
                    }}
                    placeholder={""}
                    onChange={(value) => setDegree(value || "")}
                    label="Seleccionar Carrera"
                  >
                    <Option value="Ingeniería en Software">
                      Ingeniería en Software
                    </Option>
                    <Option value="Ingeniería Química">
                      Ingeniería Química
                    </Option>
                    <Option value="Licenciatura en Psicología">
                      Licenciatura en Psicología
                    </Option>
                    <Option value="Licenciatura en Derecho">
                      Licenciatura en Derecho
                    </Option>
                    <Option value="Licenciatura en Diseño Gráfico">
                      Licenciatura en Diseño Gráfico
                    </Option>
                    <Option value="Ingeniería en Mecatrónica">
                      Ingeniería en Mecatrónica
                    </Option>
                  </Select>
                </div>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <strong>Teléfono: </strong>
                  {user?.telefono}
                </Typography>
              </div>
              <div className="w-full lg:w-1/2">
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <strong>Sexo: </strong>
                  {user?.sexo}
                </Typography>

                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6 mb-5"
                >
                  <strong>Fecha Nacimiento: </strong>
                  {user?.fechaNacimiento}
                </Typography>

                <div className="w-[100%] lg:w-[85%]">
                  <div className="w-full mb-5">
                    <Hobbies items={hobbies} onChange={setHobbie}></Hobbies>
                  </div>
                  <Intereses
                    items={interests}
                    onChange={setInterests}
                  ></Intereses>
                </div>
              </div>
            </div>
          </CardBody>
        </Card>
      </section>
      <Footer />
    </>
  );
}
