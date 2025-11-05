import {
  Card,
  Input,
  Checkbox,
  Button,
  Typography,
} from "@material-tailwind/react";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";

export default function Signup() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [terms, setTerms] = useState(false);
  const emailRegex = /^[A-Za-z0-9._%+-]+@potros\.itson\.edu\.mx$/;
  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

  const handleRegister = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!email.trim() || !password.trim()) {
      alert("Ningun campo debe estar vacio");
      return;
    }
    if (!emailRegex.test(email)) {
      alert("Solo correo @potros.itson.edu.mx");
      return;
    }
    if (!passwordRegex.test(password)) {
      alert(
        "Contraseña de al menos 8 caracteres, al menos una mayúscula, una minúscula y un número"
      );
      return;
    }
    if (!terms) {
      alert("Los terminos y condiciones deben ser aceptados");
      return;
    }
    sessionStorage.setItem("email", email);
    sessionStorage.setItem("password", password);
    navigate("/userInformation");
  };

  return (
    <div className="flex justify-center items-start max-h-screen text-white p-4 mt-10">
      <div className="w-full max-w-md lg:max-w-2xl mt-10">
        <Card
          placeholder
          color="transparent"
          shadow={false}
          className="flex flex-col items-center "
        >
          <Link to={"/"}>
            <Typography
              placeholder
              variant="h1"
              color="blue-gray"
              className="mt-10 mb-10"
            >
              Registrarse
            </Typography>
          </Link>
          <Typography
            placeholder
            color="gray"
            className="mt-1 font-normal text-center"
          >
            ¡Mucho gusto! Introduce tus datos para registrarte.
          </Typography>
          <form
            onSubmit={handleRegister}
            className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96"
          >
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
                onChange={(e) => setEmail(e.target.value)}
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
                onChange={(e) => setPassword(e.target.value)}
                className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
                labelProps={{
                  className: "before:content-none after:content-none",
                }}
              />
            </div>
            <Checkbox
              crossOrigin
              onChange={(e) => setTerms(e.target.checked)}
              label={
                <Typography
                  placeholder
                  variant="small"
                  color="gray"
                  className="flex items-center font-normal"
                >
                  Acepto los
                  <a
                    href="#"
                    className="font-medium transition-colors hover:text-gray-900"
                  >
                    &nbsp;Términos y Condiciones
                  </a>
                </Typography>
              }
              containerProps={{ className: "-ml-2.5" }}
            />
            <Button type="submit" placeholder className="mt-6" fullWidth>
              Continuar
            </Button>
            <Typography
              placeholder
              color="gray"
              className="mt-4 text-center font-normal"
            >
              Ya tienes una cuenta?{" "}
              <Link to={"/login"}>
                <a href="#" className="font-medium text-gray-900">
                  Iniciar Sesión
                </a>
              </Link>
            </Typography>
          </form>
        </Card>
      </div>
    </div>
  );
}
