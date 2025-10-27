package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.domain.Mensaje;
import com.example.TecMatch.dto.MensajeDTO;

import java.util.List;

public interface IMensajeService {
    MensajeDTO crearMensaje(MensajeDTO mensajeDTO) throws Exception;
    List<MensajeDTO> listarMensajesPorChat(Long chatId, int limite);
    MensajeDTO marcarComoVisto(Long mensajeId) throws Exception;
    void eliminarMensaje(Long mensajeId) throws Exception;
}
