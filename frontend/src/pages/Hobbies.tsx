import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";
import Category from "../componets/Category";

interface HobbiesProps {
  type: string;
}


const Hobbies = ({type}:HobbiesProps) => {
  if (type == "update"){
    return (
      <Section>
        <H1>Hobbies</H1>

        <div className="mb-4 text-center">
          <Category>Campus Centro</Category>
          <Category>Club Lectura</Category>
        </div>
        <div className="mb-4 text-center">
          <Category>Asesorias</Category>
          <Category>Teatro</Category>
          <Category>Alto Rendimiento</Category>
        </div>
        <div className="mb-4 text-center">
          <Category>SA</Category>
          <Category>Capus Nainari</Category>
          <Category>Mobilidad</Category>
        </div>
        <div className="mb-4 text-center">
          <Category>eSports</Category>
          <Category>Personal Academico</Category>
        </div>

        <Link to="/profilePage">
          <Button>&lt;</Button>
        </Link>
      </Section>
    );
  }
  return (
    <Section>
      <H1>Hobbies</H1>

      <div className="mb-4 text-center">
        <Category>Campus Centro</Category>
        <Category>Club Lectura</Category>
      </div>
      <div className="mb-4 text-center">
        <Category>Asesorias</Category>
        <Category>Teatro</Category>
        <Category>Alto Rendimiento</Category>
      </div>
      <div className="mb-4 text-center">
        <Category>SA</Category>
        <Category>Capus Nainari</Category>
        <Category>Mobilidad</Category>
      </div>
      <div className="mb-4 text-center">
        <Category>eSports</Category>
        <Category>Personal Academico</Category>
      </div>

      <Link to="/nextRegisterPage">
        <Button>&lt;</Button>
      </Link>
      <Link to="/interestsPage">
        <Button>Continuar</Button>
      </Link>
    </Section>
  );
};

export default Hobbies;
