package com.example.TecMatch.models;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "intereses_usuarios")
public class InteresUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "interes_id")
    private Interes interes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public InteresUsuario() {
    }

    public InteresUsuario(Long id, Interes interes, Usuario usuario) {
        this.id = id;
        this.interes = interes;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Interes getInteres() {
        return interes;
    }

    public void setInteres(Interes interes) {
        this.interes = interes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
