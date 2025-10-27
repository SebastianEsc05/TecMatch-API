package com.example.TecMatch.mapper;

import com.example.TecMatch.domain.Chat;
import com.example.TecMatch.dto.ChatDTO;

import java.util.stream.Collectors;

public class ChatMapper {

    public static ChatDTO mapToDTO(Chat chat) {
        if (chat == null) {
            return null;
        }
        ChatDTO dto = new ChatDTO();
        dto.setId(chat.getId());
        dto.setFecha_creacion(chat.getFecha_creacion());
        dto.setTipo(chat.getTipo());
        if (chat.getChatUsuarios() != null) {
            dto.setUsuarioIds(
                    chat.getChatUsuarios().stream()
                            .map(chatUsuario -> chatUsuario.getUsuario().getId())
                            .collect(Collectors.toSet())
            );
        }
        return dto;
    }

    public static Chat mapToEntity(ChatDTO dto) {
        if (dto == null) {
            return null;
        }
        Chat chat = new Chat();
        chat.setId(dto.getId());
        chat.setFecha_creacion(dto.getFecha_creacion());
        chat.setTipo(dto.getTipo());
        return chat;
    }
}
