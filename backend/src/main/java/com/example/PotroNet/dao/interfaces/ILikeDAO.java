package com.example.PotroNet.dao.interfaces;

import com.example.PotroNet.domain.Like;

import java.util.List;

public interface ILikeDAO {
    void crear(Like like);
    void eliminar(Long id);
    Like buscarPorId(Long id);
    List<Like> listar();
    Like findLike(Long likerId, Long likedId);
    List<Like> findLikesRecibidos(Long likedId);
    List<Like> findLikesDados(Long likerId);
}
