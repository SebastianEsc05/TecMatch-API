package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.domain.Chat;
import com.example.TecMatch.dto.ChatDTO;

import java.util.List;

public interface IChatService {
    ChatDTO crearChat(ChatDTO chatDTO) throws Exception;
    ChatDTO buscarPorId(Long id) throws Exception;
    List<ChatDTO> listarChats(int limite) throws Exception;
    void eliminarChat(Long id) throws Exception;
    List<ChatDTO> buscarChatsPorUsuarioId(Long usuarioId) throws Exception;
    void agregarUsuarioAChat(Long chatId, Long usuarioId) throws Exception;
    void eliminarUsuarioDeChat(Long chatId, Long usuarioId) throws Exception;
}
