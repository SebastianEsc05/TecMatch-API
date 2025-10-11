package com.example.TecMatch.models;

import jakarta.persistence.*;

@Entity
@Table(name = "hobbies")
public class Hobbie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

}
