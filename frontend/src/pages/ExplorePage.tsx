import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";


const ExplorePage = () => {
  
  return (
    <Section>
      <H1>Explorar</H1>
      <Link to={"/profilePage"}>
        <Button>Perfil</Button>
      </Link>
      <Link to={"/matchesPage"}>
        <Button>Matches</Button>
      </Link>
    </Section>
  );
};

export default ExplorePage;
