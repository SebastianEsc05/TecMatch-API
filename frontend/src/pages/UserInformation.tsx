import {
  Card,
  Input,
  Checkbox,
  Button,
  Typography,
} from "@material-tailwind/react";
import { Select, Option } from "@material-tailwind/react";
import { Textarea } from "@material-tailwind/react";
import { Link } from "react-router-dom";

export default function UserInfromation() {
  return (
    <div className="flex justify-center items-start max-h-screen text-white p-4 lg:mt-10">
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
            Información Personal
          </Typography>
          <Typography
            placeholder
            color="gray"
            className="mt-1 font-normal text-center"
          >
            ¡Perfecto!, Ahora hablanos más sobre ti.
          </Typography>
          <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
            <div className="mb-1 flex flex-col gap-6">
              <Typography
                placeholder
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Sexo
              </Typography>
              <div className="w-90">
                <Select placeholder label="Selecionar">
                  <Option>Hombre</Option>
                  <Option>Mujer</Option>
                  <Option>Prefiero no decirlo</Option>
                </Select>
              </div>
              <Typography
                placeholder
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Carrera
              </Typography>
              <div className="w-90">
                <Select placeholder label="Seleccionar">
                  <Option>Ingeniería en Software</Option>
                  <Option>Ingeniería Química</Option>
                  <Option>Ingeniería en Mecatrónica</Option>
                  <Option>Ingeniería en Manufactura</Option>
                  <Option>Ingeniería en Logística</Option>
                  <Option>Ingeniería Industrial y de Sistemas</Option>
                  <Option>Ingeniería en Electrónica</Option>
                  <Option>Ingeniería Electromecánica</Option>
                  <Option>Ingeniería Civil</Option>
                  <Option>Ingeniería en Ciencias Ambientales</Option>
                  <Option>Ingeniería en Biotecnología</Option>
                  <Option>Ingeniería en Biosistemas</Option>
                  <Option>Medicina Veterinaria y Zootecnia</Option>
                  <Option>Licenciatura en Tecnología de Alimentos</Option>
                  <Option>Licenciatura en Psicología</Option>
                  <Option>Licenciatura en Mercadotecnia</Option>
                  <Option>Licenciatura en Gastronomía</Option>
                  <Option>Licenciatura en Enfermería</Option>
                  <Option>Licenciatura en Emprendimiento e Innovación</Option>
                  <Option>
                    Licenciatura en Educación Inicial y Gestión de Instituciones
                  </Option>
                  <Option>Licenciatura en Educación Infantil</Option>
                  <Option>
                    Licenciatura en Educación Artística y Gestión Cultural
                  </Option>
                  <Option>Licenciatura en Economía y Finanzas</Option>
                  <Option>Licenciatura en Derecho</Option>
                  <Option>Licenciatura en Diseño Gráfico</Option>
                  <Option>
                    Licenciatura en Dirección de la Cultura Física y el Deporte
                  </Option>
                  <Option>Licenciatura en Contaduría Pública</Option>
                  <Option>Licenciatura en Ciencias del Ejercicio Físico</Option>
                  <Option>Licenciatura en Ciencias de la Educación</Option>
                  <Option>Licenciatura en Arquitectura</Option>
                  <Option>Licenciatura en Administración Estratégica</Option>
                  <Option>
                    Licenciatura en Administración de Empresas Turísticas
                  </Option>
                  <Option>Licenciatura en Administración</Option>
                  <Option>Profesional Asociado en Desarrollo Infantil</Option>
                </Select>
              </div>
              <Typography
                placeholder
                variant="h6"
                color="blue-gray"
                className="-mb-3"
              >
                Biografía
              </Typography>
              <div className="max-w-96">
                <Textarea label="Descripción" />
              </div>
            </div>
            <Link to={"/home"}>
              <Button placeholder className="mt-6" fullWidth>
                Continuar
              </Button>
            </Link>
          </form>
        </Card>
      </div>
    </div>
  );
}
