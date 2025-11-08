import fs from "fs";
import path from "path";

const appFilePath = path.resolve("src/App.tsx");
const content = fs.readFileSync(appFilePath, "utf8");

const regex = /path="([^"]+)"/g;

let match;
const rutas: string[] = [];

while ((match = regex.exec(content)) !== null) {
  rutas.push(match[1]);
}

const rutasUnicas = [...new Set(rutas)];

console.log("\nRutas encontradas:");
console.table(rutasUnicas);

fs.writeFileSync("routes.json", JSON.stringify(rutasUnicas, null, 2));

console.log("Archivo generado: routes.json\n");
