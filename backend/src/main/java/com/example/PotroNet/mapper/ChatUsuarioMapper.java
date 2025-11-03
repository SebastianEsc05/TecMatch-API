package com.example.PotroNet.mapper;

import com.example.PotroNet.domain.Chat;
import com.example.PotroNet.domain.ChatUsuario;
import com.example.PotroNet.domain.Usuario;
import com.example.PotroNet.dto.ChatUsuarioDTO;

public class ChatUsuarioMapper {

    public static ChatUsuarioDTO mapToDTO(ChatUsuario chatUsuario) {
        if (chatUsuario == null) {
            return null;
        }
        ChatUsuarioDTO chatUsuarioDTO = new ChatUsuarioDTO();
        chatUsuarioDTO.setId(chatUsuario.getId());
        if (chatUsuario.getChat() != null) {
            chatUsuarioDTO.setChatId(chatUsuario.getChat().getId());
        }
        if (chatUsuario.getUsuario() != null) {
            chatUsuarioDTO.setUsuarioId(chatUsuario.getUsuario().getId());
        }
        return chatUsuarioDTO;
    }

    public static ChatUsuario mapToEntity(ChatUsuarioDTO chatUsuarioDTO) {
        if (chatUsuarioDTO == null) {
            return null;
        }
        ChatUsuario chatUsuario = new ChatUsuario();
        chatUsuario.setId(chatUsuarioDTO.getId());

        if (chatUsuarioDTO.getChatId() != null) {
            Chat chat = new Chat();
            chat.setId(chatUsuarioDTO.getChatId());
            chatUsuario.setChat(chat);
        }
        if (chatUsuarioDTO.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(chatUsuarioDTO.getUsuarioId());
            chatUsuario.setUsuario(usuario);
        }
        return chatUsuario;
    }
}
