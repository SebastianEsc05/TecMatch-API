package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.dto.HobbieDTO;

import java.util.List;

public interface IHobbieService {
    HobbieDTO crearHobbie(HobbieDTO hobbieDTO) throws Exception;
    HobbieDTO buscarPorId(Long id);
    HobbieDTO buscarPorDescripcion(String descripcion);
    List<HobbieDTO> listarHobbies(int limite);
    HobbieDTO actualizarHobbie(Long id, HobbieDTO hobbieDTO) throws Exception;
    void eliminarHobbie(Long id) throws Exception;
}
