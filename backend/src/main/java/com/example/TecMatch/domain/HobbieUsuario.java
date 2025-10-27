package com.example.TecMatch.domain;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "hobbies_usuarios")
public class HobbieUsuario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hobbie_id")
    private Hobbie hobbie;

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public HobbieUsuario() {
    }

    public HobbieUsuario(Long id, Hobbie hobbie, Usuario usuario) {
        this.hobbie = hobbie;
        this.usuario = usuario;
    }

    public HobbieUsuario(Usuario usuario, Hobbie hobbie) {
        this.usuario = usuario;
        this.hobbie = hobbie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hobbie getHobbie() {
        return hobbie;
    }

    public void setHobbie(Hobbie hobbie) {
        this.hobbie = hobbie;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
