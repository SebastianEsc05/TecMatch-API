package com.example.PotroNet.controller;

import com.example.PotroNet.dto.ChatDTO;
import com.example.PotroNet.service.impl.ChatService;
import com.example.PotroNet.service.interfaces.IChatService;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ChatController {
    private final IChatService chatService;

    public ChatController(EntityManagerFactory emf) {
        chatService = new ChatService(emf);
    }

    public void crearChat(ChatDTO dto) {
        System.out.println("--- Ejecutando: Crear Chat ---");
        try {
            ChatDTO nuevoChat = chatService.crearChat(dto);
            System.out.println("ÉXITO: Chat " + nuevoChat.getTipo() + " creado con ID: " + nuevoChat.getId());
            System.out.println("  Participantes: " + nuevoChat.getUsuarioIds());

        } catch (Exception e) {
            System.err.println("FALLO al crear chat: " + e.getMessage());
        }
    }

    public void buscarPorId(Long id) {
        System.out.println("\n--- Ejecutando: Buscar Chat por ID " + id + " ---");
        try {
            ChatDTO chat = chatService.buscarPorId(id);
            System.out.println("ÉXITO: Chat encontrado:");
            System.out.println("  ID: " + chat.getId() + ", Tipo: " + chat.getTipo());
            System.out.println("  Fecha Creación: " + chat.getFecha_creacion());
            System.out.println("  Participantes (IDs): " + chat.getUsuarioIds());

        } catch (Exception e) {
            System.err.println("FALLO al buscar chat: " + e.getMessage());
        }
    }

    public void listarChats(int limite) {
        System.out.println("\n--- Ejecutando: Listar Chats (límite " + limite + ") ---");
        try {
            List<ChatDTO> chats = chatService.listarChats(limite);
            System.out.println("ÉXITO: Se encontraron " + chats.size() + " chats.");
            for (ChatDTO chat : chats) {
                System.out.println("  - ID: " + chat.getId() + ", Tipo: " + chat.getTipo() + ", Participantes: " + chat.getUsuarioIds().size());
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar chats: " + e.getMessage());
        }
    }

    public void buscarChatsPorUsuarioId(Long usuarioId) {
        System.out.println("\n--- Ejecutando: Buscar Chats del Usuario ID " + usuarioId + " ---");
        try {
            List<ChatDTO> chats = chatService.buscarChatsPorUsuarioId(usuarioId);
            System.out.println("ÉXITO: El usuario " + usuarioId + " está en " + chats.size() + " chats:");
            for (ChatDTO chat : chats) {
                System.out.println("  - Chat ID: " + chat.getId() + " (Tipo: " + chat.getTipo() + "), Otros participantes: " +
                        chat.getUsuarioIds().stream()
                                .filter(id -> !id.equals(usuarioId))
                                .collect(Collectors.toSet()));
            }
        } catch (Exception e) {
            System.err.println("FALLO al buscar chats por usuario: " + e.getMessage());
        }
    }

    public void agregarUsuarioAChat(Long chatId, Long usuarioId) {
        System.out.println("\n--- Ejecutando: Agregar Usuario " + usuarioId + " al Chat " + chatId + " ---");
        try {
            chatService.agregarUsuarioAChat(chatId, usuarioId);
            System.out.println("ÉXITO: Usuario " + usuarioId + " agregado al Chat " + chatId);

        } catch (Exception e) {
            System.err.println("FALLO al agregar usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuarioDeChat(Long chatId, Long usuarioId) {
        System.out.println("\n--- Ejecutando: Eliminar Usuario " + usuarioId + " del Chat " + chatId + " ---");
        try {
            chatService.eliminarUsuarioDeChat(chatId, usuarioId);
            System.out.println("ÉXITO: Usuario " + usuarioId + " eliminado del Chat " + chatId);

        } catch (Exception e) {
            System.err.println("FALLO al eliminar usuario: " + e.getMessage());
        }
    }

    public void eliminarChat(Long id) {
        System.out.println("\n--- Ejecutando: Eliminar Chat ID " + id + " ---");
        try {
            chatService.eliminarChat(id);
            System.out.println("ÉXITO: Chat con ID " + id + " eliminado.");
            System.out.println("  (Todos los mensajes y participantes asociados han sido eliminados en cascada)");

        } catch (Exception e) {
            System.err.println("FALLO al eliminar chat: " + e.getMessage());
        }
    }
}