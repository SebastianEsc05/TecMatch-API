package com.example.TecMatch.mapper;

import com.example.TecMatch.domain.Like;
import com.example.TecMatch.domain.Usuario;
import com.example.TecMatch.dto.LikeDTO;

public class LikeMapper {

    public static LikeDTO mapToDTO(Like like) {
        if (like == null) {
            return null;
        }

        LikeDTO dto = new LikeDTO();
        dto.setId(like.getId());
        dto.setFecha_hora(like.getFecha_hora());

        if (like.getLiker() != null) {
            dto.setLikerId(like.getLiker().getId());
        }

        if (like.getLiked() != null) {
            dto.setLikedId(like.getLiked().getId());
        }

        return dto;
    }

    public static Like mapToEntity(LikeDTO dto) {
        if (dto == null) {
            return null;
        }

        Like like = new Like();
        like.setId(dto.getId());
        like.setFecha_hora(dto.getFecha_hora());

        if (dto.getLikerId() != null) {
            Usuario liker = new Usuario();
            liker.setId(dto.getLikerId());
            like.setLiker(liker);
        }

        if (dto.getLikedId() != null) {
            Usuario liked = new Usuario();
            liked.setId(dto.getLikedId());
            like.setLiked(liked);
        }
        return like;
    }
}
