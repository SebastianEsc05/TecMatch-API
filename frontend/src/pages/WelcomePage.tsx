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

      <div className="relative w-full border-b-2 border-black my-6">
        <div className="absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 bg-app-pink w-8 h-8 border-2 border-black rounded-full" />
      </div>

      <H3>Conecta, comparte y descubre oportunidades con tus compañeros.</H3>

      <Link to={"https://github.com/SebastianEsc05/TecMatch-API"}>
        <Button>
          <img
            src="/github-142-svgrepo-com.svg"
            alt="icon"
            width="20"
          />
        </Button>
      </Link>
      <Link to={"https://discord.gg/AH7JA4t6TV"}>
        <Button>
          <img
            src="/discord-alt-svgrepo-com.svg"
            alt="icon"
            width="20"
          />
        </Button>
      </Link>
    </Section>
  );
};

export default WelcomePage;
