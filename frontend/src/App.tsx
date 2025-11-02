import { BrowserRouter, Routes, Route } from "react-router-dom";
import Hero from "./pages/Hero";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import UserInfromation from "./pages/UserInformation";
import Hobbies from "./pages/Hobbies";
import Interests from "./pages/Interests";
import Home from "./pages/Home";
import Profile from "./pages/Profile";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Hero />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/userInformation" element={<UserInfromation />} />
        <Route path="/hobbies" element={<Hobbies />} />
        <Route path="/interests" element={<Interests />} />
        <Route path="/home" element={<Home />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </BrowserRouter>
  );
}
