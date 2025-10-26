package com.example.TecMatch.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "dislikes")
public class Dislike implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disliker_id")
    private Usuario disliker;

    @ManyToOne
    @JoinColumn(name = "disliked_id")
    private Usuario disliked;

    private LocalDateTime fecha_hora;

    public Dislike() {}

    public Dislike(LocalDateTime fecha_hora, Usuario disliker, Usuario disliked, Long id) {
        this.fecha_hora = fecha_hora;
        this.disliker = disliker;
        this.disliked = disliked;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getDisliker() {
        return disliker;
    }

    public void setDisliker(Usuario disliker) {
        this.disliker = disliker;
    }

    public Usuario getDisliked() {
        return disliked;
    }

    public void setDisliked(Usuario disliked) {
        this.disliked = disliked;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
}
