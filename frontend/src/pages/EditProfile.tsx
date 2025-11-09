import {
  Avatar,
  Button,
  Card,
  CardBody,
  Typography,
} from "@material-tailwind/react";
import Footer from "../components/Footer";
import { useEffect } from "react";
import { useState } from "react";
import { Textarea } from "@material-tailwind/react";
import { Select, Option } from "@material-tailwind/react";
import { useNavigate } from "react-router-dom";
import Hobbies from "../components/Hobbies";
import Intereses from "../components/Interests";

export default function EditProfile() {
  const baseURL = import.meta.env.VITE_API_URL;
  const [user, setUser] = useState<any>(null);
  const navigate = useNavigate();
  const [sex, setSex] = useState("");
  const [degree, setDegree] = useState("");
  const [description, setDescription] = useState("");
  const [hobbies, setHobbie] = useState<string[]>([]);
  const [interests, setInterests] = useState<string[]>([]);

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
                  src={user?.rutaFotoPerfl}
                  alt="Potronet Avatar"
                  variant="rounded"
                />
                <div>
                  <Typography placeholder={""} color="blue-gray" variant="h6">
                    {user?.nombre}
                  </Typography>
                  <Typography
                    placeholder={""}
                    variant="small"
                    className="font-normal text-gray-600"
                  >
                    {user?.correo}
                  </Typography>
                </div>
              </div>
              <div className="flex flex-wrap items-center gap-2">
                <Button
                  placeholder={""}
                  variant="outlined"
                  className="border-gray-300 flex items-center gap-2"
                >
                  <i className="fa-brands fa-medium" />
                  Guardar
                </Button>
              </div>
            </div>
            <div className="w-[100%] lg:flex">
              <div className="w-[50%]">
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <div className="max-w-96 mb-5">
                    <Textarea
                      className="!border-gray-500"
                      label={"Biografia"}
                      labelProps={{
                        className: "!text-gray-500",
                      }}
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
                    <Option value="Ingeniería en Mecatrónica">
                      Ingeniería en Mecatrónica
                    </Option>
                    <Option value="Ingeniería en Manufactura">
                      Ingeniería en Manufactura
                    </Option>
                    <Option value="Ingeniería en Logística">
                      Ingeniería en Logística
                    </Option>
                    <Option value="Ingeniería Industrial y de Sistemas">
                      Ingeniería Industrial y de Sistemas
                    </Option>
                    <Option value="Ingeniería en Electrónica">
                      Ingeniería en Electrónica
                    </Option>
                    <Option value="Ingeniería Electromecánica">
                      Ingeniería Electromecánica
                    </Option>
                    <Option value="Ingeniería Civil">Ingeniería Civil</Option>
                    <Option value="Ingeniería en Ciencias Ambientales">
                      Ingeniería en Ciencias Ambientales
                    </Option>
                    <Option value="Ingeniería en Biotecnología">
                      Ingeniería en Biotecnología
                    </Option>
                    <Option value="Ingeniería en Biosistemas">
                      Ingeniería en Biosistemas
                    </Option>
                    <Option value="Medicina Veterinaria y Zootecnia">
                      Medicina Veterinaria y Zootecnia
                    </Option>
                    <Option value="Licenciatura en Tecnología de Alimentos">
                      Licenciatura en Tecnología de Alimentos
                    </Option>
                    <Option value="Licenciatura en Psicología">
                      Licenciatura en Psicología
                    </Option>
                    <Option value="Licenciatura en Mercadotecnia">
                      Licenciatura en Mercadotecnia
                    </Option>
                    <Option value="Licenciatura en Gastronomía">
                      Licenciatura en Gastronomía
                    </Option>
                    <Option value="Licenciatura en Enfermería">
                      Licenciatura en Enfermería
                    </Option>
                    <Option value="Licenciatura en Emprendimiento e Innovación">
                      Licenciatura en Emprendimiento e Innovación
                    </Option>
                    <Option value="Licenciatura en Educación Inicial y Gestión de Instituciones">
                      Licenciatura en Educación Inicial y Gestión de
                      Instituciones
                    </Option>
                    <Option value="Licenciatura en Educación Infantil">
                      Licenciatura en Educación Infantil
                    </Option>
                    <Option value="Licenciatura en Educación Artística y Gestión Cultural">
                      Licenciatura en Educación Artística y Gestión Cultural
                    </Option>
                    <Option value="Licenciatura en Economía y Finanzas">
                      Licenciatura en Economía y Finanzas
                    </Option>
                    <Option value="Licenciatura en Derecho">
                      Licenciatura en Derecho
                    </Option>
                    <Option value="Licenciatura en Diseño Gráfico">
                      Licenciatura en Diseño Gráfico
                    </Option>
                    <Option value="Licenciatura en Dirección de la Cultura Física y el Deporte">
                      Licenciatura en Dirección de la Cultura Física y el
                      Deporte
                    </Option>
                    <Option value="Licenciatura en Contaduría Pública">
                      Licenciatura en Contaduría Pública
                    </Option>
                    <Option value="Licenciatura en Ciencias del Ejercicio Físico">
                      Licenciatura en Ciencias del Ejercicio Físico
                    </Option>
                    <Option value="Licenciatura en Ciencias de la Educación">
                      Licenciatura en Ciencias de la Educación
                    </Option>
                    <Option value="Licenciatura en Arquitectura">
                      Licenciatura en Arquitectura
                    </Option>
                    <Option value="Licenciatura en Administración Estratégica">
                      Licenciatura en Administración Estratégica
                    </Option>
                    <Option value="Licenciatura en Administración de Empresas Turísticas">
                      Licenciatura en Administración de Empresas Turísticas
                    </Option>
                    <Option value="Licenciatura en Administración">
                      Licenciatura en Administración
                    </Option>
                    <Option value="Profesional Asociado en Desarrollo Infantil">
                      Profesional Asociado en Desarrollo Infantil
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
              <div className="w-[50%]">
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
                <div className="w-[90%]">
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
      <Footer></Footer>
    </>
  );
}
