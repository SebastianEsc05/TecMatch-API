package com.example.PotroNet.dto;

public class InteresUsuarioDTO {
    private Long id;
    private Long usuarioId;
    private Long interesId;
    private String usuarioNombre;
    private String interesDescripcion;

    public InteresUsuarioDTO() {
    }

    public InteresUsuarioDTO(Long id, Long usuarioId, Long interesId) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.interesId = interesId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getInteresId() {
        return interesId;
    }

    public void setInteresId(Long interesId) {
        this.interesId = interesId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getInteresDescripcion() {
        return interesDescripcion;
    }

    public void setInteresDescripcion(String interesDescripcion) {
        this.interesDescripcion = interesDescripcion;
    }
}