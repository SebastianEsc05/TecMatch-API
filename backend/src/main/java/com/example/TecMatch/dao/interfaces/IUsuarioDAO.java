package com.example.TecMatch.dao.interfaces;

import com.example.TecMatch.domain.Usuario;

import java.util.List;

public interface IUsuarioDAO {
    void crear(Usuario usuario);
    Usuario buscarPorId(Long id);
    List<Usuario> listar(int limite);
    Usuario actualizar(Usuario usuario);
    void eliminar(Long id);
    Usuario buscarPorCorreo(String correo);
}
