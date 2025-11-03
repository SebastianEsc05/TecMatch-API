package com.example.PotroNet.controller;

import com.example.PotroNet.dto.MensajeDTO;
import com.example.PotroNet.service.impl.MensajeService;
import com.example.PotroNet.service.interfaces.IMensajeService;

import java.util.Collections;
import java.util.List;

public class MensajeController {
    private final IMensajeService mensajeService = new MensajeService();

    public void crearMensaje(MensajeDTO dto) {
        System.out.println("--- Ejecutando: Enviar Mensaje ---");
        try {
            MensajeDTO nuevoMensaje = mensajeService.crearMensaje(dto);
            System.out.println("ÉXITO: Mensaje enviado con ID: " + nuevoMensaje.getId());
            System.out.println("  Chat: " + nuevoMensaje.getChatId());
            System.out.println("  Emisor: " + nuevoMensaje.getEmisorId());
            System.out.println("  Texto: '" + nuevoMensaje.getTexto() + "'");

        } catch (Exception e) {
            System.err.println("FALLO al enviar mensaje: " + e.getMessage());
        }
    }

    public void listarMensajesPorChat(Long chatId, int limite) {
        System.out.println("\n--- Ejecutando: Listar Mensajes del Chat ID " + chatId + " (límite " + limite + ") ---");
        try {
            List<MensajeDTO> mensajes = mensajeService.listarMensajesPorChat(chatId, limite);
            System.out.println("ÉXITO: Se encontraron " + mensajes.size() + " mensajes.");
            Collections.reverse(mensajes);
            for (MensajeDTO msg : mensajes) {
                String visto = msg.isVisto() ? "(Visto)" : "(No visto)";
                System.out.println("  [Usuario " + msg.getEmisorId() + "]: " + msg.getTexto() + " - " + visto);
            }

        } catch (Exception e) {
            System.err.println("FALLO al listar mensajes: " + e.getMessage());
        }
    }

    public void marcarComoVisto(Long mensajeId) {
        System.out.println("\n--- Ejecutando: Marcar Mensaje ID " + mensajeId + " como visto ---");
        try {
            MensajeDTO msg = mensajeService.marcarComoVisto(mensajeId);
            System.out.println("ÉXITO: Mensaje " + msg.getId() + " marcado como visto.");
        } catch (Exception e) {
            System.err.println("FALLO al marcar como visto: " + e.getMessage());
        }
    }

    public void eliminarMensaje(Long mensajeId) {
        System.out.println("\n--- Ejecutando: Eliminar Mensaje ID " + mensajeId + " ---");
        try {
            mensajeService.eliminarMensaje(mensajeId);
            System.out.println("ÉXITO: Mensaje con ID " + mensajeId + " eliminado.");
        } catch (Exception e) {
            System.err.println("FALLO al eliminar mensaje: " + e.getMessage());
        }
    }
}
