package com.example.PotroNet.service.interfaces;
import com.example.PotroNet.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws Exception;
    UsuarioDTO buscarUsuarioPorId(Long id);
    List<UsuarioDTO> listarUsuarios(int limite);
    UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) throws Exception;
    void eliminarUsuario(Long id) throws Exception;
    UsuarioDTO buscarUsuarioPorCorreo(String correo);
    int contarUsuarios();

}
