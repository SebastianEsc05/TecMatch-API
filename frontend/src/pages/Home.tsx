import { Button, Typography } from "@material-tailwind/react";
import { Link } from "react-router-dom";
import Footer from "../components/Footer";
import Stats from "../components/Stats";
import Team from "../components/Team";
import HomeCards from "../components/HomeCards";
import { ChevronUpIcon } from "@heroicons/react/24/outline";
import React, { useEffect } from "react";

interface KpiCardPropsType {
  title: string;
  count: number;
  icon: React.ReactNode;
}

export default function Home() {
  const baseURL = import.meta.env.VITE_API_URL;
  const [data, setData] = React.useState<KpiCardPropsType[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const users = await fetch(`${baseURL}/api/app/registered-users`).then(
          (res) => res.json()
        );
        const matches = await fetch(
          `${baseURL}/api/app/registered-matches`
        ).then((res) => res.json());
        const likes = await fetch(`${baseURL}/api/app/registered-likes`).then(
          (res) => res.json()
        );
        const intereses = await fetch(
          `${baseURL}/api/app/registered-hobbies-intereses`
        ).then((res) => res.json());

        setData([
          {
            title: "Estudiantes Registrados",
            count: users,
            icon: (
              <ChevronUpIcon
                strokeWidth={4}
                className="w-3 h-3 text-green-500"
              />
            ),
          },
          {
            title: "Matches Registrados",
            count: matches,
            icon: (
              <ChevronUpIcon
                strokeWidth={4}
                className="w-3 h-3 text-green-500"
              />
            ),
          },
          {
            title: "Likes Registrados",
            count: likes,
            icon: (
              <ChevronUpIcon
                strokeWidth={4}
                className="w-3 h-3 text-green-500"
              />
            ),
          },
          {
            title: "Hobbies e Intereses",
            count: intereses,
            icon: (
              <ChevronUpIcon
                strokeWidth={4}
                className="w-3 h-3 text-green-500"
              />
            ),
          },
        ]);
      } catch (err) {
        console.error(err);
      }
    };

    fetchData();
  }, []);

  return (
    <>
      <section className="w-[95%] lg:w-[70%] mx-auto">
        <div className="p-10 rounded-l-xl border border-blue-gray-100 bg-[url('https://www.material-tailwind.com/image/gradient-bg-1.png')] rounded-xl bg-no-repeat lg:bg-contain bg-cover bg-right">
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
            <Button
              placeholder={""}
              variant="outlined"
              className="flex-shrink-0"
            >
              Explorar ahora
            </Button>
          </Link>
        </div>
        <Stats data={data} />
        <HomeCards></HomeCards>
        <Team></Team>
      </section>
      <Footer></Footer>
    </>
  );
}
