package com.example.PotroNet.dao.springRepositories.impl;
import com.example.PotroNet.dao.springRepositories.ChatRepository;
import com.example.PotroNet.dao.springRepositories.MatchRepository;
import com.example.PotroNet.dao.springRepositories.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/app")
public class AppController {
    private final UsuarioRepository usuarioRepository;
    private final MatchRepository matchRepository;
    private final ChatRepository chatRepository;

    public AppController(ChatRepository chatRepository, UsuarioRepository usuarioRepository, MatchRepository matchRepository) {
        this.chatRepository = chatRepository;
        this.usuarioRepository = usuarioRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
