package com.example.TecMatch.models;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "hobbie")
public class Hobbie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @OneToMany(mappedBy = "hobbie", cascade = CascadeType.ALL)
    private Set<HobbieUsuario> hobbieUsuarios;

    public Hobbie() {
    }

    public Hobbie(Long id, String descripcion, Set<HobbieUsuario> hobbieUsuarios) {
        this.id = id;
        this.descripcion = descripcion;
        this.hobbieUsuarios = hobbieUsuarios;
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

    public Set<HobbieUsuario> getHobbieUsuarios() {
        return hobbieUsuarios;
    }

    public void setHobbieUsuarios(Set<HobbieUsuario> hobbieUsuarios) {
        this.hobbieUsuarios = hobbieUsuarios;
    }
}
