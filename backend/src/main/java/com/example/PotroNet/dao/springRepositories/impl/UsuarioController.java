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

import java.util.*;
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
    public ResponseEntity<Boolean> updatePerfilUsuario(
            @PathVariable Long id,
            Authentication authentication,
            @RequestBody UsuarioDTO usuarioDTO) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }
        Usuario usuario = (Usuario) authentication.getPrincipal();
        if (!usuario.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }
        Optional.ofNullable(usuarioDTO.getCarrera())
                .filter(c -> !c.isBlank())
                .ifPresent(usuario::setCarrera);
        Optional.ofNullable(usuarioDTO.getDescripcion())
                .filter(d -> !d.isBlank())
                .ifPresent(usuario::setDescripcion);
        if (usuarioDTO.getHobbies() != null) {
            Set<HobbieUsuario> nuevosHobbies = usuarioDTO.getHobbies().stream()
                    .map(hobbieString -> {
                        Hobbie hobbie = new Hobbie();
                        hobbie.setDescripcion(hobbieString);
                        return new HobbieUsuario(usuario, hobbie);
                    })
                    .collect(Collectors.toSet());
            usuario.getHobbieUsuarios().clear();
            usuario.getHobbieUsuarios().addAll(nuevosHobbies);
        }
        if (usuarioDTO.getIntereses() != null) {
            Set<InteresUsuario> nuevosIntereses = usuarioDTO.getIntereses().stream()
                    .map(interesString -> {
                        Interes interes = new Interes();
                        interes.setDescripcion(interesString);
                        return new InteresUsuario(usuario, interes);
                    })
                    .collect(Collectors.toSet());
            usuario.getInteresUsuarios().clear();
            usuario.getInteresUsuarios().addAll(nuevosIntereses);
        }
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/explorar")
    @Transactional(readOnly = true)
    public ResponseEntity<Map<String, Object>> explorarUsuarios(
            @AuthenticationPrincipal Usuario usuarioAutenticado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String filtro,
            @RequestParam(required = false) String valor) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> paginaUsuarios;

        if (filtro != null && valor != null) {
            switch (filtro.toLowerCase()) {
                case "carrera":
                    paginaUsuarios = usuarioRepository.findByCarreraContainingIgnoreCase(valor, pageable);
                    break;
                case "hobbie":
                    paginaUsuarios = usuarioRepository.findByHobbieDescripcion(valor, pageable);
                    break;
                case "interes":
                    paginaUsuarios = usuarioRepository.findByInteresDescripcion(valor, pageable);
                    break;
                default:
                    paginaUsuarios = usuarioRepository.findAll(pageable);
            }
        } else {
            paginaUsuarios = usuarioRepository.findAll(pageable);
        }

        List<UsuarioDTO> usuariosDTO = paginaUsuarios.getContent().stream()
                .filter(u -> !u.getId().equals(usuarioAutenticado.getId()))
                .map(UsuarioMapper::mapToDTO)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("usuarios", usuariosDTO);
        response.put("paginaActual", paginaUsuarios.getNumber());
        response.put("totalPaginas", paginaUsuarios.getTotalPages());
        response.put("totalElementos", paginaUsuarios.getTotalElements());

        return ResponseEntity.ok(response);
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
    @Transactional
    public ResponseEntity<?> subirFotoPerfil(
            @PathVariable("id") Long userId,
            @RequestParam("file") MultipartFile archivo) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario con id: " + userId + " no ha sido encontrado"));

        String urlFoto = storageService.guardarArchivoEnCloudinary(archivo);
        usuario.setRutaFotoPerfil(urlFoto);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok(Map.of("url", urlFoto));
    }


