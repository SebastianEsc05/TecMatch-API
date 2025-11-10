package potronet.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potronet.repositories.*;

import java.util.Map;

@RestController
@RequestMapping("/api/app")
public class AppController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private HobbieRepository hobbieRepository;
    @Autowired
    private InteresRepository interesRepository;

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    @GetMapping("/registered-users")
    public ResponseEntity<?> registeredUsers() {
        return ResponseEntity.ok(usuarioRepository.contarUsuarios());
    }

    @GetMapping("/registered-matches")
    public ResponseEntity<?> registeredMatches() {
        return ResponseEntity.ok(matchRepository.contarMatches());
    }

    @GetMapping("/registered-likes")
    public ResponseEntity<?> registeredLikes() {
        return ResponseEntity.ok(likeRepository.contarLikes());
    }

    @GetMapping("/registered-hobbies-intereses")
    public ResponseEntity<?> registeredHobbiesIntereses() {
        return ResponseEntity.ok(hobbieRepository.contarHobbies() + interesRepository.contarIntereses());
    }
}
