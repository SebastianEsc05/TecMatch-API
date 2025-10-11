package com.example.TecMatch.models;
import com.example.TecMatch.enums.Tipo;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "chat")
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
    private Set<Mensaje> mensajes;

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

    public Set<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
