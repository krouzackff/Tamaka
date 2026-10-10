package com.example.tamaka.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "atractivos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atractivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    private String ubicacion;

    @Column(nullable = false)
    @Builder.Default
    private boolean activo = true;
}