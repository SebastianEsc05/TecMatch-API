package com.example.TecMatch;

import com.example.TecMatch.config.JpaUtil;
import com.example.TecMatch.controller.UsuarioController;
import com.example.TecMatch.dto.UsuarioDTO;
import com.example.TecMatch.service.impl.UsuarioService;
import com.example.TecMatch.service.interfaces.IUsuarioService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Set;

public class MainPruebasConsola {
    public static void main(String [] args) {

        try {
            // 1. Instanciar el Servicio
            IUsuarioService servicio = new UsuarioService();

            // 2. Instanciar el Controlador
            UsuarioController controller = new UsuarioController(servicio);

            // 3. Creamos DTOs de prueba
            UsuarioDTO dto1 = new UsuarioDTO();
            dto1.setNombre("Ana López (MainTest)");
            dto1.setCorreo("ana.lopez.main@example.com");
            dto1.setContrasenia("pass123");
            dto1.setCarrera("ISC");
            dto1.setSexo("Femenino");
            dto1.setFechaNacimiento(LocalDate.of(2000, 5, 15));
            dto1.setHobbies(Set.of("Leer", "Videojuegos"));
            dto1.setIntereses(Set.of("Programación", "IA"));


            UsuarioDTO dto2 = new UsuarioDTO();
            dto2.setNombre("Luis Martínez (MainTest)");
            dto2.setCorreo("luis.martinez.main@example.com");
            dto2.setContrasenia("pass456");
            dto2.setCarrera("LNI");
            dto2.setSexo("Masculino");
            dto2.setFechaNacimiento(LocalDate.of(1999, 10, 20));
            dto2.setHobbies(Set.of("Fútbol", "Guitarra"));
            dto2.setIntereses(Set.of("Negocios", "Marketing"));

            // Probar CREAR
            controller.crearUsuario(dto1);
            controller.crearUsuario(dto2);

            // Probar LISTAR
            controller.listarUsuarios(10);

            Long idAna = 1L;
            Long idLuis = 2L;

            // Probar BUSCAR POR ID (éxito)
            controller.buscarUsuarioPorId(idAna);

            // Probar BUSCAR POR ID (fallo)
            controller.buscarUsuarioPorId(999L);

            // Probar BUSCAR POR CORREO (éxito)
            controller.buscarUsuarioPorCorreo("luis.martinez.main@example.com");

            // Probar BUSCAR POR CORREO (fallo)
            controller.buscarUsuarioPorCorreo("nadie@example.com");

            // Probar ACTUALIZAR
            UsuarioDTO dtoActualizado = new UsuarioDTO();
            dtoActualizado.setNombre("Ana López de García (Actualizada)");
            dtoActualizado.setCorreo("ana.garcia.main@example.com");
            dtoActualizado.setCarrera("ISD");
            dtoActualizado.setContrasenia("nuevaPass789");
            dtoActualizado.setSexo(dto1.getSexo());
            dtoActualizado.setFechaNacimiento(dto1.getFechaNacimiento());
            dtoActualizado.setHobbies(Set.of("Nadar"));
            dtoActualizado.setIntereses(Set.of("Ciberseguridad"));

            controller.actualizarUsuario(idAna, dtoActualizado);

            // Probar BUSCAR POR ID (para verificar actualización)
            controller.buscarUsuarioPorId(idAna);

            // Probar ELIMINAR
            controller.eliminarUsuario(idLuis);

            // Probar LISTAR (para verificar eliminación, solo debe quedar Ana)
            controller.listarUsuarios(10);

            // Probar ELIMINAR (fallo, ya no existe)
            controller.eliminarUsuario(idLuis);

            // Probar ELIMINAR (el último usuario)
            controller.eliminarUsuario(idAna);

            // Probar LISTAR (debería estar vacío)
            controller.listarUsuarios(10);

        } catch (Exception e) {
            System.err.println("\nERROR EN LA PRUEBA");
        } finally {
            System.out.println("\n--- Cerrando Conexión (JpaUtil.closeFactory) ---");
            JpaUtil.close();
        }
        System.out.println("\n====== PRUEBAS FINALIZADAS ======");
    }
}
