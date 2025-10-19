import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import H2 from "../componets/H2";
import Label from "../componets/Label";
import Input from "../componets/Input";
import Button from "../componets/Button";


const LoginPage = () => {

  return (
    
    <Section>
      <Link to={"/"}>
        <H1>Tec-Match</H1>
      </Link>
      <H2>Iniciar Sesión</H2>

      <form>
        <div className="mb-4 text-center">
          <Label htmlFor="username">Usuario</Label>
          <Input id="username" type="text" placeholder="Correo electrónico" />
        </div>

        <div className="mb-4 text-center">
          <Label htmlFor="password">Contraseña</Label>
          <Input
            id="password"
            type="password"
            placeholder="Ingresa tu contraseña"
          />
        </div>

        <Link to={"/explorePage"}>
          <Button>Ingresar</Button>
        </Link>
      </form>

      <div className="relative w-full border-b-2 border-black my-6">
        <div className="absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 bg-app-pink w-8 h-8 border-2 border-black rounded-full" />
      </div>

      <Link to={"/registerPage"}>
        <Button>¡Regístrate!</Button>
      </Link>
    </Section>
  );
};

export default LoginPage;
