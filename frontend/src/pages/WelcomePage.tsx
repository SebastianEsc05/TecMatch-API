import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import H2 from "../componets/H2";
import Button from "../componets/Button";

const WelcomePage = () => {
  
  return (

    <Section>
      <H1>Bienvenido!</H1>
      <H2>Tec-Match</H2>
      <Link to={"/loginPage"}>
        <Button>Comenzar</Button>
      </Link>
    </Section>
  );
};

export default WelcomePage;
