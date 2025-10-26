package com.example.TecMatch.controllers;

import com.example.TecMatch.DTOs.MensajeDTO;
import com.example.TecMatch.models.Chat;
import com.example.TecMatch.models.ChatUsuario;
import com.example.TecMatch.models.Mensaje;
import com.example.TecMatch.models.Usuario;
import com.example.TecMatch.repositories.ChatRepository;
import com.example.TecMatch.repositories.ChatUsuarioRepository;
import com.example.TecMatch.repositories.MensajeRepository;
import com.example.TecMatch.repositories.UsuarioRepository;
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