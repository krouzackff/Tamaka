package com.example.tamaka.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "Información de una reserva")
public class ReservaResponse {
    private Long id;
    private Long servicioId;
    private String servicioNombre;
    private Long comercioId;
    private String comercioNombre;
    private Long turistaId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer personas;
    private String estado;
    private BigDecimal valor;
    private boolean pagada;
}