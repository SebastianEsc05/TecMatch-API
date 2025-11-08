import React, { useState } from "react";
import {
  Input,
  Popover,
  PopoverHandler,
  PopoverContent,
} from "@material-tailwind/react";
import { format } from "date-fns";
import { DayPicker } from "react-day-picker";

interface DatePickerProps {
  label: string;
  value: Date | undefined;
  onChange: (date: Date | undefined) => void;
}

export default function DatePicker({
  label,
  value,
  onChange,
}: DatePickerProps) {
  const [open, setOpen] = useState(false);

  const handleSelect = (date: Date | undefined) => {
    onChange(date);
    setOpen(false);
  };

  return (
    <Popover placement="bottom-start" open={open} handler={setOpen}>
      <PopoverHandler>
        <Input
          crossOrigin={""}
          label={label}
          value={value ? format(value, "PPP") : ""}
          readOnly
          className="!border-t-blue-gray-200 focus:!border-t-gray-900 cursor-pointer py-5"
        />
      </PopoverHandler>
      <PopoverContent
        placeholder={""}
        className="p-4 rounded-lg shadow-lg border border-gray-200"
      >
        <DayPicker
          captionLayout="dropdown"
          mode="single"
          selected={value}
          onSelect={handleSelect}
          showOutsideDays
          className="border-0"
          classNames={{
            caption: "flex justify-center items-center gap-2 mb-3 py-1",
            caption_label: "hidden",
            nav: "hidden",
            table: "w-full border-collapse",
            head_row: "flex font-medium text-gray-900",
            head_cell: "m-0.5 w-9 font-normal text-sm",
            row: "flex w-full mt-1",
            cell: "text-gray-600 rounded-md h-9 w-9 text-center text-sm p-0 m-0.5 relative [&:has([aria-selected])]:bg-gray-900/50 [&:has([aria-selected])]:text-white",
            day: "h-9 w-9 p-0 font-normal",
            day_selected:
              "rounded-md bg-gray-900 text-white hover:bg-gray-900 focus:bg-gray-900",
            day_today: "rounded-md bg-gray-200 text-gray-900",
            day_outside: "text-gray-400 opacity-50",
            day_disabled: "text-gray-400 opacity-50",
          }}
        />
      </PopoverContent>
    </Popover>
  );
}
