interface SelectProps {
  children: React.ReactNode;
  id: string;
  name: string;
}

const Select = ({ children, id, name }: SelectProps) => {
  return (
    <select
      className="bg-black border-4 border-transparent rounded-[22px] text-[#808080] text-left text-2xl 
            p-2 px-4 w-full max-w-md transition-all focus:scale-105 focus:border-white focus:outline-none"
      id={id}
      name={name}
    >
      {children}
    </select>
  );
};

export default Select;
