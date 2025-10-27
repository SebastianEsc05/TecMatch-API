package com.example.TecMatch.controller;

import com.example.TecMatch.dto.LikeDTO;
import com.example.TecMatch.dto.MatchDTO;
import com.example.TecMatch.service.impl.LikeService;
import com.example.TecMatch.service.interfaces.ILikeService;

import java.util.List;

public class LikeController {
    private final ILikeService likeService = new LikeService();

    public void crearLike(LikeDTO dto) {
        System.out.println("--- Ejecutando: Registrar Like ---");
        try {
            MatchDTO matchCreado = likeService.crearLikeYVerificarMatch(dto);
            System.out.println("ÉXITO: Like registrado (Usuario " + dto.getLikerId() + " -> Usuario " + dto.getLikedId() + ")");
            if (matchCreado != null) {
                System.out.println("  ¡ES UN MATCH! Se creó un nuevo Match con ID: " + matchCreado.getId());
                System.out.println("  Usuarios: " + matchCreado.getUsuario1Id() + " <-> " + matchCreado.getUsuario2Id());
            } else {
                System.out.println("  (Aún no es un match mutuo)");
            }

        } catch (Exception e) {
            System.err.println("FALLO al registrar like: " + e.getMessage());
        }
    }

    public void getLikesRecibidos(Long usuarioId) {
        System.out.println("\n--- Ejecutando: Listar Likes Recibidos por Usuario ID " + usuarioId + " ---");
        try {
            List<LikeDTO> likes = likeService.getLikesRecibidos(usuarioId);
            System.out.println("ÉXITO: El usuario " + usuarioId + " ha recibido " + likes.size() + " likes:");
            for (LikeDTO like : likes) {
                System.out.println("  - Like ID: " + like.getId() + " (De: Usuario " + like.getLikerId() + " el " + like.getFecha_hora() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar likes recibidos: " + e.getMessage());
        }
    }

    public void getLikesDados(Long usuarioId) {
        System.out.println("\n--- Ejecutando: Listar Likes Dados por Usuario ID " + usuarioId + " ---");
        try {
            List<LikeDTO> likes = likeService.getLikesDados(usuarioId);
            System.out.println("ÉXITO: El usuario " + usuarioId + " ha dado " + likes.size() + " likes:");
            for (LikeDTO like : likes) {
                System.out.println("  - Like ID: " + like.getId() + " (A: Usuario " + like.getLikedId() + " el " + like.getFecha_hora() + ")");
            }
        } catch (Exception e) {
            System.err.println("FALLO al listar likes dados: " + e.getMessage());
        }
    }

    public void eliminarLike(Long id) {
        System.out.println("\n--- Ejecutando: Eliminar Like ID " + id + " ---");
        try {
            likeService.eliminarLike(id);
            System.out.println("ÉXITO: Like con ID " + id + " eliminado.");

        } catch (Exception e) {
            System.err.println("FALLO al eliminar like: " + e.getMessage());
        }
    }
}