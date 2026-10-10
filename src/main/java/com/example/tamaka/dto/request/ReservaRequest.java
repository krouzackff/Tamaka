package com.example.tamaka.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Datos para solicitar una reserva")
public class ReservaRequest {

    @NotNull(message = "El servicio es obligatorio")
    @Schema(example = "1", description = "ID del servicio a reservar")
    private Long servicioId;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio no puede ser en el pasado")
    @Schema(example = "2026-11-01")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Schema(example = "2026-11-03")
    private LocalDate fechaFin;

    @NotNull(message = "El número de personas es obligatorio")
    @Min(value = 1, message = "Debe ser al menos 1 persona")
    @Schema(example = "2")
    private Integer personas;
}