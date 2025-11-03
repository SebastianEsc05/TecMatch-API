package com.example.PotroNet.dao.interfaces;

import com.example.PotroNet.domain.InteresUsuario;

import java.util.List;

public interface IInteresUsuarioDAO {
    void crear(InteresUsuario interesUsuario);
    InteresUsuario buscarPorId(Long id);
    List<InteresUsuario> listar(int limite);
    void eliminar(Long id);
    List<InteresUsuario> buscarPorUsuarioId(Long usuarioId);
    List<InteresUsuario> buscarPorInteresId(Long interesId);
    InteresUsuario buscarPorUsuarioEInteres(Long usuarioId, Long interesId);
}
