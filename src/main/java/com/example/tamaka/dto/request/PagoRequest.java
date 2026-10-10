package com.example.tamaka.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Datos para pagar una reserva aceptada (pasarela simulada)")
public class PagoRequest {

    @NotNull(message = "El ID de la reserva es obligatorio")
    @Schema(example = "1")
    private Long reservaId;

    @NotBlank(message = "El número de tarjeta es obligatorio")
    @Pattern(regexp = "\\d{13,19}", message = "Debe ser un número de tarjeta válido (13 a 19 dígitos)")
    @Schema(example = "4111111111111111",
            description = "Número de tarjeta (simulado, no se almacena)")
    private String numeroTarjeta;
}