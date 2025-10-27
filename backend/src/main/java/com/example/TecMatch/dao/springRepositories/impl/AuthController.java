package com.example.TecMatch.dao.springRepositories.impl;

import com.example.TecMatch.dto.springDto.AuthResponseDTO;
import com.example.TecMatch.dto.springDto.LoginRequestDTO;
import com.example.TecMatch.dto.springDto.RegisterRequestDTO;
import com.example.TecMatch.dto.springDto.SolicitarCambiarCorreoDTO;
import com.example.TecMatch.domain.TokenVerificacionCorreo;
import com.example.TecMatch.domain.Usuario;
import com.example.TecMatch.dao.springRepositories.TokenVerificacionCorreoRepository;
import com.example.TecMatch.dao.springRepositories.UsuarioRepository;
import com.example.TecMatch.service.springService.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenVerificacionCorreoRepository tokenVerificacionCorreoRepository;
    private final JavaMailSender javaMailSender;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenVerificacionCorreoRepository tokenVerificacionCorreoRepository, JavaMailSender javaMailSender) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenVerificacionCorreoRepository = tokenVerificacionCorreoRepository;
        this.javaMailSender = javaMailSender;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/me/solicitar-cambio-correo")
    @Transactional
    public ResponseEntity<?> solicitarCambioCorreo(@AuthenticationPrincipal Usuario usuario, @RequestBody SolicitarCambiarCorreoDTO solicitarCambiarCorreoDTO) {
        {
            if (!passwordEncoder.matches(solicitarCambiarCorreoDTO.getContraseniaActual(), usuario.getContrasenia())) {
                return ResponseEntity.badRequest().body(Map.of("error", "La contrasenia actual es incorrecta"));
            }
            if (usuarioRepository.findByCorreo(solicitarCambiarCorreoDTO.getNuevoCorreo()).isPresent()) {
                return ResponseEntity.badRequest().body(Map.of("error", "El nuevo correo ingresasdo ya esta en uso"));
            }

            String stringToken = UUID.randomUUID().toString();
            TokenVerificacionCorreo tokenVerificacionCorreo = new TokenVerificacionCorreo(stringToken, usuario, solicitarCambiarCorreoDTO.getNuevoCorreo(), LocalDateTime.now().plusMinutes(15));
            tokenVerificacionCorreoRepository.save(tokenVerificacionCorreo);

            String confirmationUrl = "http://localhost:8080/api/auth/verify-email-change?token=" + stringToken;
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(solicitarCambiarCorreoDTO.getNuevoCorreo());
            email.setSubject("Confirma tu cambio de correo electrónico");
            email.setText("Para confirmar tu cambio de correo, por favor haz clic en el siguiente enlace: " + confirmationUrl);
            javaMailSender.send(email);

            return ResponseEntity.ok(Map.of("mensaje", "Se envio correctamente el correo de verificacion"));

        }

    }

    @GetMapping("/verficar-cambio-correo")
    @Transactional
    public ResponseEntity<?> verificarCambioCorreo(@RequestParam("token") String token){
        TokenVerificacionCorreo tokenVerificacionCorreo = tokenVerificacionCorreoRepository.findByToken(token).orElse(null);
        if(tokenVerificacionCorreo == null){
            return ResponseEntity.badRequest().body(Map.of("error","El token de verificacion no es valido"));
        }
        if(tokenVerificacionCorreo.getFechaExpiracion().isBefore(LocalDateTime.now())){
            tokenVerificacionCorreoRepository.delete(tokenVerificacionCorreo);
            return ResponseEntity.badRequest().body(Map.of("error","El token ya ha expirado"));
        }

        Usuario usuario = tokenVerificacionCorreo.getUsuario();
        usuario.setCorreo(tokenVerificacionCorreo.getNuevoCorreo());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(Map.of("mensaje","El correo ha sido actualizado con exito"));
    }

}
