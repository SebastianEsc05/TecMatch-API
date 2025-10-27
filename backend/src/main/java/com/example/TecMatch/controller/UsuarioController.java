package com.example.TecMatch.controller;

import com.example.TecMatch.dto.UsuarioDTO;
import com.example.TecMatch.service.interfaces.IUsuarioService;
import com.example.TecMatch.service.impl.UsuarioService;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class UsuarioController {
    private final IUsuarioService usuarioService;

    public UsuarioController(EntityManagerFactory emf) {
        this.usuarioService = new UsuarioService(emf);
    }

    public void crearUsuario(UsuarioDTO dto) {
        System.out.println("--- Ejecutando: Crear Usuario ---");
        try {
            UsuarioDTO nuevoUsuario = usuarioService.crearUsuario(dto);
            System.out.println("ÉXITO: Usuario creado con ID: " + nuevoUsuario.getId());
        } catch (Exception e) {
            System.err.println("FALLO al crear usuario: " + e.getMessage());
        }
    }

    public void actualizarUsuario(Long id, UsuarioDTO dto) {
        System.out.println("\n--- Ejecutando: Actualizar Usuario ID " + id + " ---");
        try {
            UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, dto);
            System.out.println("ÉXITO: Usuario actualizado. Nuevo nombre: " + usuarioActualizado.getNombre());
        } catch (Exception e) {
            System.err.println("FALLO al actualizar usuario: " + e.getMessage());
        }
    }

    public void buscarUsuarioPorId(Long id) {
        System.out.println("\n--- Ejecutando: Buscar Usuario por ID " + id + " ---");
        try {
            UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
            if (usuario != null) {
                System.out.println("ÉXITO: Usuario encontrado: " + usuario.getNombre());
            } else {
                System.out.println("AVISO: No se encontró usuario con ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("FALLO al buscar por ID: " + e.getMessage());
        }
    }

    public void buscarUsuarioPorCorreo(String correo) {
        System.out.println("\n--- Ejecutando: Buscar Usuario por Correo '" + correo + "' ---");
        try {
            UsuarioDTO usuario = usuarioService.buscarUsuarioPorCorreo(correo);
            if (usuario != null) {
                System.out.println("ÉXITO: Usuario encontrado: " + usuario.getNombre());
            } else {
                System.out.println("AVISO: No se encontró usuario con correo: " + correo);
            }
        } catch (Exception e) {
            System.err.println("FALLO al buscar por Correo: " + e.getMessage());
        }
    }

    public void listarUsuarios(int limite) {
        System.out.println("\n--- Ejecutando: Listar Usuarios (límite " + limite + ") ---");
        try {
            List<UsuarioDTO> usuarios = usuarioService.listarUsuarios(limite);
            System.out.println("ÉXITO: Se encontraron " + usuarios.size() + " usuarios.");
            for (UsuarioDTO u : usuarios) {
                System.out.println("  - ID: " + u.getId() + ", Nombre: " + u.getNombre());
            }
        } catch (Exception e) {
            System.err.println("FALLO al listar usuarios: " + e.getMessage());
        }
    }

    public void eliminarUsuario(Long id) {
        System.out.println("\n--- Ejecutando: Eliminar Usuario ID " + id + " ---");
        try {
            usuarioService.eliminarUsuario(id);
            System.out.println("ÉXITO: Usuario con ID " + id + " eliminado.");

        } catch (Exception e) {
            System.err.println("FALLO al eliminar usuario: " + e.getMessage());
        }
    }
}