package com.example.TecMatch.dto;

import java.io.Serializable;

public class InteresDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String descripcion;

    public InteresDTO() {}

    public InteresDTO(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
