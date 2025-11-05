import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import SitemapPlugin from "vite-plugin-sitemap";

export default defineConfig({
  plugins: [
    react(),
    SitemapPlugin({
      hostname: "https://potro-net.vercel.app",
      outDir: "public",
      dynamicRoutes: ["/", "/about", "/contact"],
      robots: [
        {
          userAgent: "*",
          allow: "/",
        },
      ],
      changefreq: "weekly",
      priority: 0.8,
      lastmod: new Date(),
    }),
  ],
});
