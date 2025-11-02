import { BrowserRouter, Routes, Route } from "react-router-dom";
import Hero from "./pages/Hero";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import UserInfromation from "./pages/UserInformation";
import AboutYou from "./pages/AboutYou";
import MainLayout from "./components/MainLayout";
import Home from "./pages/Home";
import Profile from "./pages/Profile";
import Explore from "./pages/Explore";
import Matches from "./pages/Matches";
import Chat from "./pages/Chat";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Hero />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/userInformation" element={<UserInfromation />} />
        <Route path="/aboutYou" element={<AboutYou />} />

        <Route element={<MainLayout />}>
          <Route path="/home" element={<Home />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/explore" element={<Explore />} />
          <Route path="/matches" element={<Matches />} />
        </Route>
        
        <Route path="/chat" element={<Chat />} />
      </Routes>
    </BrowserRouter>
  );
}
