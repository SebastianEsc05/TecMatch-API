import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";
import Category from "../componets/Category";

interface InterestsProps {
  type: string;
}

const Interests = ({ type }: InterestsProps) => {
  if (type == "update") {
    return (
      <Section>
        <H1>Intereses</H1>
        <Link to="/profilePage">
          <Button>&lt;</Button>
        </Link>
      </Section>
    );
  }
  return (
    <Section>
      <H1>Intereses</H1>

      <div className="mb-4 text-center">
        <Category>Musica</Category>
        <Category>Deportes</Category>
      </div>
      <div className="mb-4 text-center">
        <Category>Videojuegos</Category>
        <Category>Bailar</Category>
        <Category>Ajedrez</Category>
      </div>
      <div className="mb-4 text-center">
        <Category>Cocinar</Category>
        <Category>Peliculas</Category>
        <Category>Tiro al Blanco</Category>
      </div>
      <div className="mb-4 text-center">
        <Category>Programar</Category>
        <Category>Cantar</Category>
      </div>

      <Link to="/hobbiesPage">
        <Button>&lt;</Button>
      </Link>
      <Link to="/explorePage">
        <Button>Registar</Button>
      </Link>
    </Section>
  );
};

export default Interests;
