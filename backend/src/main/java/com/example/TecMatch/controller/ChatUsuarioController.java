package com.example.TecMatch.controller;

import com.example.TecMatch.dto.ChatUsuarioDTO;
import com.example.TecMatch.service.impl.ChatUsuarioService;
import com.example.TecMatch.service.interfaces.IChatUsuarioService;

import java.util.List;

public class ChatUsuarioController {
    private final IChatUsuarioService chatUsuarioService = new ChatUsuarioService();

    public void agregarUsuarioAChat(ChatUsuarioDTO dto) {
        System.out.println("--- Ejecutando: Agregar Usuario " + dto.getUsuarioId() + " a Chat " + dto.getChatId() + " ---");
        try {
            ChatUsuarioDTO nuevaRelacion = chatUsuarioService.agregarUsuarioAChat(dto);
            System.out.println("ÉXITO: Relación creada con ID: " + nuevaRelacion.getId());
            System.out.println("  Usuario " + nuevaRelacion.getUsuarioId() + " ahora está en el Chat " + nuevaRelacion.getChatId());

        } catch (Exception e) {
            System.err.println("FALLO al agregar usuario a chat: " + e.getMessage());
        }
    }

    public void eliminarUsuarioDeChat(Long chatUsuarioId) {
        System.out.println("\n--- Ejecutando: Eliminar Relación Chat-Usuario ID " + chatUsuarioId + " ---");
        try {
            chatUsuarioService.eliminarUsuarioDeChat(chatUsuarioId);
            System.out.println("ÉXITO: Relación con ID " + chatUsuarioId + " eliminada.");

        } catch (Exception e) {
            System.err.println("FALLO al eliminar relación: " + e.getMessage());
        }
    }

    public void buscarRelacionPorId(Long id) {
        System.out.println("\n--- Ejecutando: Buscar Relación Chat-Usuario por ID " + id + " ---");
        try {
            ChatUsuarioDTO dto = chatUsuarioService.buscarPorId(id);
            System.out.println("ÉXITO: Relación encontrada:");
            System.out.println("  - Chat ID: " + dto.getChatId());
            System.out.println("  - Usuario ID: " + dto.getUsuarioId());

        } catch (Exception e) {
            System.err.println("FALLO al buscar por ID: " + e.getMessage());
        }
    }

    public void buscarUsuariosPorChatId(Long chatId) {
        System.out.println("\n--- Ejecutando: Buscar Usuarios del Chat ID " + chatId + " ---");
        try {
            List<ChatUsuarioDTO> relaciones = chatUsuarioService.buscarUsuariosPorChatId(chatId);
            System.out.println("ÉXITO: Se encontraron " + relaciones.size() + " usuarios en el chat " + chatId + ":");
            for (ChatUsuarioDTO dto : relaciones) {
                System.out.println("  - Usuario ID: " + dto.getUsuarioId() + " (Relación ID: " + dto.getId() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al buscar usuarios por chat: " + e.getMessage());
        }
    }

    public void buscarChatsPorUsuarioId(Long usuarioId) {
        System.out.println("\n--- Ejecutando: Buscar Chats del Usuario ID " + usuarioId + " ---");
        try {
            List<ChatUsuarioDTO> relaciones = chatUsuarioService.buscarChatsPorUsuarioId(usuarioId);
            System.out.println("ÉXITO: El usuario " + usuarioId + " está en " + relaciones.size() + " chats:");
            for (ChatUsuarioDTO dto : relaciones) {
                System.out.println("  - Chat ID: " + dto.getChatId() + " (Relación ID: " + dto.getId() + ")");
            }

        } catch (Exception e) {
            System.err.println("FALLO al buscar chats por usuario: " + e.getMessage());
        }
    }
}