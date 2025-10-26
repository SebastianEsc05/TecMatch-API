package com.example.TecMatch.domain;
import com.example.TecMatch.domain.enums.Tipo;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha_creacion;
    private Tipo tipo;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private Set<ChatUsuario> chatUsuarios;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private Set<Math> mensajes;

    public Chat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Set<ChatUsuario> getChatUsuarios() {
        return chatUsuarios;
    }

    public void setChatUsuarios(Set<ChatUsuario> chatUsuarios) {
        this.chatUsuarios = chatUsuarios;
    }

    public Set<Math> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Set<Math> mensajes) {
        this.mensajes = mensajes;
    }
}
