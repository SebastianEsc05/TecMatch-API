interface H3Props {
  children: React.ReactNode;
}

const H3 = ({ children }: H3Props) => {
  return (
    <h3 className="font-app-font text-2xl text-white mt-10 mb-8 [text-shadow:-3px_3px_1px_black]">
      {children}
    </h3>
  );
};

export default H3;
