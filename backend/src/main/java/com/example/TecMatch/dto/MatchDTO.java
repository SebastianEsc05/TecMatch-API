package com.example.TecMatch.dto;

public class MatchDTO {
    private Long id;
    private Long usuario1Id;
    private Long usuario2Id;

    public MatchDTO() {
    }

    public MatchDTO(Long id, Long usuario1Id, Long usuario2Id) {
        this.id = id;
        this.usuario1Id = usuario1Id;
        this.usuario2Id = usuario2Id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuario1Id() {
        return usuario1Id;
    }

    public void setUsuario1Id(Long usuario1Id) {
        this.usuario1Id = usuario1Id;
    }

    public Long getUsuario2Id() {
        return usuario2Id;
    }

    public void setUsuario2Id(Long usuario2Id) {
        this.usuario2Id = usuario2Id;
    }
}