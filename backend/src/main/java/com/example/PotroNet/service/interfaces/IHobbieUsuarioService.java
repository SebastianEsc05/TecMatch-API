package com.example.PotroNet.service.interfaces;

import com.example.PotroNet.dto.HobbieUsuarioDTO;
import java.util.List;

public interface IHobbieUsuarioService {
    HobbieUsuarioDTO agregarHobbieAUsuario(HobbieUsuarioDTO dto) throws Exception;
    void eliminarHobbieDeUsuario(Long idRelacion) throws Exception;
    List<HobbieUsuarioDTO> listarHobbiesDeUsuario(Long usuarioId) throws Exception;
    List<HobbieUsuarioDTO> listarUsuariosPorHobbie(Long hobbieId) throws Exception;
    HobbieUsuarioDTO buscarPorId(Long id) throws Exception;
    List<HobbieUsuarioDTO> listar(int limite) throws Exception;
}