//    @GetMapping("/filtrar/hobbie")
//    @Transactional(readOnly = true)
//    public ResponseEntity<List<UsuarioDTO>> encontrarUsuariosPorHobbie(Authentication authentication, @RequestParam("hobbie") String hobbie, @RequestParam(defaultValue = "0") int offset) {
//        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
//        Long currentUserId = usuarioAutenticado.getId();
//        List<Usuario> usuariosEncontrados = usuarioRepository.findByHobbies(hobbie, offset);
//        List<UsuarioDTO> sugerenciasDTO = usuariosEncontrados.stream()
//                .filter(u -> !u.getId().equals(currentUserId))
//                .map(UsuarioMapper::mapToDTO)
//                .collect(Collectors.toList());
//        if (sugerenciasDTO.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(sugerenciasDTO);
//    }
//
//    @GetMapping("/filtrar/interes")
//    @Transactional(readOnly = true)
//    public ResponseEntity<List<UsuarioDTO>> encontrarUsuariosPorInteres(Authentication authentication, @RequestParam("interes") String interes, @RequestParam(defaultValue = "0") int offset) {
//        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
//        Long currentUserId = usuarioAutenticado.getId();
//        List<Usuario> usuariosEncontrados = usuarioRepository.findByInterests(interes, offset);
//        List<UsuarioDTO> sugerenciasDTO = usuariosEncontrados.stream()
//                .filter(u -> !u.getId().equals(currentUserId))
//                .map(UsuarioMapper::mapToDTO)
//                .collect(Collectors.toList());
//        if (sugerenciasDTO.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(sugerenciasDTO);
//    }
//

    @GetMapping("/filtrar/carrera/similares")
    @Transactional(readOnly = true)
    public ResponseEntity<List<UsuarioDTO>> encontrarUsuariosPorCarreraSimilares(Authentication authentication, @RequestParam(defaultValue = "0") int offset) {
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        Long idUsuarioActual = usuarioAutenticado.getId();
        String carreraNombre = usuarioAutenticado.getCarrera();
        if (carreraNombre == null || carreraNombre.trim().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<Usuario> usuariosEncontrados = usuarioRepository.findByCarreraNombreAndIdNot(carreraNombre, idUsuarioActual, offset);
        List<UsuarioDTO> sugerenciasDTO = usuariosEncontrados.stream()
                .map(UsuarioMapper::mapToDTO)
                .collect(Collectors.toList());
        if (sugerenciasDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sugerenciasDTO);
    }

    @GetMapping("/filtrar/hobbie/similares")
    @Transactional(readOnly = true)
    public ResponseEntity<List<UsuarioDTO>> encontrarUsuariosPorHobbieSimilares(Authentication authentication, @RequestParam(defaultValue = "0") int offset) {
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        Long idUsuarioActual = usuarioAutenticado.getId();
        List<String> hobbiesUsuario = usuarioAutenticado.getDescripcionHobbie();
        if (hobbiesUsuario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<Usuario> usuariosEncontrados = usuarioRepository.findByHobbiesIn(hobbiesUsuario, offset);
        List<UsuarioDTO> sugerenciasDTO = usuariosEncontrados.stream()
                .filter(u -> !u.getId().equals(idUsuarioActual))
                .distinct()
                .map(UsuarioMapper::mapToDTO)
                .collect(Collectors.toList());
        if (sugerenciasDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sugerenciasDTO);
    }

    @GetMapping("/filtrar/interes/similares")
    @Transactional(readOnly = true)
    public ResponseEntity<List<UsuarioDTO>> encontrarUsuariosPorInteresSimilares(Authentication authentication, @RequestParam(defaultValue = "0") int offset) {
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        Long idUsuarioActual = usuarioAutenticado.getId();
        List<String> interesesUsuario = usuarioAutenticado.getDescripcionInteres();
        if (interesesUsuario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<Usuario> usuariosEncontrados = usuarioRepository.findByInterestsIn(interesesUsuario, offset);
        List<UsuarioDTO> sugerenciasDTO = usuariosEncontrados.stream()
                .filter(u -> !u.getId().equals(idUsuarioActual))
                .distinct()
                .map(UsuarioMapper::mapToDTO)
                .collect(Collectors.toList());
        if (sugerenciasDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sugerenciasDTO);
    }



}
