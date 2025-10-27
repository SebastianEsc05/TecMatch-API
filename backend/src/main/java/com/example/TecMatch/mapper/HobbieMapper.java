package com.example.TecMatch.mapper;

import com.example.TecMatch.domain.Hobbie;
import com.example.TecMatch.dto.HobbieDTO;

public class HobbieMapper {

    public static Hobbie mapToEntity(HobbieDTO dto) {
        if (dto == null) return null;
        Hobbie entity = new Hobbie();
        entity.setId(dto.getId());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }

    public static HobbieDTO mapToDTO(Hobbie entity) {
        if (entity == null) return null;
        return new HobbieDTO(
                entity.getId(),
                entity.getDescripcion()
        );
    }
}