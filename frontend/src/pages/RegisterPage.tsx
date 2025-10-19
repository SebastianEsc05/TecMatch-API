import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import H2 from "../componets/H2";
import Label from "../componets/Label";
import Input from "../componets/Input";
import Button from "../componets/Button";

const RegisterPage = () => {
  return (
    <Section>
      <H1>Registro</H1>
      <H2>Tec-Match</H2>

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
          <Button>Registrar</Button>
        </Link>

      </form>
    </Section>
  );
};

export default RegisterPage;
