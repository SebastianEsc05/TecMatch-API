package com.example.TecMatch.dto;

import com.example.TecMatch.domain.enums.Tipo;
import java.time.LocalDateTime;
import java.util.Set;

public class ChatDTO {
    private Long id;
    private LocalDateTime fecha_creacion;
    private Tipo tipo;
    private Set<Long> usuarioIds;

    public ChatDTO() {
    }

    public ChatDTO(Long id, LocalDateTime fecha_creacion, Tipo tipo, Set<Long> usuarioIds) {
        this.id = id;
        this.fecha_creacion = fecha_creacion;
        this.tipo = tipo;
        this.usuarioIds = usuarioIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Set<Long> getUsuarioIds() {
        return usuarioIds;
    }

    public void setUsuarioIds(Set<Long> usuarioIds) {
        this.usuarioIds = usuarioIds;
    }
}