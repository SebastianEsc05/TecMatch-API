package com.example.PotroNet.dao.interfaces;

import com.example.PotroNet.domain.Chat;

import java.util.List;

public interface IChatDAO {
    void crear(Chat chat);
    Chat buscarPorId(Long id);
    List<Chat> listar(int limite);
    Chat actualizar(Chat chat);
    void eliminar(Long id);
    Chat buscarPorMatchId(Long matchId);
    List<Chat> buscarPorUsuarioId(Long usuarioId);
}
