interface LabelProps {
    htmlFor: string;
    children: React.ReactNode;
}

const Label = ({ htmlFor, children }: LabelProps) => {

    return (

    <label htmlFor={htmlFor} className="text-black text-3xl block mb-2">
        {children}
    </label>
    );
};

export default Label;
