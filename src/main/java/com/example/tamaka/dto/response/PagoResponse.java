package com.example.tamaka.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "Información de un pago")
public class PagoResponse {
    private Long id;
    private Long reservaId;
    private BigDecimal valor;
    private String referencia;
    private LocalDateTime fechaPago;
}