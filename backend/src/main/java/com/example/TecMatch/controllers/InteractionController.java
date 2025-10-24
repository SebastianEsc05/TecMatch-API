package com.example.TecMatch.controllers;

import com.example.TecMatch.DTOs.SolicitarLikeDTO;
import com.example.TecMatch.models.Dislike;
import com.example.TecMatch.repositories.LikeRepository;
import com.example.TecMatch.repositories.MatchRepository;
import com.example.TecMatch.repositories.UsuarioRepository;
import com.example.TecMatch.models.Like;
import com.example.TecMatch.models.Match;
import com.example.TecMatch.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/interacciones")
public class InteractionController {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/likes")
    public ResponseEntity<?> darLike(@AuthenticationPrincipal Usuario usuarioQueDaLike, @RequestBody SolicitarLikeDTO solicitarLikeDTO) {

        Long usuarioQueDaLikeId = usuarioQueDaLike.getId();
        Long usuarioAueRecibeLikeId = solicitarLikeDTO.getUsuarioQueRecibeLikeId();

        if (usuarioQueDaLikeId.equals(usuarioAueRecibeLikeId)) {
            return ResponseEntity.badRequest().body("No te puedes dar auto like");
        }

        Usuario usuarioReceptorDeLike = usuarioRepository.findById(usuarioAueRecibeLikeId).orElseThrow(() -> new RuntimeException(("usuario que da like no encontrado")));

        Like nuevoLike = new Like();
        nuevoLike.setLiker(usuarioQueDaLike);
        nuevoLike.setLiked(usuarioReceptorDeLike);
        nuevoLike.setFecha_hora(LocalDateTime.now());
        likeRepository.save(nuevoLike);

        boolean hayLikeReciproco = likeRepository.existsByLikerIdAndLikedId(usuarioAueRecibeLikeId, usuarioQueDaLikeId);

        Map<String, Object> respuesta = new HashMap<>();

        if (hayLikeReciproco) {
            Match nuevoMatch = new Match(usuarioQueDaLike, usuarioReceptorDeLike);
            matchRepository.save(nuevoMatch);

            respuesta.put("hayMatch", true);
            respuesta.put("mensaje", "Hay match");
        } else {
            respuesta.put("hayMatch", false);
            respuesta.put("message", "Like guardado correctamente.");
        }
        return ResponseEntity.ok(respuesta);
    }

}
