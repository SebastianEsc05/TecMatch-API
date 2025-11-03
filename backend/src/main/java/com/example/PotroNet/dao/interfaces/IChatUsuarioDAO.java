package com.example.PotroNet.dao.interfaces;

import com.example.PotroNet.domain.ChatUsuario;

import java.util.List;

public interface IChatUsuarioDAO {
    void crear(ChatUsuario chatUsuario);
    ChatUsuario actualizar(ChatUsuario chatUsuario);
    void eliminar(Long id);
    ChatUsuario buscarPorId(Long id);
    List<ChatUsuario> listar();
    List<ChatUsuario> buscarPorChatId(Long chatId);
    List<ChatUsuario> buscarPorUsuarioId(Long usuarioId);
}
