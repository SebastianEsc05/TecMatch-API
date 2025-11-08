import React from "react";
import {
  Input,
  Popover,
  PopoverHandler,
  PopoverContent,
} from "@material-tailwind/react";
import { format } from "date-fns";
import { DayPicker } from "react-day-picker";
import { ChevronRightIcon, ChevronLeftIcon } from "@heroicons/react/24/outline";

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
  return (
    <Popover placement="bottom-start">
      <PopoverHandler>
        <Input
          label={label}
          value={value ? format(value, "PPP") : ""}
          onChange={() => null}
          className="!border-t-blue-gray-200 focus:!border-t-gray-900 cursor-pointer"
          readOnly
        />
      </PopoverHandler>
      <PopoverContent className="p-2">
        <DayPicker
          mode="single"
          selected={value}
          onSelect={onChange}
          showOutsideDays
          className="border-0"
          classNames={{
            caption: "flex justify-center py-2 mb-4 relative items-center",
            caption_label: "text-sm font-medium text-gray-900",
            nav: "flex items-center",
            nav_button:
              "h-6 w-6 bg-transparent hover:bg-blue-gray-50 p-1 rounded-md transition-colors duration-300",
            nav_button_previous: "absolute left-1.5",
            nav_button_next: "absolute right-1.5",
            table: "w-full border-collapse",
            head_row: "flex font-medium text-gray-900",
            head_cell: "m-0.5 w-9 font-normal text-sm",
            row: "flex w-full mt-2",
            cell: "text-gray-600 rounded-md h-9 w-9 text-center text-sm p-0 m-0.5 relative [&:has([aria-selected])]:bg-gray-900/50 [&:has([aria-selected])]:text-white",
            day: "h-9 w-9 p-0 font-normal",
            day_selected:
              "rounded-md bg-gray-900 text-white hover:bg-gray-900 focus:bg-gray-900",
            day_today: "rounded-md bg-gray-200 text-gray-900",
            day_outside: "text-gray-500 opacity-50",
            day_disabled: "text-gray-400 opacity-50",
          }}
          components={{
            IconLeft: (props) => (
              <ChevronLeftIcon {...props} className="h-4 w-4 stroke-2" />
            ),
            IconRight: (props) => (
              <ChevronRightIcon {...props} className="h-4 w-4 stroke-2" />
            ),
          }}
        />
      </PopoverContent>
    </Popover>
  );
}
