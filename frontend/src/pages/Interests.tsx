import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";

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
