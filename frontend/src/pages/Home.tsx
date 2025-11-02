import React from "react";
import {
  Navbar,
  MobileNav,
  Typography,
  Button,
  Menu,
  MenuHandler,
  MenuList,
  MenuItem,
  Avatar,
  IconButton,
} from "@material-tailwind/react";
import {
  UserCircleIcon,
  Cog6ToothIcon,
  InboxArrowDownIcon,
  LifebuoyIcon,
  PowerIcon,
  ChevronDownIcon,
} from "@heroicons/react/24/solid";
import SettingsTabs from "../components/SettingsTabs";
import { Link } from "react-router-dom";

const profileMenuItems = [
  { label: "Mi Perfil", icon: UserCircleIcon, link: "/profile" },
  { label: "Editar Perfil", icon: Cog6ToothIcon, link: "/profile" },
  //{ label: "Bandeja de entrada", icon: InboxArrowDownIcon, link: "/inbox" },
  { label: "Cerrar Sesión", icon: PowerIcon, link: "/login" },
];

export default function Home() {
  const [isMenuOpen, setIsMenuOpen] = React.useState(false);
  const [openNav, setOpenNav] = React.useState(false);

  React.useEffect(() => {
    const resizeHandler = () => window.innerWidth >= 960 && setOpenNav(false);
    window.addEventListener("resize", resizeHandler);
    return () => window.removeEventListener("resize", resizeHandler);
  }, []);

  const navList = (
    <ul className="mt-2 mb-4 flex flex-col gap-2 lg:mb-0 lg:mt-0 lg:flex-row lg:items-center lg:gap-6">
      {["Inicio", "Matches", "Chats"].map((item) => (
        <Typography
          placeholder
          key={item}
          as="li"
          variant="small"
          color="blue-gray"
          className="p-1 font-normal"
        >
          <a
            href="#"
            className="flex items-center hover:text-blue-600 transition-colors"
          >
            {item}
          </a>
        </Typography>
      ))}
    </ul>
  );

  return (
    <div className="w-full">
      {/* NAVBAR */}
      <Navbar
        placeholder
        className="sticky top-0 z-10 h-max max-w-full rounded-none px-4 py-2 lg:px-8 lg:py-4 shadow-md"
      >
        <div className="mx-auto max-w-[95%] lg:max-w-[80%] flex items-center justify-between text-blue-gray-900">
          {/* Logo */}
          <img src="/ITSON_positivo.png" alt="Itson Logo" className="h-10" />

          {/* Navegación */}
          <div className="flex items-center gap-4">
            <div className="mr-4 hidden lg:block">{navList}</div>

            {/* Menú de perfil */}
            <Menu
              open={isMenuOpen}
              handler={setIsMenuOpen}
              placement="bottom-end"
            >
              <MenuHandler>
                <Button
                  placeholder
                  variant="text"
                  color="blue-gray"
                  className="flex items-center gap-1 rounded-full py-0.5 pr-2 pl-0.5"
                >
                  <Avatar
                    placeholder
                    variant="circular"
                    size="sm"
                    alt="User Avatar"
                    className="border border-gray-900 p-0.5"
                    src="https://images.unsplash.com/photo-1633332755192-727a05c4013d?auto=format&fit=crop&w=500&q=80"
                  />
                  <ChevronDownIcon
                    strokeWidth={2.5}
                    className={`h-3 w-3 transition-transform ${
                      isMenuOpen ? "rotate-180" : ""
                    }`}
                  />
                </Button>
              </MenuHandler>

              <MenuList placeholder className="p-1">
                {profileMenuItems.map(({ label, icon, link }, key) => {
                  const isLastItem = key === profileMenuItems.length - 1;
                  return (
                    <Link to={link} key={label}>
                      <MenuItem
                        color="transparent"
                        placeholder
                        onClick={() => setIsMenuOpen(false)}
                        className={`flex items-center gap-2 rounded ${
                          isLastItem
                            ? "hover:bg-red-500/10 focus:bg-red-500/10 active:bg-red-500/10"
                            : ""
                        }`}
                      >
                        {React.createElement(icon, {
                          className: `h-4 w-4 ${
                            isLastItem ? "text-red-500" : "text-gray-700"
                          }`,
                          strokeWidth: 2,
                        })}
                        <Typography
                          placeholder
                          as="span"
                          variant="small"
                          className="font-normal"
                          color={isLastItem ? "red" : "gray"}
                        >
                          {label}
                        </Typography>
                      </MenuItem>
                    </Link>
                  );
                })}
              </MenuList>
            </Menu>

            {/* Botón hamburguesa en mobile */}
            <IconButton
              placeholder
              variant="text"
              className="ml-auto h-6 w-6 text-gray hover:bg-transparent focus:bg-transparent active:bg-transparent lg:hidden"
              ripple={false}
              onClick={() => setOpenNav(!openNav)}
            >
              {openNav ? (
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  className="h-6 w-6"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  strokeWidth={2}
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M6 18L18 6M6 6l12 12"
                  />
                </svg>
              ) : (
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  className="h-6 w-6"
                  fill="none"
                  stroke="currentColor"
                  strokeWidth={2}
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M4 6h16M4 12h16M4 18h16"
                  />
                </svg>
              )}
            </IconButton>
          </div>
        </div>

        {/* Menú colapsable (Mobile) */}
        <MobileNav open={openNav}>{navList}</MobileNav>
      </Navbar>

      {/* Contenido principal */}
      <div className="mx-auto max-w-screen-md py-12">
        <SettingsTabs />
      </div>
    </div>
  );
}
