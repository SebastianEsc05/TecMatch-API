package com.example.PotroNet.service.interfaces;

import com.example.PotroNet.dto.ChatUsuarioDTO;

import java.util.List;

public interface IChatUsuarioService {
    ChatUsuarioDTO agregarUsuarioAChat(ChatUsuarioDTO dto) throws Exception;
    void eliminarUsuarioDeChat(Long chatUsuarioId) throws Exception;
    List<ChatUsuarioDTO> buscarUsuariosPorChatId(Long chatId) throws Exception;
    List<ChatUsuarioDTO> buscarChatsPorUsuarioId(Long usuarioId) throws Exception;
    ChatUsuarioDTO buscarPorId(Long id) throws Exception;
}
