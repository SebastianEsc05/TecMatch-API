import React from "react";
import { Outlet } from "react-router-dom";
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
  PowerIcon,
  ChevronDownIcon,
} from "@heroicons/react/24/solid";
import { Link } from "react-router-dom";
import {
  HomeIcon,
  MagnifyingGlassIcon,
  ChatBubbleLeftRightIcon,
} from "@heroicons/react/24/outline";

const profileMenuItems = [
  { label: "Mi Perfil", icon: UserCircleIcon, link: "/profile" },
  { label: "Editar Perfil", icon: Cog6ToothIcon, link: "/profile" },
  { label: "Cerrar Sesión", icon: PowerIcon, link: "/login" },
];

export default function MainLayout() {
  const [isMenuOpen, setIsMenuOpen] = React.useState(false);
  const [openNav, setOpenNav] = React.useState(false);

  React.useEffect(() => {
    const resizeHandler = () => window.innerWidth >= 960 && setOpenNav(false);
    window.addEventListener("resize", resizeHandler);
    return () => window.removeEventListener("resize", resizeHandler);
  }, []);

  const navList = (
    <ul className="mt-2 mb-4 flex flex-col gap-2 lg:mb-0 lg:mt-0 lg:flex-row lg:items-center lg:gap-6">
      {[
        {
          name: "Inicio",
          path: "/home",
          icon: <HomeIcon className="h-4 w-4 mr-2" />,
        },
        {
          name: "Explorar",
          path: "/explore",
          icon: <MagnifyingGlassIcon className="h-4 w-4 mr-2" />,
        },
        {
          name: "Amigos",
          path: "/matches",
          icon: <ChatBubbleLeftRightIcon className="h-4 w-4 mr-2" />,
        },
      ].map((item) => (
        <Link key={item.name} to={item.path}>
          <Typography
            placehorder={""}
            as="li"
            variant="small"
            color="blue-gray"
            className="p-1 font-normal flex items-center"
          >
            {item.icon}
            {item.name}
          </Typography>
        </Link>
      ))}
    </ul>
  );

  return (
    <div className="w-full">
      <Navbar
        placehorder={""}
        className="sticky top-0 z-10 h-max max-w-full rounded-none px-4 py-2 lg:px-8 lg:py-4 shadow-md"
      >
        <div className="mx-auto max-w-[95%] lg:max-w-[80%] flex items-center justify-between text-blue-gray-900">
          <Link to={"/home"}>
            <img
              src="/ITSON_positivo.png"
              alt="Potronet Logo"
              className="h-10"
            />
          </Link>
          <div className="flex items-center gap-4">
            <div className="mr-4 hidden lg:block">{navList}</div>

            <Menu
              open={isMenuOpen}
              handler={setIsMenuOpen}
              placement="bottom-end"
            >
              <MenuHandler>
                <Button
                  placehorder={""}
                  variant="text"
                  color="blue-gray"
                  className="flex items-center gap-1 rounded-full py-0.5 pr-2 pl-0.5"
                >
                  <Avatar
                    placehorder={""}
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

              <MenuList placehorder={""} className="p-1">
                <Link to={"/profile"} key={"Mi Perfil"}>
                  <MenuItem
                    color="transparent"
                    placehorder={""}
                    onClick={() => setIsMenuOpen(false)}
                    className={`flex items-center gap-2 rounded`}
                  >
                    {React.createElement(UserCircleIcon, {
                      className: `h-4 w-4 text-gray-700`,
                      strokeWidth: 2,
                    })}
                    <Typography
                      placehorder={""}
                      as="span"
                      variant="small"
                      className="font-normal"
                      color={"gray"}
                    >
                      {"Mi Perfil"}
                    </Typography>
                  </MenuItem>
                </Link>

                <Link to={"/profile"} key={"Editar Perfil"}>
                  <MenuItem
                    color="transparent"
                    placehorder={""}
                    onClick={() => setIsMenuOpen(false)}
                    className={`flex items-center gap-2 rounded`}
                  >
                    {React.createElement(Cog6ToothIcon, {
                      className: `h-4 w-4 text-gray-700`,
                      strokeWidth: 2,
                    })}
                    <Typography
                      placehorder={""}
                      as="span"
                      variant="small"
                      className="font-normal"
                      color={"gray"}
                    >
                      {"Editar Perfil"}
                    </Typography>
                  </MenuItem>
                </Link>

                <Link to={"/login"} key={"Cerrar Sesión"}>
                  <MenuItem
                    color="transparent"
                    placehorder={""}
                    onClick={() => {
                      sessionStorage.clear();
                      setIsMenuOpen(false);
                    }}
                    className={`flex items-center gap-2 rounded hover:bg-red-500/10 focus:bg-red-500/10 active:bg-red-500/10"`}
                  >
                    {React.createElement(PowerIcon, {
                      className: `h-4 w-4 text-red-500`,
                      strokeWidth: 2,
                    })}
                    <Typography
                      placehorder={""}
                      as="span"
                      variant="small"
                      className="font-normal"
                      color={"red"}
                    >
                      {"Cerrar Sesión"}
                    </Typography>
                  </MenuItem>
                </Link>
              </MenuList>
            </Menu>
            <IconButton
              placehorder={""}
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
        <MobileNav open={openNav}>{navList}</MobileNav>
      </Navbar>

      <div className="mx-auto max-w-full py-12">
        <Outlet />
      </div>
    </div>
  );
}
