package com.example.TecMatch.controller;

import com.example.TecMatch.dto.HobbieUsuarioDTO;
import com.example.TecMatch.service.impl.HobbieUsuarioService;
import com.example.TecMatch.service.interfaces.IHobbieUsuarioService;

import java.util.List;

public class HobbieUsuarioController {
    private IHobbieUsuarioService huService = new HobbieUsuarioService();

    public void agregarHobbieAUsuario(HobbieUsuarioDTO dto) {
        System.out.println("--- Ejecutando: Agregar Hobbie a Usuario ---");
        try {
            HobbieUsuarioDTO relacion = huService.agregarHobbieAUsuario(dto);
            System.out.println("ÉXITO: Hobbie '" + relacion.getHobbieDescripcion() + "' agregado a usuario '" + relacion.getUsuarioNombre() + "'");
            System.out.println("  ID de Relación: " + relacion.getId());
        } catch (Exception e) {
            System.err.println("FALLO al agregar hobbie: " + e.getMessage());
        }
    }

    public void eliminarHobbieDeUsuario(Long idRelacion) {
        System.out.println("\n--- Ejecutando: Eliminar Relación Hobbie-Usuario ID " + idRelacion + " ---");
        try {
            huService.eliminarHobbieDeUsuario(idRelacion);
            System.out.println("ÉXITO: Relación con ID " + idRelacion + " eliminada.");

        } catch (Exception e) {
            System.err.println("FALLO al eliminar relación: " + e.getMessage());
        }
    }

    public void listarHobbiesDeUsuario(Long usuarioId) {
        System.out.println("\n--- Ejecutando: Listar Hobbies del Usuario ID " + usuarioId + " ---");
        try {
            List<HobbieUsuarioDTO> lista = huService.listarHobbiesDeUsuario(usuarioId);
            System.out.println("ÉXITO: El usuario " + usuarioId + " tiene " + lista.size() + " hobbies:");
            for (HobbieUsuarioDTO dto : lista) {
                System.out.println("  - " + dto.getHobbieDescripcion() + " (Relación ID: " + dto.getId() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar hobbies: " + e.getMessage());
        }
    }

    public void listarUsuariosPorHobbie(Long hobbieId) {
        System.out.println("\n--- Ejecutando: Listar Usuarios con Hobbie ID " + hobbieId + " ---");
        try {
            List<HobbieUsuarioDTO> lista = huService.listarUsuariosPorHobbie(hobbieId);

            System.out.println("ÉXITO: " + lista.size() + " usuarios tienen el hobbie " + hobbieId + ":");
            for (HobbieUsuarioDTO dto : lista) {
                System.out.println("  - " + dto.getUsuarioNombre() + " (Usuario ID: " + dto.getUsuarioId() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar usuarios por hobbie: " + e.getMessage());
        }
    }

    public void listarRelaciones(int limite) {
        System.out.println("\n--- Ejecutando: Listar " + limite + " Relaciones Hobbie-Usuario ---");
        try {
            List<HobbieUsuarioDTO> lista = huService.listar(limite);
            System.out.println("ÉXITO: Se encontraron " + lista.size() + " relaciones.");
            for (HobbieUsuarioDTO dto : lista) {
                System.out.println("  - ID: " + dto.getId() + " (Usuario " + dto.getUsuarioId() + " -> Hobbie " + dto.getHobbieId() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar relaciones: " + e.getMessage());
        }
    }
}