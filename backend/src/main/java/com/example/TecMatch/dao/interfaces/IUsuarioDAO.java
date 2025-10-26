package com.example.TecMatch.dao.interfaces;

import com.example.TecMatch.domain.Usuario;

import java.util.List;

public interface IUsuarioDAO {
    boolean crear(Usuario usuario);
    Usuario buscarPorId(Long id);
    List<Usuario> listar(int limite);
    boolean actualizar(Usuario usuario);
    boolean eliminar(Long id);
    Usuario buscarPorCorreo(String correo);

}
