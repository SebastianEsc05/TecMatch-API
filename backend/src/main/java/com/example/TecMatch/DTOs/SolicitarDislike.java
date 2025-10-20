package com.example.TecMatch.DTOs;

public class SolicitarDislike {
    private Long usuarioQueRecibeDislikeId;

    public SolicitarDislike(){}

    public SolicitarDislike(Long usuarioQueRecibeDislikeId) {
        this.usuarioQueRecibeDislikeId = usuarioQueRecibeDislikeId;
    }

    public Long getUsuarioQueRecibeDislikeId(){
        return usuarioQueRecibeDislikeId;
    }
    public void setUsuarioQueRecibeDislikeId(Long usuarioQueRecibeDislikeId){
        this.usuarioQueRecibeDislikeId = usuarioQueRecibeDislikeId;
    }

}
