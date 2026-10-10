package com.example.tamaka.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Información de un atractivo turístico")
public class AtractivoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private boolean activo;
}