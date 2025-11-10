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
import Phone from "./pages/Phone";
import EditProfile from "./pages/EditProfile";
import { useEffect } from "react";

export default function App() {
  const baseURL = import.meta.env.VITE_API_URL;

  useEffect(() => {
    const pingInterval = setInterval(() => {
      fetch(`${baseURL}/api/app/health`).catch(() => {});
    }, 30 * 1000);

    return () => clearInterval(pingInterval);
  }, []);

  return (
    <>
      <img
        src="/POTROSimago_positivo.png"
        alt="Potro-NET"
        style={{
          position: "absolute",
          left: "-9999px",
          width: "300px",
          height: "auto",
        }}
      />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Hero />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/phone" element={<Phone />} />
          <Route path="/userInformation" element={<UserInfromation />} />
          <Route path="/aboutYou" element={<AboutYou />} />

          <Route element={<MainLayout />}>
            <Route path="/home" element={<Home />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/editProfile" element={<EditProfile />} />
            <Route path="/explore" element={<Explore />} />
            <Route path="/matches" element={<Matches />} />
          </Route>

          <Route path="/chat" element={<Chat />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
