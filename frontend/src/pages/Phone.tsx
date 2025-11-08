import {
  Card,
  Input,
  Checkbox,
  Button,
  Typography,
} from "@material-tailwind/react";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import DatePicker from "../components/DatePicker";

export default function Phone() {
  const navigate = useNavigate();
  const [phone, setPhone] = useState("");
  const [birthdate, setBirthdate] = useState<Date | undefined>(undefined);
  const phoneRegex = /^\d{10}$/;
  const today = new Date();
  const minBirthdate = new Date(
    today.getFullYear() - 17,
    today.getMonth(),
    today.getDate()
  );

  const handlePhone = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!phone.trim() || !birthdate) {
      alert("Ningún campo debe estar vacío");
      return;
    }

    if (!phoneRegex.test(phone)) {
      alert("El teléfono debe tener 10 dígitos");
      return;
    }

    if (birthdate > minBirthdate) {
      alert("Debes tener al menos 17 años");
      return;
    }

    sessionStorage.setItem("phone", phone);
    sessionStorage.setItem("birthdate", birthdate.toISOString());
    navigate("/userInformation");
  };

  return (
    <div className="flex justify-center items-start max-h-screen text-white p-4 mt-10">
      <div className="w-full max-w-md lg:max-w-2xl mt-10">
        <Card
          placeholder={""}
          color="transparent"
          shadow={false}
          className="flex flex-col items-center "
        >
          <Link to={"/"}>
            <Typography
              placeholder={""}
              variant="h1"
              color="blue-gray"
              className="mt-10 mb-10"
            >
              Teléfono
            </Typography>
          </Link>
          <Typography
            placeholder={""}
            color="gray"
            className="mt-1 font-normal text-center"
          >
            ¡Muy bien! Ahora introduce los siguientes datos.
          </Typography>
          <form
            onSubmit={handlePhone}
            className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96"
          >
            <div className="mb-1 flex flex-col gap-6">
              <Typography
                placeholder={""}
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Teléfono
              </Typography>
              <Input
                crossOrigin={""}
                size="lg"
                placeholder="644#######"
                className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
                onChange={(e) => setPhone(e.target.value)}
                labelProps={{
                  className: "before:content-none after:content-none",
                }}
              />
              <Typography
                placeholder={""}
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Fecha Nacimiento
              </Typography>
              <DatePicker
                label="Seleccionar"
                value={birthdate}
                onChange={setBirthdate}
              />
            </div>
            <Button type="submit" placeholder={""} className="mt-6" fullWidth>
              Continuar
            </Button>
          </form>
        </Card>
      </div>
    </div>
  );
}
