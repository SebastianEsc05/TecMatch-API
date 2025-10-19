import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";

const ChatPage = () => {
  
  return (
    <Section>
      <H1>Mis Chats</H1>
      <Link to={"/matchesPage"}>
        <Button>Regresar</Button>
      </Link>
    </Section>
  );

};

export default ChatPage;
