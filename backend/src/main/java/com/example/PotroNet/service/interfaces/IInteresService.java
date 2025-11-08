package com.example.PotroNet.service.interfaces;

import com.example.PotroNet.dto.InteresDTO;

import java.util.List;

public interface IInteresService {
    InteresDTO crearInteres(InteresDTO interesDTO) throws Exception;
    InteresDTO buscarPorId(Long id);
    InteresDTO buscarPorDescripcion(String descripcion);
    List<InteresDTO> listarIntereses(int limite);
    int contarIntereses();
    InteresDTO actualizarInteres(Long id, InteresDTO interesDTO) throws Exception;
    void eliminarInteres(Long id) throws Exception;
}
