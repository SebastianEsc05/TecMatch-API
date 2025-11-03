package com.example.PotroNet.service.interfaces;

import com.example.PotroNet.dto.LikeDTO;
import com.example.PotroNet.dto.MatchDTO;

import java.util.List;

public interface ILikeService {
    MatchDTO crearLikeYVerificarMatch(LikeDTO likeDTO) throws Exception;
    void eliminarLike(Long id) throws Exception;
    List<LikeDTO> getLikesRecibidos(Long usuarioId) throws Exception;
    List<LikeDTO> getLikesDados(Long usuarioId) throws Exception;

}
