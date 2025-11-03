import { Card, Button, Typography } from "@material-tailwind/react";
import { Link } from "react-router-dom";
import Hobbies from "../components/Hobbies";
import Intereses from "../components/Interests";

export default function AboutYou() {
  return (
    <div className="flex justify-center items-start max-h-screen text-white p-4 mt-5 lg:mt-20">
      <div className="w-full max-w-md lg:max-w-2xl mt-10">
        <Card
          placeholder
          color="transparent"
          shadow={false}
          className="flex flex-col items-center "
        >
          <Typography
            placeholder
            variant="h1"
            color="blue-gray"
            className="mt-10 mb-10 text-center"
          >
            Tus Intereses
          </Typography>
          <Typography
            placeholder
            color="gray"
            className="mt-1 font-normal text-center"
          >
            ¡Cuéntanos lo que te gusta!, Lo que te hace único/a.
          </Typography>
          <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
            <div className="mb-1 flex flex-col gap-6">
              <Typography
                placeholder
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Hobbies
              </Typography>
              <div className="w-90">
                <Hobbies></Hobbies>
              </div>
              <Typography
                placeholder
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Intereses
              </Typography>
              <div className="w-90">
                <Intereses></Intereses>
              </div>
            </div>
            <hr className="border-t border-gray-400 shadow-sm my-8 rounded" />
            <Link to={"/home"}>
              <Button placeholder className="mt-6" fullWidth>
                Continuar
              </Button>
            </Link>
            <Typography
              placeholder
              color="gray"
              className="mt-4 text-center font-normal"
            >
              Te gustaria sugerir?{" "}
              <a
                href="https://docs.google.com/forms/d/e/1FAIpQLScx0_Rcu4f4MvNpdlrEVS2xkys_UhdLlJWhL8Rfo27reV3mfQ/viewform?usp=dialog"
                className="font-medium text-gray-900"
              >
                Intereses
              </a>
            </Typography>
          </form>
        </Card>
      </div>
    </div>
  );
}
