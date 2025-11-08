import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import { readFileSync } from "fs";
import SitemapPlugin from "vite-plugin-sitemap";

const routes = JSON.parse(readFileSync("./routes.json", "utf-8"));

export default defineConfig({
  plugins: [
    react(),
    SitemapPlugin({
      hostname: "https://potro-net.vercel.app",
      outDir: "dist",
      dynamicRoutes: routes,
      changefreq: "weekly",
      priority: 0.8,
      lastmod: new Date(),
      robots: [
        {
          userAgent: "*",
          allow: "/",
        },
      ],
    }),
  ],
  server: {
    proxy: {
      "/api": {
        target: "https://potro-net-wlxk.onrender.com",
        changeOrigin: true,
        secure: false,
      },
    },
  },
});


