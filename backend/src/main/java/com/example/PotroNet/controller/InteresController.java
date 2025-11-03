package com.example.PotroNet.controller;

import com.example.PotroNet.dto.InteresDTO;
import com.example.PotroNet.service.impl.InteresService;
import com.example.PotroNet.service.interfaces.IInteresService;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class InteresController {
    private final IInteresService interesService;

    public InteresController(EntityManagerFactory emf) {
        interesService = new InteresService(emf);
    }

    public void crearInteres(InteresDTO dto) {
        System.out.println("--- Ejecutando: Crear Interés ---");
        try {
            InteresDTO nuevoInteres = interesService.crearInteres(dto);
            System.out.println("ÉXITO: Interés creado con ID: " + nuevoInteres.getId());
            System.out.println("  Descripción: " + nuevoInteres.getDescripcion());

        } catch (Exception e) {
            System.err.println("FALLO al crear interés: " + e.getMessage());
        }
    }

    public void actualizarInteres(Long id, InteresDTO dto) {
        System.out.println("\n--- Ejecutando: Actualizar Interés ID " + id + " ---");
        try {
            InteresDTO actualizado = interesService.actualizarInteres(id, dto);
            System.out.println("ÉXITO: Interés actualizado.");
            System.out.println("  Nueva Descripción: " + actualizado.getDescripcion());

        } catch (Exception e) {
            System.err.println("FALLO al actualizar interés: " + e.getMessage());
        }
    }

    public void buscarPorId(Long id) {
        System.out.println("\n--- Ejecutando: Buscar Interés por ID " + id + " ---");
        try {
            InteresDTO interes = interesService.buscarPorId(id);
            if (interes != null) {
                System.out.println("ÉXITO: Interés encontrado:");
                System.out.println("  ID: " + interes.getId() + ", Desc: " + interes.getDescripcion());
            } else {
                System.out.println("AVISO: No se encontró interés con ID: " + id);
            }

        } catch (Exception e) {
            System.err.println("FALLO al buscar por ID: " + e.getMessage());
        }
    }

    public void buscarPorDescripcion(String descripcion) {
        System.out.println("\n--- Ejecutando: Buscar Interés por Descripción '" + descripcion + "' ---");
        try {
            InteresDTO interes = interesService.buscarPorDescripcion(descripcion);
            if (interes != null) {
                System.out.println("ÉXITO: Interés encontrado:");
                System.out.println("  ID: " + interes.getId() + ", Desc: " + interes.getDescripcion());
            } else {
                System.out.println("AVISO: No se encontró interés con la descripción: " + descripcion);
            }
        } catch (Exception e) {
            System.err.println("FALLO al buscar por descripción: " + e.getMessage());
        }
    }

    public void listarIntereses(int limite) {
        System.out.println("\n--- Ejecutando: Listar Intereses (límite " + limite + ") ---");
        try {
            List<InteresDTO> intereses = interesService.listarIntereses(limite);
            System.out.println("ÉXITO: Se encontraron " + intereses.size() + " intereses.");
            for (InteresDTO i : intereses) {
                System.out.println("  - ID: " + i.getId() + ", Desc: " + i.getDescripcion());
            }
        } catch (Exception e) {
            System.err.println("FALLO al listar intereses: " + e.getMessage());
        }
    }

    public void eliminarInteres(Long id) {
        System.out.println("\n--- Ejecutando: Eliminar Interés ID " + id + " ---");
        try {
            interesService.eliminarInteres(id);
            System.out.println("ÉXITO: Interés con ID " + id + " eliminado.");

        } catch (Exception e) {
            System.err.println("FALLO al eliminar interés: " + e.getMessage());
        }
    }
}