package com.example.TecMatch.service.springService;


import com.example.TecMatch.dto.springDto.AuthResponseDTO;
import com.example.TecMatch.dto.springDto.LoginRequestDTO;
import com.example.TecMatch.dto.springDto.RegisterRequestDTO;
import com.example.TecMatch.dao.springRepositories.UsuarioRepository;
import com.example.TecMatch.domain.Usuario;
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

    public AuthResponseDTO register (RegisterRequestDTO request){
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setCarrera(request.getCarrera());
        usuario.setSexo(request.getSexo());
        usuario.setDescripcion(request.getDescripcion());

        usuario.setContrasenia(passwordEncoder.encode(request.getContrasenia()));

        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario);

        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getContrasenia()
                )
        );

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado tras la autenticación"));

        String token = jwtService.generateToken(usuario);

        return new AuthResponseDTO(token);
    }
}
