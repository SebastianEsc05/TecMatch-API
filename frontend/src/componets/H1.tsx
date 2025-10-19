interface H1Props {
  children: React.ReactNode;
}

const H1 = ({ children }: H1Props) => {

  return (
    
    <h1 className="text-7xl mt-14 text-app-blue mb-4 lg:text-9xl [text-shadow:-6px_6px_2px_black]">
      {children}
    </h1>
  );
};

export default H1;
