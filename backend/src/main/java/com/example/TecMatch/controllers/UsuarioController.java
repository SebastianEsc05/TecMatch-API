package com.example.TecMatch.controllers;

import com.example.TecMatch.repositories.UsuarioRepository;
import com.example.TecMatch.models.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public ResponseEntity<Usuario> getMyProfile(Authentication authentication){
        Usuario currentUser = (Usuario) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
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
