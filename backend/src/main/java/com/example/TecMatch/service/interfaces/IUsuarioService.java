package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.domain.Usuario;

import java.util.List;

public interface IUsuarioService {
    boolean crear(Usuario usuario);
    Usuario buscarPorId(Long id);
    List<Usuario> listar(int limite);
    boolean actualizar(Usuario usuario);
    boolean eliminar(Long id);
}
