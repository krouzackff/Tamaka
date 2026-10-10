package com.example.tamaka.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Credenciales de inicio de sesión")
public class LoginRequest {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    @Schema(example = "ana@tamaka.co")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Schema(example = "secreto123")
    private String password;
}