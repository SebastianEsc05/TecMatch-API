import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";

const MatchesPage = () => {
  
  return (
    <Section>
      <H1>Matches</H1>
      <Link to={"/explorePage"}>
        <Button>&lt;</Button>
      </Link>
      <Link to={"/chatPage"}>
        <Button>Chat</Button>
      </Link>
    </Section>
  );
};

export default MatchesPage;
