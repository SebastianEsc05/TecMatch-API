import { Typography } from "@material-tailwind/react";


export default function Footer() {
  return (
    <footer
      className="mt-20 lg:mt-0 flex max-w-[70%] mx-auto flex-row flex-wrap items-center justify-center gap-y-6 gap-x-12 border-t 
    border-blue-gray-50 py-6 text-center md:justify-between
    
    "
    >
      <Typography placeholder color="blue-gray" className="font-normal">
        &copy; 2025 Potro-Net
      </Typography>
      <ul className="grid grid-cols-2 gap-4 sm:grid-cols-2 md:flex md:flex-row md:flex-wrap md:items-center md:justify-between md:gap-x-12">
        <li>
          <Typography
            placeholder
            as="a"
            href="#"
            color="blue-gray"
            className="font-normal transition-colors hover:text-blue-500 focus:text-blue-500"
          >
            Sobre Nosotros
          </Typography>
        </li>
        <li>
          <Typography
            placeholder
            as="a"
            href="#"
            color="blue-gray"
            className="font-normal transition-colors hover:text-blue-500 focus:text-blue-500"
          >
            Licencia
          </Typography>
        </li>
        <li>
          <Typography
            placeholder
            as="a"
            href="#"
            color="blue-gray"
            className="font-normal transition-colors hover:text-blue-500 focus:text-blue-500"
          >
            Contribuir
          </Typography>
        </li>
        <li>
          <Typography
            placeholder
            as="a"
            href="#"
            color="blue-gray"
            className="font-normal transition-colors hover:text-blue-500 focus:text-blue-500"
          >
            Contactanos
          </Typography>
        </li>
      </ul>
    </footer>
  );
}
