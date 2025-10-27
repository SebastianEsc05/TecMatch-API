package com.example.TecMatch.mapper;

import com.example.TecMatch.domain.Interes;
import com.example.TecMatch.domain.InteresUsuario;
import com.example.TecMatch.domain.Usuario;
import com.example.TecMatch.dto.InteresUsuarioDTO;

public class InteresUsuarioMapper {

    public static InteresUsuarioDTO mapToDTO(InteresUsuario iu) {
        if (iu == null) {
            return null;
        }
        InteresUsuarioDTO dto = new InteresUsuarioDTO();
        dto.setId(iu.getId());
        if (iu.getUsuario() != null) {
            dto.setUsuarioId(iu.getUsuario().getId());
            dto.setUsuarioNombre(iu.getUsuario().getNombre());
        }
        if (iu.getInteres() != null) {
            dto.setInteresId(iu.getInteres().getId());
            dto.setInteresDescripcion(iu.getInteres().getDescripcion());
        }
        return dto;
    }

    public static InteresUsuario mapToEntity(InteresUsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        InteresUsuario iu = new InteresUsuario();
        iu.setId(dto.getId());
        if (dto.getUsuarioId() != null) {
            Usuario u = new Usuario();
            u.setId(dto.getUsuarioId());
            iu.setUsuario(u);
        }
        if (dto.getInteresId() != null) {
            Interes i = new Interes();
            i.setId(dto.getInteresId());
            iu.setInteres(i);
        }
        return iu;
    }
}