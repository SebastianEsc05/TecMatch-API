package com.example.TecMatch.ChatHandler;

import com.example.TecMatch.dto.MensajeDTO;
import com.example.TecMatch.service.springService.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class ChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    private final CopyOnWriteArraySet<WebSocketSession> sesiones = new CopyOnWriteArraySet<>();

    @Autowired
    public ChatHandler(ObjectMapper objectMapper, ChatService chatService) {
        this.objectMapper = objectMapper;
        this.chatService = chatService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession sesion) {
        sesiones.add(sesion);
        System.out.println("Cliente conectado: " + sesion.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession sesion, TextMessage mensaje) throws Exception {
        try {
            MensajeDTO mensajeDTO = objectMapper.readValue(mensaje.getPayload(), MensajeDTO.class);
            System.out.println("Mensaje recibido: " + mensajeDTO.getTexto());

            MensajeDTO mensajeGuardado = chatService.guardarMensaje(mensajeDTO);

            String jsonResponse = objectMapper.writeValueAsString(mensajeGuardado);
            for (WebSocketSession s : sesiones) {
                if (s.isOpen()) {
                    s.sendMessage(new TextMessage(jsonResponse));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            sesion.sendMessage(new TextMessage("{\"error\": \"Error al procesar el mensaje\"}"));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession sesion, CloseStatus status) {
        sesiones.remove(sesion);
        System.out.println("Cliente desconectado: " + sesion.getId());
    }
}
