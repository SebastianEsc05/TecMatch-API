package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.domain.Chat;

import java.util.List;

public interface IChatService {
    boolean crear(Chat Chat);
    Chat buscarPorId(Long id);
    List<Chat> listar(int limite);
    boolean actualizar(Chat Chat);
    boolean eliminar(Long id);
}
