//import { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import WelcomePage from "./pages/WelcomePage";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import NextRegisterPage from "./pages/NextRegisterPage";
import ExplorePage from "./pages/ExplorePage";
import Profile from "./pages/profile";
import Hobbies from "./pages/Hobbies";
import Interests from "./pages/Interests";
import MatchesPage from "./pages/MatchesPage";
import ChatPage from "./pages/ChatPage";

function App() {
  //const [count, setCount] = useState(0);

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/loginPage" element={<LoginPage />} />
          <Route path="/registerPage" element={<RegisterPage />} />
          <Route path="/nextRegisterPage" element={<NextRegisterPage />} />
          <Route path="/hobbiesPage" element={<Hobbies type="register" />} />
          <Route path="/interestsPage" element={<Interests type="register" />} />
          <Route path="/explorePage" element={<ExplorePage />} />
          <Route path="/profilePage" element={<Profile />} />
          <Route path="/updateHobbiesPage" element={<Hobbies type="update" />} />
          <Route path="/updateInterestsPage" element={<Interests type="update" />} />
          <Route path="/matchesPage" element={<MatchesPage />} />
          <Route path="/chatPage" element={<ChatPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
