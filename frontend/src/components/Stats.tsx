import React from "react";

import {
  Button,
  Typography,
  Menu,
  MenuHandler,
  Card,
  CardBody,
} from "@material-tailwind/react";

import { ChevronDownIcon} from "@heroicons/react/24/outline";

interface KpiCardPropsType {
  title: string;
  count: number;
  icon: React.ReactNode;
}

export function KpiCard({ title, count, icon }: KpiCardPropsType) {
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
            <Typography placeholder={""} className="font-medium !text-small">
              {count / 100} %
            </Typography>
          </div>
        </div>
        <Typography
          placeholder={""}
          color="blue-gray"
          className="mt-1 font-bold text-2xl"
        >
          {count}
        </Typography>
      </CardBody>
    </Card>
  );
}

interface StatsProps {
  data: KpiCardPropsType[];
}

export default function Stats({ data }: StatsProps) {
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
                Total
                <ChevronDownIcon
                  strokeWidth={4}
                  className="w-3 h-3 text-gray-900"
                />
              </Button>
            </MenuHandler>
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
