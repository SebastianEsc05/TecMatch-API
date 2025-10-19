import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";
import Label from "../componets/Label";

const Profile = () => {
  return (
    <Section>
      <H1>Perfil</H1>

      <div className="mb-4 text-center">
        <Button>
          <img
            src="src\assets\img\user-svgrepo-com.svg"
            alt="icon"
            width="200"
          />
        </Button>
      </div>

      <div className="mb-2 text-center">
        <Label htmlFor="name">Nombre: </Label>
      </div>

      <div className="mb-2 text-center">
        <Label htmlFor="email">Correo: </Label>
      </div>
      <div className="relative w-full border-b-2 border-black my-6"></div>

      <Link to={"/updateInterestsPage"}>
        <Button>Actualizar Intereses</Button>
      </Link>
      <Link to={"/updateHobbiesPage"}>
        <Button>Actualizar Hobbies</Button>
      </Link>

      <div className="mb-4 text-center">
        <Link to={"/explorePage"}>
          <Button>&lt;</Button>
        </Link>
        <Link to={"/loginPage"}>
          <Button>Cerrar Sesion</Button>
        </Link>
      </div>
    </Section>
  );
};

export default Profile;
