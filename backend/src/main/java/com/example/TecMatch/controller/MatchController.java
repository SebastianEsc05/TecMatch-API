package com.example.TecMatch.controller;

import com.example.TecMatch.dto.MatchDTO;
import com.example.TecMatch.service.impl.MatchService;
import com.example.TecMatch.service.interfaces.IMatchService;

import java.util.List;

public class MatchController {
    private final IMatchService matchService = new MatchService();

    public void crearMatch(MatchDTO dto) {
        System.out.println("--- Ejecutando: Crear Match ---");
        try {
            MatchDTO nuevoMatch = matchService.crearMatch(dto);
            System.out.println("ÉXITO: Match creado con ID: " + nuevoMatch.getId());
            System.out.println("  Usuarios: " + nuevoMatch.getUsuario1Id() + " <-> " + nuevoMatch.getUsuario2Id());
            System.out.println("  (Un chat privado ha sido creado para ellos)");
        } catch (Exception e) {
            System.err.println("FALLO al crear match: " + e.getMessage());
        }
    }

    public void buscarMatchPorId(Long id) {
        System.out.println("\n--- Ejecutando: Buscar Match por ID " + id + " ---");
        try {
            MatchDTO match = matchService.buscarMatchPorId(id);
            System.out.println("ÉXITO: Match encontrado:");
            System.out.println("  ID: " + match.getId());
            System.out.println("  Usuario 1: " + match.getUsuario1Id());
            System.out.println("  Usuario 2: " + match.getUsuario2Id());

        } catch (Exception e) {
            System.err.println("FALLO al buscar match: " + e.getMessage());
        }
    }

    public void getMatchEntreUsuarios(Long usuario1Id, Long usuario2Id) {
        System.out.println("\n--- Ejecutando: Buscar Match entre Usuario " + usuario1Id + " y " + usuario2Id + " ---");
        try {
            MatchDTO match = matchService.getMatchEntreUsuarios(usuario1Id, usuario2Id);
            if (match != null) {
                System.out.println("ÉXITO: Se encontró un match con ID: " + match.getId());
            } else {
                System.out.println("AVISO: No existe un match entre estos dos usuarios.");
            }

        } catch (Exception e) {
            System.err.println("FALLO al buscar match: " + e.getMessage());
        }
    }

    public void listarTodosLosMatches() {
        System.out.println("\n--- Ejecutando: Listar Todos los Matches ---");
        try {
            List<MatchDTO> matches = matchService.buscarTodosLosMatches();
            System.out.println("ÉXITO: Se encontraron " + matches.size() + " matches en total.");
            for (MatchDTO m : matches) {
                System.out.println("  - ID: " + m.getId() + " (Usuario " + m.getUsuario1Id() + " <-> Usuario " + m.getUsuario2Id() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar matches: " + e.getMessage());
        }
    }

    public void eliminarMatch(Long id) {
        System.out.println("\n--- Ejecutando: Eliminar Match ID " + id + " ---");
        try {
            matchService.eliminarMatch(id);
            System.out.println("ÉXITO: Match con ID " + id + " eliminado.");
            System.out.println("  (El chat asociado y sus mensajes pueden seguir existiendo)");

        } catch (Exception e) {
            System.err.println("FALLO al eliminar match: " + e.getMessage());
        }
    }
}