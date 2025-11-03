package com.example.PotroNet.dto;

public class HobbieUsuarioDTO {
    private Long id;
    private Long usuarioId;
    private Long hobbieId;
    private String usuarioNombre;
    private String hobbieDescripcion;

    public HobbieUsuarioDTO() {
    }

    public HobbieUsuarioDTO(Long id, Long usuarioId, Long hobbieId) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.hobbieId = hobbieId;
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

    public Long getHobbieId() {
        return hobbieId;
    }

    public void setHobbieId(Long hobbieId) {
        this.hobbieId = hobbieId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getHobbieDescripcion() {
        return hobbieDescripcion;
    }

    public void setHobbieDescripcion(String hobbieDescripcion) {
        this.hobbieDescripcion = hobbieDescripcion;
    }
}
