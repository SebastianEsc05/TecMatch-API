import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";

interface HobbiesProps {
  type: string;
}


const Hobbies = ({type}:HobbiesProps) => {
  if (type == "update"){
    return (
      <Section>
        <H1>Hobbies</H1>
        <Link to="/profilePage">
          <Button>&lt;</Button>
        </Link>
      </Section>
    );
  }
  return (
    <Section>
      <H1>Hobbies</H1>
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
