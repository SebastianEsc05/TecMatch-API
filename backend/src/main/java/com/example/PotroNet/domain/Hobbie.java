package com.example.PotroNet.domain;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "hobbies")
public class Hobbie implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @OneToMany(mappedBy = "hobbie", cascade = CascadeType.ALL)
    private Set<HobbieUsuario> hobbieUsuarios;

    public Hobbie() {
    }

    public Hobbie(String descripcion, Set<HobbieUsuario> hobbieUsuarios) {
        this.descripcion = descripcion;
        this.hobbieUsuarios = hobbieUsuarios;
    }

    public Hobbie(String nombreHobbie) {
        this.descripcion = nombreHobbie;
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
