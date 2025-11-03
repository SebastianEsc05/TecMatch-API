package com.example.PotroNet.service.interfaces;

import com.example.PotroNet.dto.MensajeDTO;

import java.util.List;

public interface IMensajeService {
    MensajeDTO crearMensaje(MensajeDTO mensajeDTO) throws Exception;
    List<MensajeDTO> listarMensajesPorChat(Long chatId, int limite);
    MensajeDTO marcarComoVisto(Long mensajeId) throws Exception;
    void eliminarMensaje(Long mensajeId) throws Exception;
}
