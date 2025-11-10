package potronet.controllers;
import potronet.repositories.MatchRepository;
import potronet.entities.Match;
import potronet.dto.UsuarioDTO;
import potronet.mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping("/{userId}")
    public List<UsuarioDTO> obtenerMatches(@PathVariable Long userId) {

        List<Match> matches = matchRepository.findAllMatchesByUserId(userId);

        return matches.stream()
                .map(m -> m.getUsuario1().getId().equals(userId)
                        ? m.getUsuario2()
                        : m.getUsuario1())
                .map(UsuarioMapper::mapToDTO)
                .toList();
    }
}

