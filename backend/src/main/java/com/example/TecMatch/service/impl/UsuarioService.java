package com.example.TecMatch.service.impl;

import com.example.TecMatch.dao.impl.UsuarioDAO;
import com.example.TecMatch.dao.interfaces.IUsuarioDAO;
import com.example.TecMatch.domain.Usuario;
import com.example.TecMatch.service.interfaces.IUsuarioService;

import java.time.LocalDate;
import java.util.List;

public class UsuarioService implements IUsuarioService {
    private final IUsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public boolean crear(Usuario usuario) {
        if(usuario == null){
            System.out.println("El usuario no puede ser nulo");
            return false;
        }
        if (campoEsInvalido(usuario.getNombre(), "Nombre")) return false;
        if (campoEsInvalido(usuario.getCarrera(), "Carrera")) return false;
        if (campoEsInvalido(usuario.getCorreo(), "Correo")) return false;
        if (campoEsInvalido(usuario.getDescripcion(), "Descripción")) return false;
        if (campoEsInvalido(usuario.getContrasenia(), "Contraseña")) return false;
        if (campoEsInvalido(usuario.getSexo(), "Sexo")) return false;
        if(usuario.getFechaNacimiento().isBefore(LocalDate.now())){
            System.out.println("Fecha invalida");
            return false;
        }
        Usuario usuarioExistente = usuarioDAO.buscarPorCorreo(usuario.getCorreo());
        if(usuarioExistente != null){
            System.out.println("Usuario ya registrado");
            return false;
        }
        return usuarioDAO.crear(usuario);

    }

    @Override
    public Usuario buscarPorId(Long id) {
        if(id == null || id <= 0){
            System.out.println("id invalido");
            return null;
        }
        return usuarioDAO.buscarPorId(id);
    }

    @Override
    public List<Usuario> listar(int limite) {
        if(limite > 100) limite = 100;
        return usuarioDAO.listar(limite);
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        if(usuario == null || usuario.getId() == null){
            System.out.println("usuario invalido");
            return false;
        }
        return usuarioDAO.actualizar(usuario);
    }

    @Override
    public boolean eliminar(Long id) {
        if(id == null || id <= 0){
            System.out.println("id invalido");
            return false;
        }
        return usuarioDAO.eliminar(id);
    }

    private boolean campoEsInvalido(String valor, String nombreCampo){
        if(valor == null || valor.isBlank()){
            System.out.println(nombreCampo + "es invalido");
            return true;
        }
        return false;
    }
}
