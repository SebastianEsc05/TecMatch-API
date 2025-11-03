package com.example.PotroNet.dao.interfaces;

import com.example.PotroNet.domain.Usuario;

import java.util.List;

public interface IUsuarioDAO {
    void crear(Usuario usuario);
    Usuario buscarPorId(Long id);
    List<Usuario> listar(int limite);
    Usuario actualizar(Usuario usuario);
    void eliminar(Long id);
    Usuario buscarPorCorreo(String correo);

    List<Usuario> listarPorLikes(int offset);
    List<Usuario> listarPorDislikes(int offset);
    List<Usuario> listarPorHobbies(String descripcion,int offset);
    List<Usuario> listarPorIntereses(String descripcion,int offset);
    List<Usuario> listarPorLikes(int id, int offset);
    List<Usuario> listarMatches(int id, int offset);
    List<Usuario> listarPorHobbieEInteres(String descripcionHobbie,String descripcionInteres,int offset);
}
