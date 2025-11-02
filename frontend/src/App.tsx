import { BrowserRouter, Routes, Route } from "react-router-dom";
import Hero from "./pages/Hero";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Hobbies from "./pages/Hobbies";
import Interests from "./pages/Interests";
import Home from "./pages/Home";
import UserInfromation from "./pages/UserInformation";


export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Hero />} />
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/userInformation" element={<UserInfromation />} />
        <Route path="/userInformation" element={<Hobbies />} />
        <Route path="/userInformation" element={<Interests />} />
      </Routes>
    </BrowserRouter>
  );
}
