package com.example.PotroNet.dao.springRepositories.impl;
import com.example.PotroNet.dao.springRepositories.*;
import com.example.PotroNet.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/app")
public class AppController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private HobbieService hobbieService;
    @Autowired
    private InteresService interesService;

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    @GetMapping("/registered-users")
    public ResponseEntity<?> registeredUsers() {
        return ResponseEntity.ok(usuarioService.contarUsuarios());
    }

    @GetMapping("/registered-matches")
    public ResponseEntity<?> registeredMatches() {
        return ResponseEntity.ok(matchService.contarMatches());
    }

    @GetMapping("/registered-likes")
    public ResponseEntity<?> registeredLikes() {
        return ResponseEntity.ok(likeService.contarLikes());
    }

    @GetMapping("/registered-hobbies-intereses")
    public ResponseEntity<?> registeredHobbiesIntereses() {
        return ResponseEntity.ok(interesService.contarIntereses());
    }
}
