package com.example.TecMatch.models;

import com.example.TecMatch.enums.Tipo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha_creacion;
    private Tipo tipo;

    @ManyToMany
    @JoinTable(
            name = "chat_participantes",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> participantes;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private Set<Mensaje> mensajes;
}
