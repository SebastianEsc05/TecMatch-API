package com.example.TecMatch.dto;

public class ChatUsuarioDTO {
    private Long id;
    private Long chatId;
    private Long usuarioId;

    public ChatUsuarioDTO() {
    }

    public ChatUsuarioDTO(Long id, Long chatId, Long usuarioId) {
        this.id = id;
        this.chatId = chatId;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
