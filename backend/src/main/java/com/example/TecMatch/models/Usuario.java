package com.example.TecMatch.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String carrera;
    private String correo;
    private String descripcion;
    private String contrasenia;
    private String sexo;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
           name = "usuario_intereses",
           joinColumns = @JoinColumn(name = "usuario_id"),
           inverseJoinColumns = @JoinColumn(name = "interes_id")
   )
   private Set<Interes> interees;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_hobbies",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "hobbie_id")
    )
    private Set<Hobbie> hobbies;

    @ManyToMany(mappedBy = "participantes", cascade = CascadeType.ALL)
    private Set<Chat> chats;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<Interes> getInterees() {
        return interees;
    }

    public void setInterees(Set<Interes> interees) {
        this.interees = interees;
    }

    public Set<Hobbie> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobbie> hobbies) {
        this.hobbies = hobbies;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.contrasenia;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
