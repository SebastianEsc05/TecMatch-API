package com.example.PotroNet.dto.springDto;

public class ExistRequestDTO {
    private String correo;

    public ExistRequestDTO() {
    }

    public ExistRequestDTO(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
