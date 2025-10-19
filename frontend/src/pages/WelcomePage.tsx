import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import H2 from "../componets/H2";
import H3 from "../componets/H3";
import Button from "../componets/Button";

const WelcomePage = () => {
  
  return (
    <Section>
      <div className="h-25"></div>
      <H1>Tec-Match</H1>
      <H2>Bienvenido!</H2>
      <Link to={"/loginPage"}>
        <Button>Comenzar</Button>
      </Link>

      <H3>Conecta, comparte y descubre oportunidades con tus compañeros.</H3>

    </Section>
  );
};

export default WelcomePage;
