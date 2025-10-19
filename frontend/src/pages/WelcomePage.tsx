import { Link } from "react-router-dom";

const WelcomePage = () => {
  return (
    <div className="flex justify-center items-start min-h-screen text-white text-center p-4">
      <div className="w-full max-w-md lg:max-w-2xl">
        <Link to={"/loginPage"}>
          <button className="font-app-font bg-white text-black border-4 border-black rounded-full py-2 px-8 text-2xl mt-8 mb-8 shadow-[-6px_6px_0px_black] transition-transform duration-100 ease-in-out hover:translate-x-0.5 hover:translate-y-0.5 hover:shadow-[-2px_2px_0px_black]">
            Comenzar
          </button>
        </Link>
      </div>
    </div>
  );
};

export default WelcomePage;
