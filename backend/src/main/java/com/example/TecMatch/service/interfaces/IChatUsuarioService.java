package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.dto.ChatUsuarioDTO;

import java.util.List;

public interface IChatUsuarioService {
    ChatUsuarioDTO agregarUsuarioAChat(ChatUsuarioDTO dto) throws Exception;
    void eliminarUsuarioDeChat(Long chatUsuarioId) throws Exception;
    List<ChatUsuarioDTO> buscarUsuariosPorChatId(Long chatId) throws Exception;
    List<ChatUsuarioDTO> buscarChatsPorUsuarioId(Long usuarioId) throws Exception;
    ChatUsuarioDTO buscarPorId(Long id) throws Exception;
}
