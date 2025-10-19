interface ButtonProps {
  children: React.ReactNode;
}


const Button = ({children}:ButtonProps) => {
  return (
    <button className="m-3 font-app-font bg-white text-black border-4 border-black rounded-full 
    py-2 px-8 text-2xl mt-8 mb-8 shadow-[-6px_6px_0px_black] transition-transform duration-100 
    ease-in-out hover:translate-x-0.5 hover:translate-y-0.5 hover:shadow-[-2px_2px_0px_black]">
      {children}
    </button>
  );
};

export default Button;
