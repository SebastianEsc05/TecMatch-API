package com.example.PotroNet.mapper;

import com.example.PotroNet.domain.Interes;
import com.example.PotroNet.dto.InteresDTO;

public class InteresMapper {

    public static Interes mapToEntity(InteresDTO interesDTO) {
        if (interesDTO == null) return null;
        Interes entity = new Interes();
        entity.setId(interesDTO.getId());
        entity.setDescripcion(interesDTO.getDescripcion());
        return entity;
    }

    public static InteresDTO mapToDTO(Interes interes) {
        if (interes == null) return null;
        return new InteresDTO(
                interes.getId(),
                interes.getDescripcion()
        );
    }
}