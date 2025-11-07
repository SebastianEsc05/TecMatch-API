package com.example.PotroNet.dto.springDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RegisterRequestDTO {
    private String nombre;
    private String carrera;
    private String correo;
    private String descripcion;
    private String contrasenia;
    private String sexo;
    private List<String> hobbies;
    private List<String> intereses;

    public RegisterRequestDTO() {
    }

    public RegisterRequestDTO(String nombre, String carrera, String correo, String descripcion, String contrasenia, String sexo, List<String> hobbies, List<String> intereses) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.correo = correo;
        this.descripcion = descripcion;
        this.contrasenia = contrasenia;
        this.sexo = sexo;
        this.hobbies = hobbies;
        this.intereses = intereses;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<String> intereses) {
        this.intereses = intereses;
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
}
