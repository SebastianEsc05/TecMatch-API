package com.example.PotroNet.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha_hora;

    @ManyToOne
    @JoinColumn(name = "emisor_id")
    private Usuario emisor;

    private String texto;
    private boolean visto;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;


    public Mensaje() {
    }

    public Mensaje(Long id, LocalDateTime fecha_hora, Usuario emisor, String texto, boolean visto, Chat chat) {
        this.fecha_hora = fecha_hora;
        this.texto = texto;
        this.visto = visto;
        this.emisor = emisor;
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}