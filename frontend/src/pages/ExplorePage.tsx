import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import Button from "../componets/Button";


const ExplorePage = () => {
  
  return (
    <Section>
      <H1>Explorar</H1>

      <div
        className="mt-12 mb-2 m-3 font-app-font bg-white text-black border-4 border-black py-4 px-6 
        text-2xl shadow-[-6px_6px_0px_black] transition-transform duration-100 ease-in-out flex gap-4"
      >
        <div className="flex items-center">
          <Button>
            <img
              src="src/assets/img/user-svgrepo-com.svg"
              alt="user"
              width={300}
              className="rounded-full"
            />
          </Button>
        </div>

        <div className="flex flex-col justify-between w-full">
          <p className="mt-10 text-xl">
            David Gay David Gay David Gay David Gay David Gay David Gay
          </p>

          <div className="flex justify-end gap-3">
            <Button>
              <img
                src="src/assets/img/like-svgrepo-com.svg"
                alt="like"
                width={25}
              />
            </Button>
            <Button>
              <img
                src="src/assets/img/dislike-svgrepo-com.svg"
                alt="dislike"
                width={25}
              />
            </Button>
          </div>
        </div>
      </div>

      <div
        className="mt-12 mb-2 m-3 font-app-font bg-white text-black border-4 border-black py-4 px-6 
        text-2xl shadow-[-6px_6px_0px_black] transition-transform duration-100 ease-in-out flex gap-4"
      >
        <div className="flex items-center">
          <Button>
            <img
              src="src/assets/img/user-svgrepo-com.svg"
              alt="user"
              width={300}
              className="rounded-full"
            />
          </Button>
        </div>

        <div className="flex flex-col justify-between w-full">
          <p className="mt-10 text-xl">
            David Gay David Gay David Gay David Gay David Gay David Gay
          </p>

          <div className="flex justify-end gap-3">
            <Button>
              <img
                src="src/assets/img/like-svgrepo-com.svg"
                alt="like"
                width={25}
              />
            </Button>
            <Button>
              <img
                src="src/assets/img/dislike-svgrepo-com.svg"
                alt="dislike"
                width={25}
              />
            </Button>
          </div>
        </div>
      </div>

      <Link to={"/matchesPage"}>
        <Button>Matches</Button>
      </Link>
      <Link to={"/profilePage"}>
        <Button>Perfil</Button>
      </Link>
    </Section>
  );
};

export default ExplorePage;
