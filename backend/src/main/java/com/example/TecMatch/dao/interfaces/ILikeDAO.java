package com.example.TecMatch.dao.interfaces;

import com.example.TecMatch.domain.Like;

import java.util.List;

public interface ILikeDAO {
    boolean crear(Like like);
    Like buscarPorId(Long id);
    List<Like> listar(int limite);
    boolean actualizar(Like like);
    boolean eliminar(Long id);

}
