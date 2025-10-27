package com.example.TecMatch.controller;

import com.example.TecMatch.dto.HobbieDTO;
import com.example.TecMatch.service.impl.HobbieService;
import com.example.TecMatch.service.interfaces.IHobbieService;
import java.util.List;

public class HobbieController {
    private final IHobbieService hobbieService = new HobbieService();

    public void crearHobbie(HobbieDTO dto) {
        System.out.println("--- Ejecutando: Crear Hobbie ---");
        try {
            HobbieDTO nuevoHobbie = hobbieService.crearHobbie(dto);
            System.out.println("ÉXITO: Hobbie creado con ID: " + nuevoHobbie.getId());
            System.out.println("  Descripción: " + nuevoHobbie.getDescripcion());

        } catch (Exception e) {
            System.err.println("FALLO al crear hobbie: " + e.getMessage());
        }
    }

    public void actualizarHobbie(Long id, HobbieDTO dto) {
        System.out.println("\n--- Ejecutando: Actualizar Hobbie ID " + id + " ---");
        try {
            HobbieDTO actualizado = hobbieService.actualizarHobbie(id, dto);
            System.out.println("ÉXITO: Hobbie actualizado.");
            System.out.println("  Nueva Descripción: " + actualizado.getDescripcion());

        } catch (Exception e) {
            System.err.println("FALLO al actualizar hobbie: " + e.getMessage());
        }
    }

    public void buscarPorId(Long id) {
        System.out.println("\n--- Ejecutando: Buscar Hobbie por ID " + id + " ---");
        try {
            HobbieDTO hobbie = hobbieService.buscarPorId(id);
            if (hobbie != null) {
                System.out.println("ÉXITO: Hobbie encontrado:");
                System.out.println("  ID: " + hobbie.getId() + ", Desc: " + hobbie.getDescripcion());
            } else {
                System.out.println("AVISO: No se encontró hobbie con ID: " + id);
            }

        } catch (Exception e) {
            System.err.println("FALLO al buscar por ID: " + e.getMessage());
        }
    }

    public void buscarPorDescripcion(String descripcion) {
        System.out.println("\n--- Ejecutando: Buscar Hobbie por Descripción '" + descripcion + "' ---");
        try {
            HobbieDTO hobbie = hobbieService.buscarPorDescripcion(descripcion);
            if (hobbie != null) {
                System.out.println("ÉXITO: Hobbie encontrado:");
                System.out.println("  ID: " + hobbie.getId() + ", Desc: " + hobbie.getDescripcion());
            } else {
                System.out.println("AVISO: No se encontró hobbie con la descripción: " + descripcion);
            }

        } catch (Exception e) {
            System.err.println("FALLO al buscar por descripción: " + e.getMessage());
        }
    }

    public void listarHobbies(int limite) {
        System.out.println("\n--- Ejecutando: Listar Hobbies (límite " + limite + ") ---");
        try {
            List<HobbieDTO> hobbies = hobbieService.listarHobbies(limite);
            System.out.println("ÉXITO: Se encontraron " + hobbies.size() + " hobbies.");
            for (HobbieDTO h : hobbies) {
                System.out.println("  - ID: " + h.getId() + ", Desc: " + h.getDescripcion());
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar hobbies: " + e.getMessage());
        }
    }

    public void eliminarHobbie(Long id) {
        System.out.println("\n--- Ejecutando: Eliminar Hobbie ID " + id + " ---");
        try {
            hobbieService.eliminarHobbie(id);
            System.out.println("ÉXITO: Hobbie con ID " + id + " eliminado.");

        } catch (Exception e) {
            System.err.println("FALLO al eliminar hobbie: " + e.getMessage());
        }
    }
}