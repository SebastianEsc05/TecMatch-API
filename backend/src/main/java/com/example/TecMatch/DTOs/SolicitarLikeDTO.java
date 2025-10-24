package com.example.TecMatch.DTOs;

public class SolicitarLikeDTO {
    private Long usuarioQueRecibeLikeId;

    public SolicitarLikeDTO(){}

    public SolicitarLikeDTO(Long usuarioQueRecibeLikeId) {
        this.usuarioQueRecibeLikeId = usuarioQueRecibeLikeId;
    }

    public Long getUsuarioQueRecibeLikeId(){
        return usuarioQueRecibeLikeId;
    }
    public void setUsuarioQueRecibeLikeId(Long usuarioQueRecibeLikeId){
        this.usuarioQueRecibeLikeId = usuarioQueRecibeLikeId;
    }



}
