import React from "react";

import {
  Button,
  Typography,
  Menu,
  MenuHandler,
  MenuList,
  MenuItem,
  Card,
  CardBody,
} from "@material-tailwind/react";

import { ChevronDownIcon, ChevronUpIcon } from "@heroicons/react/24/outline";

interface KpiCardPropsType {
  title: string;
  percentage: string;
  price: string;
  color: string;
  icon: React.ReactNode;
}

export function KpiCard({
  title,
  percentage,
  price,
  color,
  icon,
}: KpiCardPropsType) {
  return (
    <Card
      placeholder={""}
      className="shadow-sm border border-gray-200 !rounded-lg"
    >
      <CardBody placeholder={""} className="p-4">
        <div className="flex justify-between items-center">
          <Typography
            placeholder={""}
            className="!font-medium !text-small text-gray-600"
          >
            {title}
          </Typography>
          <div className="flex items-center gap-1">
            {icon}
            <Typography
              placeholder={""}
              color={color as any}
              className="font-medium !text-small"
            >
              {percentage}
            </Typography>
          </div>
        </div>
        <Typography
          placeholder={""}
          color="blue-gray"
          className="mt-1 font-bold text-2xl"
        >
          {price}
        </Typography>
      </CardBody>
    </Card>
  );
}

const data = [
  {
    title: "Estudiantes Registrados",
    percentage: "0%",
    price: "0",
    color: "red",
    icon: <ChevronDownIcon strokeWidth={4} className="w-3 h-3 text-red-500" />,
  },
  {
    title: "Matches Registrados",
    percentage: "0%",
    price: "0",
    color: "green",
    icon: <ChevronUpIcon strokeWidth={4} className="w-3 h-3 text-green-500" />,
  },
  {
    title: "Estudiantes Activos",
    percentage: "0%",
    price: "0",
    color: "green",
    icon: <ChevronUpIcon strokeWidth={4} className="w-3 h-3 text-green-500" />,
  },
  {
    title: "Contador Visitas",
    percentage: "0%",
    price: "0",
    color: "red",
    icon: <ChevronDownIcon strokeWidth={4} className="w-3 h-3 text-red-500" />,
  },
];

export default function Stats() {
  return (
    <section className="container mt-10 lg:mt-28 mb-5 px-8">
      <div className="flex justify-between md:items-center">
        <div>
          <Typography
            variant="h3"
            placeholder={""}
            className="font-bold text-2xl lg:text-4xl mb-2"
          >
            Estadísticas
          </Typography>
          <Typography
            placeholder={""}
            variant="h6"
            className="text-sm lg:text-xl font-normal text-gray-600 md:w-full w-4/5"
          >
            Muestra todos los estudiantes que forman parte de nuestra comunidad.
          </Typography>
        </div>
        <div className="shrink-0">
          <Menu>
            <MenuHandler>
              <Button
                placeholder={""}
                color="gray"
                variant="outlined"
                className="flex items-center gap-1 !border-gray-300"
              >
                Last 24h
                <ChevronDownIcon
                  strokeWidth={4}
                  className="w-3 h-3 text-gray-900"
                />
              </Button>
            </MenuHandler>
            <MenuList placeholder={""}>
              <MenuItem placeholder={""}>Last 6h</MenuItem>
              <MenuItem placeholder={""}>Last 12h</MenuItem>
              <MenuItem placeholder={""}>Last 24h</MenuItem>
            </MenuList>
          </Menu>
        </div>
      </div>
      <div className="mt-6 grid lg:grid-cols-4 md:grid-cols-2 grid-cols-1 items-center md:gap-2.5 gap-4">
        {data.map((props, key) => (
          <KpiCard key={key} {...(props as any)} />
        ))}
      </div>
    </section>
  );
}
