package com.example.TecMatch.controllers;

import com.example.TecMatch.DTOs.SolicitarDislike;
import com.example.TecMatch.DTOs.SolicitarLike;
import com.example.TecMatch.models.Dislike;
import com.example.TecMatch.repositories.DislikeRepository;
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
    private DislikeRepository dislikeRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/likes")
    public ResponseEntity<?> darLike(@AuthenticationPrincipal Usuario usuarioQueDaLike, @RequestBody SolicitarLike solicitarLike) {

        Long usuarioQueDaLikeId = usuarioQueDaLike.getId();
        Long usuarioAueRecibeLikeId = solicitarLike.getUsuarioQueRecibeLikeId();

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

    @PostMapping("/dislikes")
    public ResponseEntity<?> darDislike(@AuthenticationPrincipal Usuario usuarioQueDaDislike, @RequestBody SolicitarDislike solicitarDislike){

        Long usuarioQueDaDislikeId = usuarioQueDaDislike.getId();
        Long usuarioQueRecibeDislikeId = solicitarDislike.getUsuarioQueRecibeDislikeId();

        if (usuarioQueDaDislikeId.equals(usuarioQueRecibeDislikeId)) {
            return ResponseEntity.badRequest().body("No te puedes dar auto like");
        }

        Usuario usuarioReceptorDeLike = usuarioRepository.findById(usuarioQueRecibeDislikeId).orElseThrow(() -> new RuntimeException(("usuario que da like no encontrado")));

        Dislike nuevoDislike = new Dislike();
        nuevoDislike.setDisliker(usuarioQueDaDislike);
        nuevoDislike.setDisliked(usuarioReceptorDeLike);
        nuevoDislike.setFecha_hora(LocalDateTime.now());
        dislikeRepository.save(nuevoDislike);

        matchRepository.findMatchByUsuarios(usuarioQueDaDislikeId, usuarioQueRecibeDislikeId).ifPresent(match -> matchRepository.delete(match));

        likeRepository.deleteByLikerIdAndLikedId(usuarioQueDaDislikeId, usuarioQueRecibeDislikeId);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Dislike registrado de forma correcta, si habia match anteriormente, se ha elliminado");
        return ResponseEntity.ok(respuesta);
    }
}
