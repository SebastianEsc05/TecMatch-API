interface SectionProps {
  children: React.ReactNode;
}


const Section = ({children}:SectionProps) => {

  return (

    <div className="flex justify-center items-start min-h-screen text-white text-center p-4">
      <div className="w-full max-w-md lg:max-w-2xl">
        {children}
      </div>
    </div>
  );
};

export default Section;