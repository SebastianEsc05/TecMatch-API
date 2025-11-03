import React from "react";
import {
  Button,
  Dialog,
  DialogHeader,
  DialogBody,
  DialogFooter,
  IconButton,
  Typography,
  MenuItem,
} from "@material-tailwind/react";
import { Checkbox } from "@material-tailwind/react";

export default function Hobbies() {
  const [open, setOpen] = React.useState(false);

  const handleOpen = () => setOpen((cur) => !cur);

  return (
    <>
      <Button
        placeholder
        className="w-full bg-white text-black border border-gray-400 shadow-md hover:bg-gray-100 font-sans rounded-md py-3 px-4 transition-colors"
        onClick={handleOpen}
      >
        Seleccionar Hobbies
      </Button>

      <Dialog
        placeholder
        open={open}
        size="xs"
        handler={handleOpen}
        className="max-h-[60vh] overflow-hidden mx-auto rounded-2xl"
      >
        <DialogHeader
          placeholder
          className="justify-between sticky top-0 bg-white z-10"
        >
          <div>
            <Typography
              placeholder
              variant="h5"
              className=" lg:text-3xl mb-1"
              color="blue-gray"
            >
              Hobbies Disponibles
            </Typography>
            <Typography
              placeholder
              color="gray"
              variant="paragraph"
              className="text-xs lg:text-base"
            >
              Escoge los que te identifiquen mejor.
            </Typography>
          </div>
          <IconButton
            placeholder
            color="blue-gray"
            size="sm"
            variant="text"
            onClick={handleOpen}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              strokeWidth={2}
              className="h-5 w-5"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M6 18L18 6M6 6l12 12"
              />
            </svg>
          </IconButton>
        </DialogHeader>
        <DialogBody
          placeholder
          className="overflow-y-auto max-h-[40vh] !px-5 scrollbar-thin scrollbar-thumb-gray-300 scrollbar-track-gray-100"
        >
          <div className="mb-6">
            <ul className="-ml-2 mt-3 flex flex-col gap-1">
              {[
                {
                  name: "Musica",
                  id: "music",
                  img: "https://cdn.jsdelivr.net/gh/microsoft/fluentui-emoji/assets/Musical%20notes/3D/musical_notes_3d.png",
                },
                {
                  name: "Deportes",
                  id: "sport",
                  img: "https://cdn.jsdelivr.net/gh/microsoft/fluentui-emoji/assets/Soccer%20ball/3D/soccer_ball_3d.png",
                },
                {
                  name: "Cocina",
                  id: "cook",
                  img: "https://cdn.jsdelivr.net/gh/microsoft/fluentui-emoji/assets/Cooking/3D/cooking_3d.png",
                },
                {
                  name: "Videojuegos",
                  id: "games",
                  img: "https://cdn.jsdelivr.net/gh/microsoft/fluentui-emoji/assets/Joystick/3D/joystick_3d.png",
                },
                {
                  name: "Películas",
                  id: "movies",
                  img: "https://cdn.jsdelivr.net/gh/microsoft/fluentui-emoji/assets/Movie%20camera/3D/movie_camera_3d.png",
                },
                {
                  name: "Programación",
                  id: "code",
                  img: "https://cdn.jsdelivr.net/gh/microsoft/fluentui-emoji/assets/Laptop/3D/laptop_3d.png",
                },
              ].map((item) => (
                <MenuItem
                  key={item.id}
                  placeholder
                  className="mb-2 flex items-center justify-between gap-3  lg:!py-2 shadow-md rounded-xl hover:bg-blue-gray-50 transition"
                >
                  <div className="flex items-center gap-3">
                    <img
                      src={item.img}
                      alt={item.name}
                      className="h-5 w-5 lg:h-7 lg:w-7"
                    />
                    <Typography
                      placeholder
                      className="uppercase text-sm lg:text-base"
                      color="blue-gray"
                      variant="h6"
                    >
                      {item.name}
                    </Typography>
                  </div>
                  <Checkbox
                    crossOrigin
                    id={item.id}
                    name={item.id}
                    color="gray"
                    defaultChecked={false}
                    ripple={true}
                  />
                </MenuItem>
              ))}
            </ul>
          </div>
        </DialogBody>

        <DialogFooter
          placeholder
          className="justify-between gap-2 sticky bottom-0 bg-white z-10 border-t border-gray-100"
        >
          <Typography
            placeholder
            variant="h6"
            color="gray"
            className="font-normal text-xs lg:text-sm"
          >
            Representan tu personalidad?
          </Typography>
          <Button placeholder variant="outlined" size="sm">
            Aceptar
          </Button>
        </DialogFooter>
      </Dialog>
    </>
  );
}
