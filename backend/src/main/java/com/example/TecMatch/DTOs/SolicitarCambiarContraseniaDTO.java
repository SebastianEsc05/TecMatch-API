package com.example.TecMatch.DTOs;

public class SolicitarCambiarContraseniaDTO {
    private String contraseniaActual;
    private String contraseniaNueva;

    public SolicitarCambiarContraseniaDTO() {
    }

    public SolicitarCambiarContraseniaDTO(String contraseniaActual, String contraseniaNueva) {
        this.contraseniaActual = contraseniaActual;
        this.contraseniaNueva = contraseniaNueva;
    }

    public String getContraseniaActual() {
        return contraseniaActual;
    }

    public void setContraseniaActual(String contraseniaActual) {
        this.contraseniaActual = contraseniaActual;
    }

    public String getContraseniaNueva() {
        return contraseniaNueva;
    }

    public void setContraseniaNueva(String contraseniaNueva) {
        this.contraseniaNueva = contraseniaNueva;
    }
}
