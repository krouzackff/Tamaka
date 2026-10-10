package com.example.tamaka.entity;

import com.example.tamaka.utilities.EstadoReembolso;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reembolsos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reembolso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pago_id", nullable = false)
    private Pago pago;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReembolso estado = EstadoReembolso.SOLICITADO;

    @Column(nullable = false, length = 500)
    private String motivo;

    @Column(unique = true)
    private String referenciaPasarela;

    @Column(nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(length = 500)
    private String observaciones;
}