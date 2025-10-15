package com.example.TecMatch.controllers;

import com.example.TecMatch.DTOs.UsuarioDTO;
import com.example.TecMatch.models.Hobbie;
import com.example.TecMatch.models.Interes;
import com.example.TecMatch.models.InteresUsuario;
import com.example.TecMatch.repositories.UsuarioRepository;
import com.example.TecMatch.models.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {


    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public Usuario createEstudiante(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateEstudiante(@PathVariable Long id, @RequestBody Usuario nuevoUsuario){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el usuario con este id"));
        usuario.setNombre(nuevoUsuario.getNombre());
        usuario.setCarrera(nuevoUsuario.getCarrera());
        usuario.setCorreo(nuevoUsuario.getCorreo());
        usuario.setDescripcion(nuevoUsuario.getDescripcion());
        usuario.setContrasenia(nuevoUsuario.getContrasenia());
        usuario.setSexo(nuevoUsuario.getSexo());
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/me")
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioDTO> getMyProfile(Authentication authentication){
        String emailUsuario = authentication.getName();
        Usuario currentUser = usuarioRepository.findByCorreo(emailUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(currentUser.getId());
        usuarioDTO.setNombre(currentUser.getNombre());
        usuarioDTO.setCarrera(currentUser.getCarrera());
        usuarioDTO.setCorreo(currentUser.getCorreo());
        usuarioDTO.setDescripcion(currentUser.getDescripcion());
        usuarioDTO.setSexo(currentUser.getSexo());

        usuarioDTO.setHobbies(currentUser.getHobbieUsuarios().stream()
                .map(hobbieUsuario -> hobbieUsuario.getHobbie().getDescripcion())
                .collect(Collectors.toSet()));

        usuarioDTO.setIntereses(currentUser.getInteresUsuarios().stream()
                .map(interesUsuario -> interesUsuario.getInteres().getDescripcion())
                .collect(Collectors.toSet()));

        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping("/{id}")
    public Usuario getByIdEstudiante(@PathVariable Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el estudiante con este id"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByIdEstudiante(@PathVariable Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el usuario con este id"));
        usuarioRepository.delete(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Usuario> getAllEstudiantes(){
        return usuarioRepository.findAll();
    }
}
