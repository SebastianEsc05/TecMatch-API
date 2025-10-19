interface InputProps {
  id: string;
  type: string;
  placeholder: string;
}


const Input = ({id, type, placeholder}: InputProps) => {

    return (
      <input
        className="bg-black border-4 border-transparent rounded-[22px] text-white text-left text-2xl 
        p-2 px-4 w-full max-w-md transition-all focus:scale-105 focus:border-white focus:outline-none"
        id={id}
        type={type}
        placeholder={placeholder}
      />
    );
}

export default Input;