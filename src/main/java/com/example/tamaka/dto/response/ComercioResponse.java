package com.example.tamaka.dto.response;

import lombok.Data;

@Data
public class ComercioResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private String rnt;
    private boolean activo;
    private Long operadorId;
}