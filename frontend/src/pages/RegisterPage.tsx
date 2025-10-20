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
          <Label htmlFor="name">Nombre</Label>
          <Input id="name" type="text" placeholder="Escribe tu nombre" />
        </div>

        <div className="mb-4 text-center">
          <Label htmlFor="username">Correo</Label>
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

        <div className="mt-14 relative w-full border-b-2 border-black my-6"></div>

        <Link to={"/loginPage"}>
          <Button>&lt;</Button>
        </Link>

        <Link to={"/nextRegisterPage"}>
          <Button>Continuar</Button>
        </Link>
      </form>
    </Section>
  );
};

export default RegisterPage;
