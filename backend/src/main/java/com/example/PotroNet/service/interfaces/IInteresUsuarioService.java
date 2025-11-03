package com.example.PotroNet.service.interfaces;

import com.example.PotroNet.dto.InteresUsuarioDTO;

import java.util.List;

public interface IInteresUsuarioService {
    InteresUsuarioDTO agregarInteresAUsuario(InteresUsuarioDTO dto) throws Exception;
    void eliminarInteresDeUsuario(Long idRelacion) throws Exception;
    List<InteresUsuarioDTO> listarInteresesDeUsuario(Long usuarioId) throws Exception;
    List<InteresUsuarioDTO> listarUsuariosPorInteres(Long interesId) throws Exception;
    InteresUsuarioDTO buscarPorId(Long id) throws Exception;
    List<InteresUsuarioDTO> listar(int limite) throws Exception;
}
