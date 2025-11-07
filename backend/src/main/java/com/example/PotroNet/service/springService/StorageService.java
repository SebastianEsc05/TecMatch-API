package com.example.PotroNet.service.springService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class StorageService {
    public String guardarArchivoEnSistema(MultipartFile archivo){
        try{
            String extension = Objects.requireNonNull(archivo.getOriginalFilename()).substring(archivo.getOriginalFilename().lastIndexOf("."));
            String nombreArchivo = UUID.randomUUID() + extension;
            Path rutaDestino = Paths.get("./uploads/").resolve(nombreArchivo).normalize();
            if(!Files.exists(rutaDestino.getParent())){
                Files.createDirectories(rutaDestino.getParent());
            }

            Files.copy(archivo.getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);

            return nombreArchivo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
