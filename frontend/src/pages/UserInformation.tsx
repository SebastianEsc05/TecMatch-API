import { Card, Button, Typography } from "@material-tailwind/react";
import { Select, Option } from "@material-tailwind/react";
import { Textarea } from "@material-tailwind/react";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export default function UserInfromation() {
  const navigate = useNavigate();
  const [sex, setSex] = useState("");
  const [degree, setDegree] = useState("");
  const [description, setDescription] = useState("");

  const handleUserInformation = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!sex.trim()) {
      alert("Debes seleccionar un sexo");
      return;
    }
    if (!degree.trim()) {
      alert("Debes seleccionar una carrera");
      return;
    }
    if (!description.trim()) {
      alert("La biografia no puede estar vacia");
      return;
    }
    sessionStorage.setItem("sex", sex);
    sessionStorage.setItem("degree", degree);
    sessionStorage.setItem("description", description);
    navigate("/aboutYou");
  };

  return (
    <div className="flex justify-center items-start max-h-screen text-white p-4 lg:mt-20">
      <div className="w-full max-w-md lg:max-w-2xl">
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
            Información Personal
          </Typography>
          <Typography
            placeholder={""}
            color="gray"
            className="mt-1 font-normal text-center"
          >
            ¡Perfecto!, Ahora hablanos más sobre ti.
          </Typography>
          <form
            onSubmit={handleUserInformation}
            className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96"
          >
            <div className="mb-1 flex flex-col gap-6">
              <Typography
                placeholder={""}
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Sexo
              </Typography>
              <div className="w-90">
                <Select
                  placeholder={""}
                  onChange={(value) => setSex(value || "")}
                  label="Selecionar"
                >
                  <Option value="Hombre">Hombre</Option>
                  <Option value="Mujer">Mujer</Option>
                  <Option value="Prefiero no decirlo">
                    Prefiero no decirlo
                  </Option>
                </Select>
              </div>
              <Typography
                placeholder={""}
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Carrera
              </Typography>
              <div className="w-90">
                <Select
                  placeholder={""}
                  onChange={(value) => setDegree(value || "")}
                  label="Seleccionar"
                >
                  <Option value="Ingeniería en Software">
                    Ingeniería en Software
                  </Option>
                  <Option value="Ingeniería Química">Ingeniería Química</Option>
                  <Option value="Ingeniería en Mecatrónica">
                    Ingeniería en Mecatrónica
                  </Option>
                  <Option value="Ingeniería en Manufactura">
                    Ingeniería en Manufactura
                  </Option>
                  <Option value="Ingeniería en Logística">
                    Ingeniería en Logística
                  </Option>
                  <Option value="Ingeniería Industrial y de Sistemas">
                    Ingeniería Industrial y de Sistemas
                  </Option>
                  <Option value="Ingeniería en Electrónica">
                    Ingeniería en Electrónica
                  </Option>
                  <Option value="Ingeniería Electromecánica">
                    Ingeniería Electromecánica
                  </Option>
                  <Option value="Ingeniería Civil">Ingeniería Civil</Option>
                  <Option value="Ingeniería en Ciencias Ambientales">
                    Ingeniería en Ciencias Ambientales
                  </Option>
                  <Option value="Ingeniería en Biotecnología">
                    Ingeniería en Biotecnología
                  </Option>
                  <Option value="Ingeniería en Biosistemas">
                    Ingeniería en Biosistemas
                  </Option>
                  <Option value="Medicina Veterinaria y Zootecnia">
                    Medicina Veterinaria y Zootecnia
                  </Option>
                  <Option value="Licenciatura en Tecnología de Alimentos">
                    Licenciatura en Tecnología de Alimentos
                  </Option>
                  <Option value="Licenciatura en Psicología">
                    Licenciatura en Psicología
                  </Option>
                  <Option value="Licenciatura en Mercadotecnia">
                    Licenciatura en Mercadotecnia
                  </Option>
                  <Option value="Licenciatura en Gastronomía">
                    Licenciatura en Gastronomía
                  </Option>
                  <Option value="Licenciatura en Enfermería">
                    Licenciatura en Enfermería
                  </Option>
                  <Option value="Licenciatura en Emprendimiento e Innovación">
                    Licenciatura en Emprendimiento e Innovación
                  </Option>
                  <Option value="Licenciatura en Educación Inicial y Gestión de Instituciones">
                    Licenciatura en Educación Inicial y Gestión de Instituciones
                  </Option>
                  <Option value="Licenciatura en Educación Infantil">
                    Licenciatura en Educación Infantil
                  </Option>
                  <Option value="Licenciatura en Educación Artística y Gestión Cultural">
                    Licenciatura en Educación Artística y Gestión Cultural
                  </Option>
                  <Option value="Licenciatura en Economía y Finanzas">
                    Licenciatura en Economía y Finanzas
                  </Option>
                  <Option value="Licenciatura en Derecho">
                    Licenciatura en Derecho
                  </Option>
                  <Option value="Licenciatura en Diseño Gráfico">
                    Licenciatura en Diseño Gráfico
                  </Option>
                  <Option value="Licenciatura en Dirección de la Cultura Física y el Deporte">
                    Licenciatura en Dirección de la Cultura Física y el Deporte
                  </Option>
                  <Option value="Licenciatura en Contaduría Pública">
                    Licenciatura en Contaduría Pública
                  </Option>
                  <Option value="Licenciatura en Ciencias del Ejercicio Físico">
                    Licenciatura en Ciencias del Ejercicio Físico
                  </Option>
                  <Option value="Licenciatura en Ciencias de la Educación">
                    Licenciatura en Ciencias de la Educación
                  </Option>
                  <Option value="Licenciatura en Arquitectura">
                    Licenciatura en Arquitectura
                  </Option>
                  <Option value="Licenciatura en Administración Estratégica">
                    Licenciatura en Administración Estratégica
                  </Option>
                  <Option value="Licenciatura en Administración de Empresas Turísticas">
                    Licenciatura en Administración de Empresas Turísticas
                  </Option>
                  <Option value="Licenciatura en Administración">
                    Licenciatura en Administración
                  </Option>
                  <Option value="Profesional Asociado en Desarrollo Infantil">
                    Profesional Asociado en Desarrollo Infantil
                  </Option>
                </Select>
              </div>
              <Typography
                placeholder={""}
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Biografía
              </Typography>
              <div className="max-w-96">
                <Textarea
                  value={description}
                  onChange={(e: React.ChangeEvent<HTMLTextAreaElement>) =>
                    setDescription(e.target.value || "")
                  }
                  label="Descripción"
                />
              </div>
            </div>
            <Button placeholder={""} type="submit" className="mt-6" fullWidth>
              Continuar
            </Button>
          </form>
        </Card>
      </div>
    </div>
  );
}
