import {
  UserCircleIcon,
  Cog6ToothIcon,
  PowerIcon,
} from "@heroicons/react/24/solid";

const profileMenuItems = [
  { label: "Mi Perfil", icon: UserCircleIcon, link: "/profile" },
  { label: "Editar Perfil", icon: Cog6ToothIcon, link: "/profile" },
  //{ label: "Bandeja de entrada", icon: InboxArrowDownIcon, link: "/inbox" },
  { label: "Cerrar Sesión", icon: PowerIcon, link: "/login" },
];

export default function Home() {
  return (
    <div className="w-full">
      <div className="mx-auto max-w-screen-md py-12"></div>
    </div>
  );
}
