import React from "react";
import {
  Button,
  Dialog,
  DialogHeader,
  DialogBody,
  DialogFooter,
  IconButton,
  Typography,
  MenuItem
} from "@material-tailwind/react";

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

      <Dialog placeholder size="xs" open={open} handler={handleOpen}>
        <DialogHeader placeholder className="justify-between">
          <div>
            <Typography placeholder variant="h3" color="blue-gray">
              Hobbies Disponibles
            </Typography>
            <Typography placeholder color="gray" variant="paragraph">
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
        <DialogBody placeholder className="overflow-y-scroll !px-5">
          <div className="mb-6">
            <Typography
              placeholder
              variant="paragraph"
              color="blue-gray"
              className="py-3 font-semibold uppercase opacity-70"
            >
              Popular
            </Typography>
            <ul className="-ml-2 mt-3 flex flex-col gap-1">
              <MenuItem
                placeholder
                className="mb-4 flex items-center justify-center gap-3 !py-4 shadow-md"
              >
                <img
                  src="https://docs.material-tailwind.com/icons/metamask.svg"
                  alt="metamast"
                  className="h-6 w-6"
                />
                <Typography
                  placeholder
                  className="uppercase"
                  color="blue-gray"
                  variant="h6"
                >
                  Connect with MetaMask
                </Typography>
              </MenuItem>
              <MenuItem
                placeholder
                className="mb-1 flex items-center justify-center gap-3 !py-4 shadow-md"
              >
                <img
                  src="https://docs.material-tailwind.com/icons/coinbase.svg"
                  alt="metamast"
                  className="h-6 w-6 rounded-md"
                />
                <Typography
                  placeholder
                  className="uppercase"
                  color="blue-gray"
                  variant="h6"
                >
                  Connect with Coinbase
                </Typography>
              </MenuItem>
            </ul>
          </div>
          <div>
            <Typography
              placeholder
              variant="paragraph"
              color="blue-gray"
              className="py-4 font-semibold uppercase opacity-70"
            >
              Other
            </Typography>
            <ul className="-ml-2.5 mt-4 flex flex-col gap-1">
              <MenuItem
                placeholder
                className="mb-4 flex items-center justify-center gap-3 !py-4 shadow-md"
              >
                <img
                  src="https://docs.material-tailwind.com/icons/trust-wallet.svg"
                  alt="metamast"
                  className="h-7 w-7 rounded-md border border-blue-gray-50"
                />
                <Typography
                  placeholder
                  className="uppsecase"
                  color="blue-gray"
                  variant="h6"
                >
                  Connect with Trust Wallet
                </Typography>
              </MenuItem>
            </ul>
          </div>
        </DialogBody>
        <DialogFooter placeholder className="justify-between gap-2">
          <Typography
            placeholder
            variant="small"
            color="gray"
            className="font-normal"
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
