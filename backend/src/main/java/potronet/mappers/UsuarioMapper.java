package potronet.mappers;

import potronet.entities.Usuario;
import potronet.dto.UsuarioDTO;

import java.util.stream.Collectors;

public class UsuarioMapper {
    public static Usuario mapToEntity(UsuarioDTO usuarioDTO){
        if(usuarioDTO == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCarrera(usuarioDTO.getCarrera());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setDescripcion(usuarioDTO.getDescripcion());
        usuario.setSexo(usuarioDTO.getSexo());
        usuario.setRutaFotoPerfil(usuarioDTO.getRutaFotoPerfl());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        return usuario;
    }
    public static UsuarioDTO mapToDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCarrera(usuario.getCarrera());
        dto.setCorreo(usuario.getCorreo());
        dto.setDescripcion(usuario.getDescripcion());
        dto.setSexo(usuario.getSexo());
        dto.setFechaNacimiento(usuario.getFechaNacimiento());
        dto.setTelefono(usuario.getTelefono());
        dto.setRutaFotoPerfl(usuario.getRutaFotoPerfil());
        if (usuario.getInteresUsuarios() != null) {
            dto.setIntereses(usuario.getInteresUsuarios().stream()
                    .map(interesUsuario -> interesUsuario.getInteres().getDescripcion())
                    .collect(Collectors.toSet()));
        }
        if (usuario.getHobbieUsuarios() != null) {
            dto.setHobbies(usuario.getHobbieUsuarios().stream()
                    .map(hobbieUsuario -> hobbieUsuario.getHobbie().getDescripcion())
                    .collect(Collectors.toSet()));
        }
        return dto;
    }
}
