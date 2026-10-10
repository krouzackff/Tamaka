package com.example.tamaka.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Datos para registrar un comercio (RNT obligatorio)")
public class ComercioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255)
    @Schema(example = "Tour Tayrona", description = "Nombre del comercio")
    private String nombre;

    @Size(max = 1000, message = "La descripción no puede superar 1000 caracteres")
    @Schema(example = "Caminatas guiadas por el Parque Tayrona")
    private String descripcion;

    @NotBlank(message = "El RNT es obligatorio")
    @Size(max = 100)
    @Schema(example = "RNT-12345", description = "Registro Nacional de Turismo (obligatorio)")
    private String rnt;
}