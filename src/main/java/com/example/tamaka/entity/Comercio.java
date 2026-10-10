package com.example.tamaka.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comercios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false, unique = true)
    private String rnt;

    @Column(nullable = false)
    @Builder.Default
    private boolean activo = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operador_id", nullable = false)
    private Usuario operador;

    @OneToMany(mappedBy = "comercio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Servicio> servicios = new ArrayList<>();

    public void addServicio(Servicio servicio) {
        servicios.add(servicio);
        servicio.setComercio(this);
    }

    public void removeServicio(Servicio servicio) {
        servicios.remove(servicio);
        servicio.setComercio(null);
    }
}