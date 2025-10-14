import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

// 1. Frameworks
import "bootstrap/dist/css/bootstrap.min.css";
// 2. estilos globales
import "./styles/global.css";
import App from "./App.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <App />
  </StrictMode>
);
