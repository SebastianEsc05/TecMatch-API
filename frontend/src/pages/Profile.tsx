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
import { Link } from "react-router-dom";

export default function Profile() {
  const baseURL = import.meta.env.VITE_API_URL;
  const [user, setUser] = useState<any>(null);

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
      <section className="container mx-auto px-8 py-10 lg:mb-80">
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
                <Link to={"/editProfile"}>
                  <Button
                    placeholder={""}
                    variant="outlined"
                    className="border-gray-300 flex items-center gap-2"
                  >
                    <i className="fa-brands fa-medium" />
                    Editar Perfil
                  </Button>
                </Link>
              </div>
            </div>
            <div className="w-[100%] lg:flex">
              <div className="w-[50%]">
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <strong>Biografía: </strong>
                  <br />
                  {user?.descripcion}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <strong>Carrera: </strong>
                  {user?.carrera}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <strong>Teléfono: </strong>
                  {user?.telefono}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <strong>Sexo: </strong>
                  {user?.sexo}
                </Typography>
              </div>
              <div className="w-[50%]">
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <strong>Fecha Nacimiento: </strong>
                  {user?.fechaNacimiento}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <strong>Hobbies: </strong>
                  {user?.hobbies}
                </Typography>
                <Typography
                  placeholder={""}
                  variant="small"
                  className="font-normal text-gray-600 mt-6"
                >
                  <strong>Intereses: </strong>
                  {user?.intereses}
                </Typography>
              </div>
            </div>
          </CardBody>
        </Card>
      </section>
      <Footer></Footer>
    </>
  );
}
