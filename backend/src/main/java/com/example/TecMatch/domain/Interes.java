package com.example.TecMatch.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "intereses")
public class Interes implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @OneToMany(mappedBy = "interes", cascade = CascadeType.ALL)
    private Set<InteresUsuario> interesUsuarios;

    public Interes() {
    }

    public Interes(Long id, String descripcion, Set<InteresUsuario> interesUsuarios) {
        this.id = id;
        this.descripcion = descripcion;
        this.interesUsuarios = interesUsuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<InteresUsuario> getInteresUsuarios() {
        return interesUsuarios;
    }

    public void setInteresUsuarios(Set<InteresUsuario> interesUsuarios) {
        this.interesUsuarios = interesUsuarios;
    }
}
