package com.example.TecMatch.dao.springRepositories.impl;

import com.example.TecMatch.dao.springRepositories.*;
import com.example.TecMatch.dto.SolicitarLikeDTO;
import com.example.TecMatch.domain.enums.Tipo;
import com.example.TecMatch.domain.*;
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
import java.util.Set;

@RestController
@RequestMapping("/api/interacciones")
public class InteractionController {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatUsuarioRepository chatUsuarioRepository;

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

            Chat nuevoChat = new Chat();
            nuevoChat.setTipo(Tipo.PRIVADO);
            nuevoChat.setFecha_creacion(LocalDateTime.now());
            chatRepository.save(nuevoChat);

            ChatUsuario chatUsuarioA = new ChatUsuario();
            chatUsuarioA.setChat(nuevoChat);
            chatUsuarioA.setUsuario(usuarioQueDaLike);
            chatUsuarioRepository.save(chatUsuarioA);

            ChatUsuario chatUsuarioB = new ChatUsuario();
            chatUsuarioB.setChat(nuevoChat);
            chatUsuarioB.setUsuario(usuarioReceptorDeLike);
            chatUsuarioRepository.save(chatUsuarioB);

            nuevoChat.setChatUsuarios(Set.of(chatUsuarioA,chatUsuarioB));
            chatRepository.save(nuevoChat); // !



            respuesta.put("hayMatch", true);
            respuesta.put("chatId:",nuevoChat.getId());
            respuesta.put("mensaje", "Hay match");
        } else {
            respuesta.put("hayMatch", false);
            respuesta.put("message", "Like guardado correctamente.");
        }
        return ResponseEntity.ok(respuesta);
    }

}
