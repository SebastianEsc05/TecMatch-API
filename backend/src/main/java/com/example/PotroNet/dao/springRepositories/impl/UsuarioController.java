package com.example.PotroNet.dao.springRepositories.impl;

import com.example.PotroNet.dto.springDto.SolicitarCambiarContraseniaDTO;
import com.example.PotroNet.dto.UsuarioDTO;
import com.example.PotroNet.dao.springRepositories.UsuarioRepository;
import com.example.PotroNet.domain.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public Usuario createEstudiante(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updatePerfilUsuario(Authentication authentication, @RequestBody UsuarioDTO usuarioDTO){
      String emailUsuario = authentication.getName();
      Usuario usuario = usuarioRepository.findByCorreo(emailUsuario).orElseThrow(() -> new RuntimeException("No se encontro el usuario"));

      usuario.setNombre(usuarioDTO.getNombre());
      usuario.setCarrera(usuarioDTO.getCarrera());
      usuario.setDescripcion(usuarioDTO.getDescripcion());
      usuario.setSexo(usuarioDTO.getSexo());

      Usuario usuarioActualizado = usuarioRepository.save(usuario);
      return ResponseEntity.ok(convertToDTO(usuarioActualizado));
    }

    @GetMapping("/me")
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioDTO> getMiPerfil(Authentication authentication){
        String emailUsuario = authentication.getName();
        Usuario usuario = usuarioRepository.findByCorreo(emailUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setCarrera(usuario.getCarrera());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setDescripcion(usuario.getDescripcion());
        usuarioDTO.setSexo(usuario.getSexo());

        usuarioDTO.setHobbies(usuario.getHobbieUsuarios().stream()
                .map(hobbieUsuario -> hobbieUsuario.getHobbie().getDescripcion())
                .collect(Collectors.toSet()));

        usuarioDTO.setIntereses(usuario.getInteresUsuarios().stream()
                .map(interesUsuario -> interesUsuario.getInteres().getDescripcion())
                .collect(Collectors.toSet()));

        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioDTO> getByIdUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        UsuarioDTO usuarioDTO = convertToDTO(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByIdEstudiante(@PathVariable Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el usuario con este id"));
        usuarioRepository.delete(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Transactional(readOnly = true)
    public List<UsuarioDTO> getAllUsuarios(){
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/me/cambiar-contrasenia")
    @Transactional
    public ResponseEntity<?> cambiarContrasenia(@AuthenticationPrincipal Usuario usuario, @RequestBody SolicitarCambiarContraseniaDTO solicitarCambiarContraseniaDTO){
        if(!passwordEncoder.matches(solicitarCambiarContraseniaDTO.getContraseniaActual(), usuario.getContrasenia())){
            return ResponseEntity.badRequest().body(Map.of("error","La contrasenia actual es incorrecta"));
        }

        if(solicitarCambiarContraseniaDTO.getContraseniaNueva() == null || solicitarCambiarContraseniaDTO.getContraseniaNueva().isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("error", "La nueva contrasenia no puede estar vacia"));
        }
        usuario.setContrasenia(passwordEncoder.encode(solicitarCambiarContraseniaDTO.getContraseniaNueva()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok((Map.of("exito","Contrasenia actualizada de forma exitosa")));
    }



    private UsuarioDTO convertToDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setCarrera(usuario.getCarrera());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setDescripcion(usuario.getDescripcion());
        usuarioDTO.setSexo(usuario.getSexo());
        usuarioDTO.setHobbies(usuario.getHobbieUsuarios().stream()
                .map(h -> h.getHobbie().getDescripcion())
                .collect(Collectors.toSet()));
        usuarioDTO.setIntereses(usuario.getInteresUsuarios().stream()
                .map(i -> i.getInteres().getDescripcion())
                .collect(Collectors.toSet()));
        return usuarioDTO;
    }
}
