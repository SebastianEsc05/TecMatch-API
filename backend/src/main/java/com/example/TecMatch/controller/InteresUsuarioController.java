package com.example.TecMatch.controller;

import com.example.TecMatch.dto.InteresUsuarioDTO;
import com.example.TecMatch.service.impl.InteresUsuarioService;
import com.example.TecMatch.service.interfaces.IInteresUsuarioService;

import java.util.List;

public class InteresUsuarioController {
    private final IInteresUsuarioService interesUsuarioService = new InteresUsuarioService();

    public void agregarInteresAUsuario(InteresUsuarioDTO dto) {
        System.out.println("--- Ejecutando: Agregar Interés a Usuario ---");
        try {
            InteresUsuarioDTO relacion = interesUsuarioService.agregarInteresAUsuario(dto);
            System.out.println("ÉXITO: Interés '" + relacion.getInteresDescripcion() + "' agregado a usuario '" + relacion.getUsuarioNombre() + "'");
            System.out.println("  ID de Relación: " + relacion.getId());

        } catch (Exception e) {
            System.err.println("FALLO al agregar interés: " + e.getMessage());
        }
    }

    public void eliminarInteresDeUsuario(Long idRelacion) {
        System.out.println("\n--- Ejecutando: Eliminar Relación Interés-Usuario ID " + idRelacion + " ---");
        try {
            interesUsuarioService.eliminarInteresDeUsuario(idRelacion);
            System.out.println("ÉXITO: Relación con ID " + idRelacion + " eliminada.");

        } catch (Exception e) {
            System.err.println("FALLO al eliminar relación: " + e.getMessage());
        }
    }

    public void listarInteresesDeUsuario(Long usuarioId) {
        System.out.println("\n--- Ejecutando: Listar Intereses del Usuario ID " + usuarioId + " ---");
        try {
            List<InteresUsuarioDTO> lista = interesUsuarioService.listarInteresesDeUsuario(usuarioId);
            System.out.println("ÉXITO: El usuario " + usuarioId + " tiene " + lista.size() + " intereses:");
            for (InteresUsuarioDTO dto : lista) {
                System.out.println("  - " + dto.getInteresDescripcion() + " (Relación ID: " + dto.getId() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar intereses: " + e.getMessage());
        }
    }

    public void listarUsuariosPorInteres(Long interesId) {
        System.out.println("\n--- Ejecutando: Listar Usuarios con Interés ID " + interesId + " ---");
        try {
            List<InteresUsuarioDTO> lista = interesUsuarioService.listarUsuariosPorInteres(interesId);
            System.out.println("ÉXITO: " + lista.size() + " usuarios tienen el interés " + interesId + ":");
            for (InteresUsuarioDTO dto : lista) {
                System.out.println("  - " + dto.getUsuarioNombre() + " (Usuario ID: " + dto.getUsuarioId() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar usuarios por interés: " + e.getMessage());
        }
    }

    public void listarRelaciones(int limite) {
        System.out.println("\n--- Ejecutando: Listar " + limite + " Relaciones Interés-Usuario ---");
        try {
            List<InteresUsuarioDTO> lista = interesUsuarioService.listar(limite);
            System.out.println("ÉXITO: Se encontraron " + lista.size() + " relaciones.");
            for (InteresUsuarioDTO dto : lista) {
                System.out.println("  - ID: " + dto.getId() + " (Usuario " + dto.getUsuarioId() + " -> Interés " + dto.getInteresId() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar relaciones: " + e.getMessage());
        }
    }
}