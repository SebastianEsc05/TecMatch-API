import Input from "../componets/Input";
import Button from "../componets/Button";
import { Link } from "react-router-dom";


const LoginPage = () => {

  return (

    <div className="flex justify-center items-start min-h-screen text-white text-center p-4">
      <div className="w-full max-w-md lg:max-w-2xl">
        <h1 className="text-7xl mt-14 text-app-blue mb-4 lg:text-9xl [text-shadow:-6px_6px_2px_black]">
          Tec-Match
        </h1>
        <h2 className="font-app-font text-5xl text-white mb-8 [text-shadow:-6px_6px_2px_black]">
          Iniciar Sesión
        </h2>

        <form>
          <div className="mb-4 text-center">
            <label htmlFor="username" className="text-black text-3xl block mb-2">
              Usuario
            </label>
            <Input id="username" type="text" placeholder="Correo electrónico" />
          </div>

          <div className="mb-4 text-center">
            <label htmlFor="password" className="text-black text-3xl block mb-2">
              Contraseña
            </label>
            <Input id="password" type="password" placeholder="Ingresa tu contraseña"/>
          </div>

          <Button>
            Ingresar
          </Button>
        </form>
        
        <div className="relative w-full border-b-2 border-black my-6">
          <div className="absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 bg-app-pink w-8 h-8 border-2 border-black rounded-full" />
        </div>
        
        <Link to={"/registerPage"}>
          <Button>
            ¡Regístrate!
          </Button>
        </Link>
      </div>
    </div>
  );
};

export default LoginPage;
