import Pagination from "../components/Pagination";
import {
  Card,
  Menu,
  Input,
  Button,
  MenuList,
  MenuItem,
  CardBody,
  Typography,
  CardHeader,
  MenuHandler,
} from "@material-tailwind/react";
import { MagnifyingGlassIcon } from "@heroicons/react/24/solid";
import { Checkbox } from "@material-tailwind/react";
import { ChevronDownIcon } from "@heroicons/react/24/outline";

const TABLE_ROW = [
  {
    img: "https://images.unsplash.com/photo-1633332755192-727a05c4013d?auto=format&fit=crop&w=500&q=80",
    name: "Manuel Cortez",
    email: "manuel@gmail.com",
    description: "Ingeniero en Software y experto en estar jodiendo",
  },
  {
    img: "https://images.unsplash.com/photo-1633332755192-727a05c4013d?auto=format&fit=crop&w=500&q=80",
    name: "Sebastián Escalante",
    email: "sebas@gmail.com",
    description: "Ingeniero en Software y experto en estar jodiendo",
  },
  {
    img: "https://images.unsplash.com/photo-1633332755192-727a05c4013d?auto=format&fit=crop&w=500&q=80",
    name: "David Escarcega",
    email: "david@gmail.com",
    description: "Ingeniero en Software y experto en estar jodiendo",
  },
  {
    img: "https://images.unsplash.com/photo-1633332755192-727a05c4013d?auto=format&fit=crop&w=500&q=80",
    name: "Cristian Dévora",
    email: "crix@gmail.com",
    description: "Ingeniero en Software y experto en estar jodiendo",
  },
  {
    img: "https://images.unsplash.com/photo-1633332755192-727a05c4013d?auto=format&fit=crop&w=500&q=80",
    name: "Joel Cárdenas",
    email: "joel@gmail.com",
    description: "Ingeniero en Software y experto en estar jodiendo",
  },
];

const TABLE_HEAD = [
  {
    head: "Nombre",
    customeStyle: "!text-left",
  },
  {
    head: "Descripción",
    customeStyle: "text-center",
  },
  {
    head: "Likes",
    customeStyle: "text-right",
  },
];

export default function Explore() {
  return (
    <section className="w-[95%] lg:w-[50%] mx-auto">
      <Card placeholder>
        <CardHeader
          placeholder
          floated={false}
          shadow={false}
          className="mb-4 flex flex-wrap justify-between gap-4 rounded-none"
        >
          <div>
            <Typography placeholder variant="h4" color="blue-gray">
              Explora y Haz Nuevos Amigos
            </Typography>
            <Typography
              placeholder
              variant="small"
              className="mt-1 font-normal text-gray-600"
            >
              Dale like al perfil de un usuario y espera su respuesta.
            </Typography>
          </div>
          <div className="flex w-full shrink-0 items-center gap-4 md:w-max">
            <div className="w-full md:w-72">
              <Input
                crossOrigin
                size="lg"
                label="Buscar"
                icon={<MagnifyingGlassIcon className="h-5 w-5" />}
              />
            </div>
            <Menu>
              <MenuHandler>
                <Button
                  placeholder
                  variant="outlined"
                  className="flex items-center gap-2 border-gray-300"
                >
                  Filtrar
                  <ChevronDownIcon strokeWidth={3} className="w-3 h-3" />
                </Button>
              </MenuHandler>
              <MenuList placeholder>
                <MenuItem placeholder>Hobbies</MenuItem>
                <MenuItem placeholder>Intereses</MenuItem>
                <MenuItem placeholder>Carrera</MenuItem>
              </MenuList>
            </Menu>
          </div>
        </CardHeader>
        <CardBody placeholder className="overflow-x-auto !px-0 py-2">
          <table className="w-full min-w-full ">
            <thead className="hidden lg:table-header-group">
              <tr>
                {TABLE_HEAD.map(({ head, customeStyle }) => (
                  <th
                    key={head}
                    className={`border-b border-gray-300 !p-4 pb-2 ${customeStyle}`}
                  >
                    <Typography
                      placeholder
                      color="blue-gray"
                      variant="small"
                      className="!font-bold"
                    >
                      {head}
                    </Typography>
                  </th>
                ))}
              </tr>
            </thead>

            <tbody className="flex flex-col gap-4 lg:table-row-group">
              {TABLE_ROW.map(({ img, name, email, description }, index) => {
                const isLast = index === TABLE_ROW.length - 1;
                const classes = isLast
                  ? "!p-2"
                  : "!p-2 border-b border-gray-300";

                return (
                  <tr
                    key={name}
                    className="flex flex-col border lg:border-none rounded-xl lg:rounded-none shadow-sm lg:shadow-none lg:table-row"
                  >
                    <td
                      className={`${classes} flex flex-col lg:flex-row items-start lg:items-center w-full lg:w-[40%] gap-3`}
                    >
                      <img
                        src={img}
                        alt={name}
                        className="mt-3 ml-3 h-10 w-10 rounded-full flex-shrink-0"
                      />
                      <div className="p-3 flex flex-col flex-1 min-w-[200px] break-words">
                        <Typography
                          placeholder
                          variant="small"
                          color="blue-gray"
                          className="!font-semibold truncate lg:truncate-none"
                        >
                          {name}
                        </Typography>
                        <Typography
                          placeholder
                          variant="small"
                          className="!font-normal text-gray-600 truncate lg:truncate-none"
                        >
                          {email}
                        </Typography>
                        <Typography
                          placeholder
                          variant="small"
                          className="!font-normal text-gray-600 lg:hidden mt-1 break-words"
                        >
                          {description}
                        </Typography>
                      </div>
                    </td>

                    <td
                      className={`${classes} hidden lg:table-cell max-w-[300px] lg:w-[60%] break-words`}
                    >
                      <Typography
                        placeholder
                        variant="small"
                        className="text-center !font-normal text-gray-600"
                      >
                        {description}
                      </Typography>
                    </td>

                    <td
                      className={`${classes} w-full lg:w-[10%] lg:table-cell flex justify-end lg:justify-center`}
                    >
                      <Checkbox
                        crossOrigin
                        icon={
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            className="h-3 w-3"
                            viewBox="0 0 20 20"
                            fill="currentColor"
                          >
                            <path
                              fillRule="evenodd"
                              d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z"
                              clipRule="evenodd"
                            />
                          </svg>
                        }
                        defaultChecked={false}
                      />
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </CardBody>
      </Card>
      <Pagination></Pagination>
    </section>
  );
}
