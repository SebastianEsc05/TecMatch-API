interface H2Props {
  children: React.ReactNode;
}

const H2 = ({ children }: H2Props) => {
  return (
    <h2 className="font-app-font text-5xl text-white mb-8 [text-shadow:-6px_6px_2px_black]">
      {children}
    </h2>
  );
};

export default H2;
