import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";

const Profile = () => {
  return (
    <Section>
      <H1>Perfil</H1>
      <Link to={"/explorePage"}>
        <Button>&lt;</Button>
      </Link>
      <Link to={"/updateInterestsPage"}>
        <Button>Actualizar Intereses</Button>
      </Link>
      <Link to={"/updateHobbiesPage"}>
        <Button>Actualizar Hobbies</Button>
      </Link>
      <Link to={"/loginPage"}>
        <Button>Cerrar Sesion</Button>
      </Link>
    </Section>
  );
};

export default Profile;
