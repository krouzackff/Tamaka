package com.example.tamaka.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favoritos_atractivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoritoAtractivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atractivo_id", nullable = false)
    private Atractivo atractivo;
}