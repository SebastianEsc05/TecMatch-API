package com.example.TecMatch.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
public class Like implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "liker_id")
    private Usuario liker;

    @ManyToOne
    @JoinColumn(name = "liked_id")
    private Usuario liked;

    private LocalDateTime fecha_hora;

    public Like() {}

    public Like(LocalDateTime fecha_hora, Usuario liker, Usuario liked, Long id) {
        this.fecha_hora = fecha_hora;
        this.liker = liker;
        this.liked = liked;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getLiker() {
        return liker;
    }

    public void setLiker(Usuario liker) {
        this.liker = liker;
    }

    public Usuario getLiked() {
        return liked;
    }

    public void setLiked(Usuario liked) {
        this.liked = liked;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
}
