package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.dto.InteresDTO;

import java.util.List;

public interface IInteresService {
    InteresDTO crearInteres(InteresDTO interesDTO) throws Exception;
    InteresDTO buscarPorId(Long id);
    InteresDTO buscarPorDescripcion(String descripcion);
    List<InteresDTO> listarIntereses(int limite);
    InteresDTO actualizarInteres(Long id, InteresDTO interesDTO) throws Exception;
    void eliminarInteres(Long id) throws Exception;
}
