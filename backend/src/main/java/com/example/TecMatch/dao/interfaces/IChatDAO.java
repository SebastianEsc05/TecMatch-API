package com.example.TecMatch.dao.interfaces;

import com.example.TecMatch.domain.Chat;
import com.example.TecMatch.domain.Usuario;

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
