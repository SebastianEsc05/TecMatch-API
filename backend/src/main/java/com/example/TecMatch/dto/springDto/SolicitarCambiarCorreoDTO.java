package com.example.TecMatch.dto.springDto;

public class SolicitarCambiarCorreoDTO {
    private String nuevoCorreo;
    private String contraseniaActual;

    public SolicitarCambiarCorreoDTO() {
    }

    public SolicitarCambiarCorreoDTO(String nuevoCorreo, String contraseniaActual) {
        this.nuevoCorreo = nuevoCorreo;
        this.contraseniaActual = contraseniaActual;
    }

    public String getNuevoCorreo() {
        return nuevoCorreo;
    }

    public void setNuevoCorreo(String nuevoCorreo) {
        this.nuevoCorreo = nuevoCorreo;
    }

    public String getContraseniaActual() {
        return contraseniaActual;
    }

    public void setContraseniaActual(String contraseniaActual) {
        this.contraseniaActual = contraseniaActual;
    }


}
