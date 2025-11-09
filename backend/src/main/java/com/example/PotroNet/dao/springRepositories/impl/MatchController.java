package com.example.PotroNet.dao.springRepositories.impl;
import com.example.PotroNet.dao.springRepositories.MatchRepository;
import com.example.PotroNet.domain.Match;
import com.example.PotroNet.dto.UsuarioDTO;
import com.example.PotroNet.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchRepository matchRepository;

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

