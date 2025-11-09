package com.example.PotroNet.dao.springRepositories.impl;
import com.example.PotroNet.domain.*;
import com.example.PotroNet.dto.springDto.SolicitarCambiarContraseniaDTO;
import com.example.PotroNet.dto.UsuarioDTO;
import com.example.PotroNet.mapper.UsuarioMapper;
import com.example.PotroNet.dao.springRepositories.UsuarioRepository;
import com.example.PotroNet.service.springService.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;
    private final StorageService storageService;

    public UsuarioController(UsuarioRepository usuarioRepository, StorageService storageService) {
        this.usuarioRepository = usuarioRepository;
        this.storageService = storageService;
    }

    @PostMapping
    public Usuario createEstudiante(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Boolean> updatePerfilUsuario(Authentication authentication, @RequestBody UsuarioDTO usuarioDTO) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }
        Usuario usuario = (Usuario) authentication.getPrincipal();
        usuario.setCarrera(usuarioDTO.getCarrera());
        usuario.setDescripcion(usuarioDTO.getDescripcion());
        Set<HobbieUsuario> nuevosHobbies = usuarioDTO.getHobbies().stream()
                .map(hobbieString -> {
                    Hobbie hobbie = new Hobbie();
                    hobbie.setDescripcion(hobbieString);
                    return new HobbieUsuario(usuario, hobbie);
                })
                .collect(Collectors.toSet());
        usuario.getHobbieUsuarios().clear();
        usuario.getHobbieUsuarios().addAll(nuevosHobbies);
        Set<InteresUsuario> nuevosIntereses = usuarioDTO.getIntereses().stream()
                .map(interesString -> {
                    Interes interes = new Interes();
                    interes.setDescripcion(interesString);
                    return new InteresUsuario(usuario, interes);
                })
                .collect(Collectors.toSet());
        usuario.getInteresUsuarios().clear();
        usuario.getInteresUsuarios().addAll(nuevosIntereses);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(true);
    }



    @GetMapping("/me")
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioDTO> getMiPerfil(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Usuario usuarioAuth = (Usuario) authentication.getPrincipal();
        Usuario usuario = usuarioRepository.findFullProfileByCorreo(usuarioAuth.getCorreo())
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario"));
        UsuarioDTO usuarioDTO = UsuarioMapper.mapToDTO(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }


    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioDTO> getByIdUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        UsuarioDTO usuarioDTO = UsuarioMapper.mapToDTO(usuario);
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
                .map(UsuarioMapper::mapToDTO)
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

    @PostMapping("/{id}/foto-perfil")
    public ResponseEntity<?> subirFotoPerfil(@PathVariable("id") Long userId, @RequestParam("file") MultipartFile archivo) {
        String urlFoto = storageService.guardarArchivoEnCloudinary(archivo);
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario con id: " + userId + " no ha sido encontrado"));
        usuario.setRutaFotoPerfil(urlFoto);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(urlFoto);
    }

}
