package potronet.dto;

import java.time.LocalDateTime;

public class MensajeDTO {
    private Long id;
    private Long chatId;
    private Long emisorId;
    private String texto;
    private LocalDateTime fechaHora;
    private boolean visto;


    public MensajeDTO() {
    }

    public MensajeDTO(Long chatId, Long emisorId, String texto, LocalDateTime fechaHora, boolean visto) {
        this.chatId = chatId;
        this.emisorId = emisorId;
        this.texto = texto;
        this.fechaHora = fechaHora;
        this.visto = visto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
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
