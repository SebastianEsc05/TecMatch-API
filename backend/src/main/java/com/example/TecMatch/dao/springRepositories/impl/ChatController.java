package com.example.TecMatch.dao.springRepositories.impl;

import com.example.TecMatch.domain.Mensaje;
import com.example.TecMatch.dto.MensajeDTO;
import com.example.TecMatch.domain.Chat;
import com.example.TecMatch.domain.ChatUsuario;
import com.example.TecMatch.domain.Usuario;
import com.example.TecMatch.dao.springRepositories.ChatRepository;
import com.example.TecMatch.dao.springRepositories.ChatUsuarioRepository;
import com.example.TecMatch.dao.springRepositories.MensajeRepository;
import com.example.TecMatch.dao.springRepositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private MensajeRepository mensajeRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ChatRepository chatRepository;
    @Autowired private ChatUsuarioRepository chatUsuarioRepository;

    @MessageMapping("/chat")
    @Transactional
    public void processMessage(@Payload MensajeDTO mensajeDTO){

        Usuario emisor = usuarioRepository.findById(mensajeDTO.getEmisorId()).orElse(null);
        Chat chat = chatRepository.findById(mensajeDTO.getChatId()).orElse(null);

        if(emisor == null){
            return;
        }
        if(chat == null){
            return;
        }

        Mensaje nuevoMensaje = new Mensaje();
        nuevoMensaje.setChat(chat);
        nuevoMensaje.setEmisor(emisor);

        String textoRecibido = mensajeDTO.getTexto();
        if (textoRecibido == null) {
            return;
        }
        nuevoMensaje.setTexto(textoRecibido);
        nuevoMensaje.setFecha_hora(LocalDateTime.now());
        nuevoMensaje.setVisto(false);

        try {
            Mensaje mensajeGuardado = mensajeRepository.save(nuevoMensaje);

            Usuario receptor = chatUsuarioRepository.findByChatAndUsuarioNot(chat,emisor)
                    .map(ChatUsuario::getUsuario)
                    .orElse(null);

            if(receptor != null){
                messagingTemplate.convertAndSendToUser(
                        receptor.getCorreo(),
                        "/queue/messages",
                        mensajeGuardado
                );
            }else{
                System.out.println("No se encontro un receptor en este chat.");
            }
        } catch (Exception e) {
            System.out.println("La transacción falló,  causa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}