package com.example.TecMatch.dto.springDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {
    @NotBlank(message = "El nombreno puede estar vacio")
    private String nombre;
    private String carrera;

    @Email(message = "El formato del correo no esvalido")
    @NotBlank(message = "El correo no puede estar vacio")
    private String correo;
    private String descripcion;
    @NotBlank
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String contrasenia;
    private String sexo;

    public RegisterRequestDTO() {
    }

    public RegisterRequestDTO(String nombre, String carrera, String correo, String descripcion, String contrasenia, String sexo) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.correo = correo;
        this.descripcion = descripcion;
        this.contrasenia = contrasenia;
        this.sexo = sexo;
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
