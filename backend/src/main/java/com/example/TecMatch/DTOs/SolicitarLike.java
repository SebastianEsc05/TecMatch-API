package com.example.TecMatch.DTOs;

public class SolicitarLike {
    private Long usuarioQueRecibeLikeId;

    public SolicitarLike(){}

    public SolicitarLike(Long usuarioQueRecibeLikeId) {
        this.usuarioQueRecibeLikeId = usuarioQueRecibeLikeId;
    }

    public Long getUsuarioQueRecibeLikeId(){
        return usuarioQueRecibeLikeId;
    }
    public void setUsuarioQueRecibeLikeId(Long usuarioQueRecibeLikeId){
        this.usuarioQueRecibeLikeId = usuarioQueRecibeLikeId;
    }



}
