import {
  UserCircleIcon,
  Cog6ToothIcon,
  PowerIcon,
} from "@heroicons/react/24/solid";
import { Button, Typography } from "@material-tailwind/react";
import { Link } from "react-router-dom";
import Footer from "../components/Footer";
import Stats from "../components/Stats";
import Team from "../components/Team";
import HomeCards from "../components/HomeCards";

export default function Home() {
  return (
    <>
      <section className="w-[95%] lg:w-[70%] mx-auto">
        <div className="p-10 rounded-l-xl border border-blue-gray-100 bg-[url('/image/gradient-bg-1.png')] rounded-xl bg-no-repeat lg:bg-contain bg-cover bg-right">
          <Typography
            placeholder={""}
            variant="h1"
            color="blue-gray"
            className="font-bold mb-5 text-4xl lg:text-5xl"
          >
            ¡Bienvenido a Potro-NET!
          </Typography>
          <Typography
            placeholder={""}
            variant="h3"
            color="blue-gray"
            className="font-bold mb-2 text-xl lg:text-3xl"
          >
            Conecta con Estudiantes que Comparten tus Intereses
          </Typography>
          <Typography
            placeholder={""}
            className="mt-2 mb-6 !text-base font-normal text-gray-500"
          >
            Explora perfiles, descubre hobbies, crear matches y participa en
            conversaciones con tus amigos.
          </Typography>
          <Link to={"/explore"}>
            <Button placeholder variant="outlined" className="flex-shrink-0">
              Explorar ahora
            </Button>
          </Link>
        </div>
        <Stats></Stats>
        <HomeCards></HomeCards>
        <Team></Team>
      </section>

      <Footer></Footer>
    </>
  );
}
