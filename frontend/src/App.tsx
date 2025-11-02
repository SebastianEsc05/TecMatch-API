import { Typography, Card } from "@material-tailwind/react";
import Hero from "./pages/Hero";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import HeroSection16 from "./pages/HeroSEction16";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Hero />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
}
