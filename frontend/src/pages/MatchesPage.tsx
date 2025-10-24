import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";

const MatchesPage = () => {
  
  return (
    <Section>
      <H1>Matches</H1>

      <div
        className="mt-12 mb-2 m-3 font-app-font bg-white text-black border-4 border-black py-4 px-6 
        text-2xl shadow-[-6px_6px_0px_black] transition-transform duration-100 ease-in-out flex gap-4"
      >
        <div className="flex items-center">
          <Button>
            <img
              src="/user-svgrepo-com.svg"
              alt="user"
              width={150}
              className="rounded-full"
            />
          </Button>
        </div>

        <div className="flex items-center justify-center gap-10 w-full">
          <h1>Sebas Escalante</h1>
          <Link to={"/chatPage"}>
            <Button>
              <img
                src="/chat-round-dots-svgrepo-com.svg"
                alt="chat"
                width={40}
              />
            </Button>
          </Link>
        </div>
      </div>

      <div
        className="mt-12 mb-2 m-3 font-app-font bg-white text-black border-4 border-black py-4 px-6 
        text-2xl shadow-[-6px_6px_0px_black] transition-transform duration-100 ease-in-out flex gap-4"
      >
        <div className="flex items-center">
          <Button>
            <img
              src="/user-svgrepo-com.svg"
              alt="user"
              width={150}
              className="rounded-full"
            />
          </Button>
        </div>

        <div className="flex items-center justify-center gap-10 w-full">
          <h1>Martin Bernal</h1>
          <Link to={"/chatPage"}>
            <Button>
              <img
                src="/chat-round-dots-svgrepo-com.svg"
                alt="chat"
                width={40}
              />
            </Button>
          </Link>
        </div>
      </div>

      <Link to={"/explorePage"}>
        <Button>&lt;</Button>
      </Link>
    </Section>
  );
};

export default MatchesPage;
