package com.example.tamaka.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Datos para aceptar una reserva (el operador fija el valor)")
public class AceptarReservaRequest {

    @NotNull(message = "El valor es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    @Schema(example = "300000.00", description = "Valor total a pagar por el turista")
    private BigDecimal valor;
}