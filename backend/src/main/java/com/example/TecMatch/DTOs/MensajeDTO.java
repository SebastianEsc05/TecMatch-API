package com.example.TecMatch.DTOs;

public class MensajeDTO {
    private Long chatId;
    private Long emisorId;
    private String texto;

    public MensajeDTO() {
    }

    public MensajeDTO(Long chatId, Long emisorId, String texto) {
        this.chatId = chatId;
        this.emisorId = emisorId;
        this.texto = texto;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(Long emisorId) {
        this.emisorId = emisorId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
