package com.example.PotroNet.mapper;

import com.example.PotroNet.domain.Match;
import com.example.PotroNet.domain.Usuario;
import com.example.PotroNet.dto.MatchDTO;

public class MatchMapper {
    public static MatchDTO mapToDTO(Match match) {
        if (match == null) {
            return null;
        }
        return new MatchDTO(
                match.getId(),
                match.getUsuario1() != null ? match.getUsuario1().getId() : null,
                match.getUsuario2() != null ? match.getUsuario2().getId() : null
        );
    }
    public static Match mapToEntity(MatchDTO dto) {
        if (dto == null) {
            return null;
        }
        Match match = new Match();
        match.setId(dto.getId());

        if (dto.getUsuario1Id() != null) {
            Usuario u1 = new Usuario();
            u1.setId(dto.getUsuario1Id());
            match.setUsuario1(u1);
        }
        if (dto.getUsuario2Id() != null) {
            Usuario u2 = new Usuario();
            u2.setId(dto.getUsuario2Id());
            match.setUsuario2(u2);
        }
        return match;
    }
}