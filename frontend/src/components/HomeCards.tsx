import { Typography, Card, CardBody } from "@material-tailwind/react";

interface ContentCardPropsType {
  img: string;
  title: string;
  desc: string;
}

function ContentCard({ img, title, desc }: ContentCardPropsType) {
  return (
    <Card
      placeholder
      className="relative grid min-h-[30rem] items-end overflow-hidden rounded-xl"
      color="transparent"
    >
      <img
        src={img}
        alt="Potronet Card"
        className="absolute inset-0 h-full w-full object-cover object-center"
      />
      <div className="absolute inset-0 bg-black/70" />
      <CardBody placeholder className="relative flex flex-col justify-end">
        <Typography placeholder variant="h4" color="white">
          {title}
        </Typography>
        <Typography
          placeholder
          variant="paragraph"
          color="white"
          className="my-2 font-normal"
        >
          {desc}
        </Typography>
      </CardBody>
    </Card>
  );
}

const contents = [
  {
    img: "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?ixlib=rb‑4.0&auto=format&fit=crop&w=800&q=80",
    title: "Explora y Comparte",
    desc: "Encuentra amigos, eventos y actividades que te motiven. Participar en tu comunidad nunca había sido tan fácil.",
  },
  {
    img: "https://images.pexels.com/photos/3182765/pexels-photo-3182765.jpeg?auto=compress&cs=tinysrgb&w=800",
    title: "Crea tu Espacio",
    desc: "Comparte tus ideas, proyectos y pasiones. Una red para estudiantes te ayuda a expresarte y aprender de otros.",
  },
  {
    img: "https://images.pexels.com/photos/3184436/pexels-photo-3184436.jpeg?auto=compress&cs=tinysrgb&w=800",
    title: "Crecemos Juntos",
    desc: "Cada interacción, cada descubrimiento, te acerca a nuevas oportunidades y experiencias.",
  },
];


export default function HomeCards() {
  return (
    <section className="container mx-auto px-8 py-10 lg:py-28">
      <Typography
        placeholder
        variant="h2"
        color="blue-gray"
        className="!text-2xl !leading-snug lg:!text-3xl"
      >
        Conecta y Aprende
      </Typography>
      <Typography
        placeholder
        variant="lead"
        className="mt-2 max-w-xl text-md lg:text-xl !font-normal !text-gray-500"
      >
        Comparte tus intereses, descubre nuevos hobbies y conoce a otros
        estudiantes que piensan como tú.
      </Typography>

      <div className="mt-10 grid grid-cols-1 gap-10 lg:grid-cols-3">
        {contents.map(({ img, title, desc }) => (
          <ContentCard key={title} img={img} title={title} desc={desc} />
        ))}
      </div>
    </section>
  );
}
