package com.example.TecMatch.services;


import com.example.TecMatch.DTOs.AuthResponse;
import com.example.TecMatch.DTOs.LoginRequest;
import com.example.TecMatch.DTOs.RegisterRequest;
import com.example.TecMatch.repositories.UsuarioRepository;
import com.example.TecMatch.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse register (RegisterRequest request){
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setCarrera(request.getCarrera());
        usuario.setSexo(request.getSexo());
        usuario.setDescripcion(request.getDescripcion());

        usuario.setContrasenia(passwordEncoder.encode(request.getContrasenia()));

        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getContrasenia()
                )
        );

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado tras la autenticación"));

        String token = jwtService.generateToken(usuario);

        return new AuthResponse(token);
    }
}
