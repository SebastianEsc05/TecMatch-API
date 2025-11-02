import {
  Card,
  CardBody,
  Avatar,
  IconButton,
  Typography,
} from "@material-tailwind/react";

interface TeamCardPropsType {
  img: string;
  name: string;
  title: string;
}

function TeamCard({ img, name, title }: TeamCardPropsType) {
  return (
    <Card placeholder className="rounded-lg bg-[#FAFAFA]" shadow={false}>
      <CardBody placeholder className="text-center">
        <Avatar
          placeholder
          src={img}
          alt={name}
          variant="circular"
          size="xxl"
          className="mx-auto mb-6 object-top"
        />
        <Typography
          placeholder
          variant="h5"
          color="blue-gray"
          className="!font-medium text-lg"
        >
          {name}
        </Typography>
        <Typography
          placeholder
          color="blue-gray"
          className="mb-2 !text-base !font-semibold text-gray-600"
        >
          {title}
        </Typography>
        <div className="flex items-center justify-center gap-1.5">
          <IconButton placeholder variant="text" color="gray">
            <i className="fa-brands fa-twitter text-lg" />
          </IconButton>
          <IconButton placeholder variant="text" color="gray">
            <i className="fa-brands fa-linkedin text-lg" />
          </IconButton>
          <IconButton placeholder variant="text" color="gray">
            <i className="fa-brands fa-dribbble text-lg" />
          </IconButton>
        </div>
      </CardBody>
    </Card>
  );
}

const members = [
  {
    img: `https://avatars.githubusercontent.com/u/191727193?v=4"`,
    name: "Sebastián Escalante",
    title: "Software Dev",
  },
  {
    img: `https://avatars.githubusercontent.com/u/237477628?v=4`,
    name: "David Escarcega",
    title: "Software Dev",
  },
  {
    img: `https://avatars.githubusercontent.com/u/124020433?v=4`,
    name: "Joel Cardenas",
    title: "Software Dev",
  },
  {
    img: `https://avatars.githubusercontent.com/u/192383640?v=4`,
    name: "Manuel Cortez",
    title: "Software Dev",
  },
];

export default function Team() {
  return (
    <section className="px-8 lg:px-20 mb-20 mt-10">
      <div className="container mx-auto">
        <div className="mb-12 text-center">
          <Typography
            placeholder
            variant="h5"
            color="blue-gray"
          >
            Conoce al Equipo de Trabajo
          </Typography>
          <Typography
            placeholder
            variant="h1"
            color="blue-gray"
            className="my-4 !text-2xl lg:!text-4xl"
          >
            Juntos Hacemos que las Cosas Sucedan
          </Typography>
          <Typography
            placeholder
            variant="lead"
            className="mx-auto w-full !text-gray-500 max-w-4xl"
          >
            Cada uno de nosotros aporta su experiencia y creatividad para que
            todo funcione mejor. Desde la planificación hasta la ejecución,
            trabajamos juntos para ofrecer ideas útiles y soluciones prácticas.
          </Typography>
        </div>
        <div className="grid grid-cols-1 gap-6 md:grid-cols-2 lg:grid-cols-4">
          {members.map((props, key) => (
            <TeamCard key={key} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
