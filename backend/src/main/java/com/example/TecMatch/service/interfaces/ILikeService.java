package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.domain.Like;
import com.example.TecMatch.dto.LikeDTO;
import com.example.TecMatch.dto.MatchDTO;

import java.util.List;

public interface ILikeService {
    MatchDTO crearLikeYVerificarMatch(LikeDTO likeDTO) throws Exception;
    void eliminarLike(Long id) throws Exception;
    List<LikeDTO> getLikesRecibidos(Long usuarioId) throws Exception;
    List<LikeDTO> getLikesDados(Long usuarioId) throws Exception;

}
