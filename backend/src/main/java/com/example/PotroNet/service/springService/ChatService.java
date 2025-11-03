package com.example.PotroNet.service.springService;

import com.example.PotroNet.dao.springRepositories.ChatRepository;
import com.example.PotroNet.dao.springRepositories.MensajeRepository;
import com.example.PotroNet.dao.springRepositories.UsuarioRepository;
import com.example.PotroNet.domain.Chat;
import com.example.PotroNet.domain.Mensaje;
import com.example.PotroNet.domain.Usuario;
import com.example.PotroNet.dto.MensajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {

    @Autowired private MensajeRepository mensajeRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ChatRepository chatRepository;

    public MensajeDTO guardarMensaje(MensajeDTO mensajeDTO) {
        Usuario emisor = usuarioRepository.findById(mensajeDTO.getEmisorId()).orElse(null);
        Chat chat = chatRepository.findById(mensajeDTO.getChatId()).orElse(null);

        if (emisor == null || chat == null) {
            throw new IllegalArgumentException("Emisor o chat no encontrados");
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setEmisor(emisor);
        mensaje.setChat(chat);
        mensaje.setTexto(mensajeDTO.getTexto());
        mensaje.setFecha_hora(LocalDateTime.now());
        mensaje.setVisto(false);

        Mensaje guardado = mensajeRepository.save(mensaje);

        MensajeDTO respuesta = new MensajeDTO();
        respuesta.setId(guardado.getId());
        respuesta.setTexto(guardado.getTexto());
        respuesta.setChatId(chat.getId());
        respuesta.setEmisorId(emisor.getId());
        respuesta.setFechaHora(guardado.getFecha_hora());
        respuesta.setVisto(guardado.isVisto());

        return respuesta;
    }
}
