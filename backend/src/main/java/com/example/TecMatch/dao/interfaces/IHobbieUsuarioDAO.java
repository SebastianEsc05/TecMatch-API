package com.example.TecMatch.dao.interfaces;

import com.example.TecMatch.domain.HobbieUsuario;

import java.util.List;

public interface IHobbieUsuarioDAO {
    void crear(HobbieUsuario hobbieUsuario);
    HobbieUsuario buscarPorId(Long id);
    List<HobbieUsuario> listar(int limite);
    void eliminar(Long id);
    List<HobbieUsuario> buscarPorUsuarioId(Long usuarioId);
    List<HobbieUsuario> buscarPorHobbieId(Long hobbieId);
    HobbieUsuario buscarPorUsuarioEHobbie(Long usuarioId, Long hobbieId);
}
