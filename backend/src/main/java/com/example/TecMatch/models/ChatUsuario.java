package com.example.TecMatch.models;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chat_usuario")
public class ChatUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public ChatUsuario() {
    }

    public ChatUsuario(Long id, Chat chat, Usuario usuario) {
        this.id = id;
        this.chat = chat;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
