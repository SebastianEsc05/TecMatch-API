package com.example.TecMatch.DTOs;

import java.util.Set;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String carrera;
    private String correo;
    private String descripcion;
    private String sexo;
    private Set<String> intereses;
    private Set<String> hobbies;

    public UsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public UsuarioDTO(Long id, String nombre, String carrera, String correo, String descripcion, String sexo, Set<String> intereses, Set<String> hobbies) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.correo = correo;
        this.descripcion = descripcion;
        this.sexo = sexo;
        this.intereses = intereses;
        this.hobbies = hobbies;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<String> getIntereses() {
        return intereses;
    }

    public void setIntereses(Set<String> intereses) {
        this.intereses = intereses;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<String> hobbies) {
        this.hobbies = hobbies;
    }
}
