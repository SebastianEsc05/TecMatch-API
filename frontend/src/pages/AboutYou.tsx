import { Card, Button, Typography } from "@material-tailwind/react";
import Hobbies from "../components/Hobbies";
import Intereses from "../components/Interests";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export default function AboutYou() {
  const navigate = useNavigate();
  const [hobbies, setHobbie] = useState<string[]>([]);
  const [interests, setInterests] = useState<string[]>([]);
  const baseURL = import.meta.env.VITE_API_URL;

  const handleAboutYou = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (hobbies.length == 0) {
      alert("Debes seleccionar al menos un hobby");
      return;
    }
    if (interests.length == 0) {
      alert("Debes seleccionar al menos un interes");
      return;
    }

    const email = sessionStorage.getItem("email");
    const password = sessionStorage.getItem("password");
    const sex = sessionStorage.getItem("sex");
    const degree = sessionStorage.getItem("degree");
    const description = sessionStorage.getItem("description");
    const name = sessionStorage.getItem("name");
    const phone = sessionStorage.getItem("phone");
    const birthdate = sessionStorage.getItem("birthdate");
    

    try {
      const response = await fetch(`${baseURL}/api/auth/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          fecha_nacimiento: birthdate,
          telefono: phone,
          nombre: name,
          correo: email,
          contrasenia: password,
          sexo: sex,
          carrera: degree,
          descripcion: description,
          hobbies: hobbies,
          intereses: interests,
        }),
      });
      const data = await response.json();
      if (!response.ok) {
        alert(data.message);
      } else {
        const response = await fetch(`${baseURL}/api/auth/login`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ correo: email, contrasenia: password }),
        });
        const data = await response.json();
        if (!response.ok) {
          alert(data.message);
        }
        sessionStorage.setItem("token", data.token);
        navigate("/home");
      }
    } catch (err) {
      alert("Error de conexion con el servidor");
      return;
    }
  };

  return (
    <div className="flex justify-center items-start max-h-screen text-white p-4 mt-5 lg:mt-20">
      <div className="w-full max-w-md lg:max-w-2xl mt-10">
        <Card
          placeholder={""}
          color="transparent"
          shadow={false}
          className="flex flex-col items-center "
        >
          <Typography
            placeholder={""}
            variant="h1"
            color="blue-gray"
            className="mt-10 mb-10 text-center"
          >
            Tus Intereses
          </Typography>
          <Typography
            placeholder={""}
            color="gray"
            className="mt-1 font-normal text-center"
          >
            ¡Cuéntanos lo que te gusta!, Lo que te hace único/a.
          </Typography>
          <form
            onSubmit={handleAboutYou}
            className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96"
          >
            <div className="mb-1 flex flex-col gap-6">
              <Typography
                placeholder={""}
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Hobbies
              </Typography>
              <div className="w-90">
                <Hobbies items={hobbies} onChange={setHobbie} />
              </div>
              <Typography
                placeholder={""}
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Intereses
              </Typography>
              <div className="w-90">
                <Intereses items={interests} onChange={setInterests} />
              </div>
            </div>
            <hr className="border-t border-gray-400 shadow-sm my-8 rounded" />
            <Button type="submit" fullWidth placeholder={""} className="mt-6">
              Continuar
            </Button>
            <Typography
              placeholder={""}
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
