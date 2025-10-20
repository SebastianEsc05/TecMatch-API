const LoginPage = () => {
  return (
    <div className="flex justify-center items-start min-h-screen text-white text-center p-4">
      <div className="w-full max-w-md">
        <h1
          className="
          text-8xl           
          text-app-blue     
          mb-4               
          [text-shadow:-6px_6px_2px_black] 
        "
        >
          Tec-Match
        </h1>

        <h2
          className="
          font-app-font
          text-5xl
          text-white
          mb-8               
          [text-shadow:-6px_6px_2px_black]
        "
        >
          Iniciar sesión
        </h2>

        <form>
          <div className="mb-4 text-center">
            <label
              htmlFor="username"
              className="
              text-black     
              text-3xl       
              block          
              mb-2          
            "
            >
              Usuario
            </label>
            <input
              id="username"
              type="text"
              placeholder="Correo electrónico"
              className="
                bg-black
                border-4 border-transparent
                rounded-[22px]     
                text-white
                text-left
                text-2xl
                p-2 px-4           
                w-full             
                transition-all
                focus:scale-105 focus:border-white focus:outline-none
              "
            />
          </div>

          <div className="mb-4 text-center">
            <label
              htmlFor="password"
              className="text-black 
              text-3xl 
              block 
              mb-2"
            >
              Contraseña
            </label>
            <input
              id="password"
              type="password"
              placeholder="ingresa tu contraseña"
              className="bg-black border-4 
              border-transparent rounded-[22px] 
              text-white 
              text-left 
              text-2xl 
              p-2 px-4 
              w-full 
              transition-all 
              focus:scale-105 
              focus:border-white
              focus:outline-none"
            />
          </div>

          <button
            type="submit"
            className="
            font-app-font bg-white text-black border-4 border-black rounded-full
            py-2 px-8 text-2xl mt-8 mb-8
            shadow-[-6px_6px_0px_black]
            transition-transform duration-100 ease-in-out
            hover:translate-x-0.5 hover:translate-y-0.5 hover:shadow-[-2px_2px_0px_black]
          "
          >
            Ingresar
          </button>
        </form>

        <div className="relative w-full border-b-2 border-black my-6">
          <div
            className="
            absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2
            bg-app-pink w-8 h-8 border-2 border-black rounded-full
          "
          ></div>
        </div>

        <button
          className="
          font-app-font bg-white text-black border-4 border-black rounded-full
          py-2 px-8 text-2xl mt-8 mb-8
          shadow-[-6px_6px_0px_black]
          transition-transform duration-100 ease-in-out
          hover:translate-x-0.5 hover:translate-y-0.5 hover:shadow-[-2px_2px_0px_black]
        "
        >
          ¡Regístrate!
        </button>
      </div>
    </div>
  );
};

export default LoginPage;
