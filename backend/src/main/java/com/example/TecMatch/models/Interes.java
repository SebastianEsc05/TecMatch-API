package com.example.TecMatch.models;

import jakarta.persistence.*;

@Entity
@Table(name = "intereses")
public class Interes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

}
