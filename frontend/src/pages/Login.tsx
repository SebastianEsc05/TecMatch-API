import { Card, Input, Button, Typography } from "@material-tailwind/react";
import { Link } from "react-router-dom";

export default function Login() {
  return (
    <div className="flex justify-center items-start max-h-screen text-white p-4 mt-10 lg:mt-20">
      <div className="w-full max-w-md lg:max-w-2xl mt-10">
        <Card
          placeholder
          color="transparent"
          shadow={false}
          className="flex flex-col items-center">
          <Link to={"/"}>
            <Typography
              placeholder
              variant="h1"
              color="blue-gray"
              className="mt-10 mb-10"
            >
              Iniciar Sesión
            </Typography>
          </Link>
          <Typography
            placeholder
            color="gray"
            className="mt-1 font-normal text-center"
          >
            ¡Hola de nuevo! Introduce tus credenciales para iniciar sesión.
          </Typography>
          <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
            <div className="mb-1 flex flex-col gap-6">
              <Typography
                placeholder
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Correo
              </Typography>
              <Input
                crossOrigin
                size="lg"
                placeholder="nombre.apellidoID@potros.itson.edu.mx"
                className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
                labelProps={{
                  className: "before:content-none after:content-none",
                }}
              />
              <Typography
                placeholder
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Contraseña
              </Typography>
              <Input
                crossOrigin
                type="password"
                size="lg"
                placeholder="********"
                className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
                labelProps={{
                  className: "before:content-none after:content-none",
                }}
              />
            </div>
            <Button placeholder className="mt-6" fullWidth>
              Ingresar
            </Button>
            <Typography
              placeholder
              color="gray"
              className="mt-4 text-center font-normal"
            >
              Aún no tienes una cuenta?{" "}
              <Link to={"/signup"}>
                <a href="#" className="font-medium text-gray-900">
                  Registrarse
                </a>
              </Link>
            </Typography>
          </form>
        </Card>
      </div>
    </div>
  );
}
