import { useState } from "react";
import { Card, Input, Button, Typography } from "@material-tailwind/react";
import { Link, useNavigate } from "react-router-dom";

export default function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const regex = /^[A-Za-z0-9._%+-]+@potros\.itson\.edu\.mx$/;

  const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!email.trim() || !password.trim()) {
      alert("Ningun campo debe estar vacio");
      return;
    }
    if (!regex.test(email)) {
      alert("Solo correo @potros.itson.edu.mx");
      return;
    }
    try {
      const response = await fetch("/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ correo: email, contrasenia: password }),
      });
      const data = await response.json();
      if (!response.ok) {
        alert(data.message);
        return;
      }
      sessionStorage.setItem("token", data.token);
      navigate("/home");
    } catch (err) {
      alert("Error de conexion con el servidor");
      return;
    }
  };

  return (
    <div className="flex justify-center items-start max-h-screen text-white p-4 mt-10 lg:mt-20">
      <div className="w-full max-w-md lg:max-w-2xl mt-10">
        <Card
          placeholder={""}
          color="transparent"
          shadow={false}
          className="flex flex-col items-center"
        >
          <Link to={"/"}>
            <Typography
              placehorder={""}
              variant="h1"
              color="blue-gray"
              className="mt-10 mb-10"
            >
              Iniciar Sesión
            </Typography>
          </Link>

          <Typography
            placehorder={""}
            color="gray"
            className="mt-1 font-normal text-center"
          >
            ¡Hola de nuevo! Introduce tus credenciales para iniciar sesión.
          </Typography>

          <form
            onSubmit={handleLogin}
            className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96"
          >
            <div className="mb-1 flex flex-col gap-6">
              <Typography
                placehorder={""}
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Correo
              </Typography>
              <Input
                crossOrigin={""}
                size="lg"
                placeholder="nombre.apellidoID@potros.itson.edu.mx"
                onChange={(e) => setEmail(e.target.value)}
                labelProps={{
                  className: "before:content-none after:content-none",
                }}
                className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
              />

              <Typography
                placehorder={""}
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Contraseña
              </Typography>
              <Input
                crossOrigin={""}
                type="password"
                size="lg"
                placeholder="********"
                onChange={(e) => setPassword(e.target.value)}
                labelProps={{
                  className: "before:content-none after:content-none",
                }}
                className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
              />
            </div>

            <Button type="submit" placeholder={""} className="mt-6" fullWidth>
              Ingresar
            </Button>

            <Typography
              placehorder={""}
              color="gray"
              className="mt-4 text-center font-normal"
            >
              Aún no tienes una cuenta?{" "}
              <Link to={"/signup"} className="font-medium text-gray-900">
                Registrarse
              </Link>
            </Typography>
          </form>
        </Card>
      </div>
    </div>
  );
}
