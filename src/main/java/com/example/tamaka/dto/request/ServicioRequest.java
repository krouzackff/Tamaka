package com.example.tamaka.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Datos para crear un servicio turístico dentro de un comercio")
public class ServicioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255)
    @Schema(example = "Caminata 2 días")
    private String nombre;

    @Size(max = 1000)
    @Schema(example = "Recorrido guiado con hospedaje incluido")
    private String descripcion;

    @Min(value = 1, message = "El mínimo de personas debe ser al menos 1")
    @Schema(example = "2", description = "Número mínimo de personas (opcional)")
    private Integer minPersonas;

    @Min(value = 1, message = "El máximo de personas debe ser al menos 1")
    @Schema(example = "10", description = "Número máximo de personas (opcional)")
    private Integer maxPersonas;

    @PositiveOrZero(message = "El precio de referencia no puede ser negativo")
    @Schema(example = "150000.00", description = "Precio de referencia (opcional)")
    private BigDecimal precioReferencia;

    @Size(max = 1000)
    @Schema(example = "Cancelación gratuita hasta 48h antes")
    private String condicionesCancelacion;
}