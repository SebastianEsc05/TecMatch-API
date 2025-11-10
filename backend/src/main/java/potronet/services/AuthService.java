package potronet.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potronet.dto.springDto.*;
import potronet.entities.*;
import potronet.repositories.*;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private InteresRepository interesRepository;
    @Autowired
    private HobbieRepository hobbieRepository;
    @Autowired
    private HobbieUsuarioRepository hobbieUsuarioRepository;
    @Autowired
    private InteresUsuarioRepository interesUsuarioRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponseDTO register (RegisterRequestDTO request){
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setCorreo(request.getCorreo());
        nuevoUsuario.setCarrera(request.getCarrera());
        nuevoUsuario.setDescripcion(request.getDescripcion());
        nuevoUsuario.setSexo(request.getSexo());
        nuevoUsuario.setTelefono(request.getTelefono());
        nuevoUsuario.setFechaNacimiento(request.getFecha_nacimiento());
        nuevoUsuario.setContrasenia(passwordEncoder.encode(request.getContrasenia()));
        nuevoUsuario = usuarioRepository.save(nuevoUsuario);
        if (request.getHobbies() != null) {
            for (String nombreHobbie : request.getHobbies()) {
                Optional<Hobbie> hobbieOptional = hobbieRepository.findByDescripcionIgnoreCase(nombreHobbie);
                if (!hobbieOptional.isPresent()) {
                    throw new RuntimeException("Hobbie '" + nombreHobbie + "' no encontrado en el catálogo.");
                }
                Hobbie hobbie = hobbieOptional.get();
                HobbieUsuario relacion = new HobbieUsuario(nuevoUsuario, hobbie);
                hobbieUsuarioRepository.save(relacion);
            }
        }
        if (request.getIntereses() != null) {
            for (String nombreInteres : request.getIntereses()) {
                Optional<Interes> interesOptional = interesRepository.findByDescripcionIgnoreCase(nombreInteres);

                if (!interesOptional.isPresent()) {
                    throw new RuntimeException("Interes '" + nombreInteres + "' no encontrado en el catálogo.");
                }

                Interes interes = interesOptional.get();
                InteresUsuario relacion = new InteresUsuario(nuevoUsuario, interes);
                interesUsuarioRepository.save(relacion);
            }
        }
        String token = jwtService.generateToken(nuevoUsuario);
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getCorreo(),
                            request.getContrasenia()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Correo o contraseña incorrectos");
        }

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("No existe un usuario registrado con ese correo"));
        String token = jwtService.generateToken(usuario);
        return new AuthResponseDTO(token);
    }

    public ExistResponseDTO exist(ExistRequestDTO request) {
        return new ExistResponseDTO(usuarioRepository.findByCorreo(request.getCorreo()).isPresent());
    }
}
