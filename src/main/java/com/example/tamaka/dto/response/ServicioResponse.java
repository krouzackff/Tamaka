package com.example.tamaka.dto.response;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServicioResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer minPersonas;
    private Integer maxPersonas;
    private BigDecimal precioReferencia;
    private String condicionesCancelacion;
    private boolean activo;
    private Long comercioId;
}