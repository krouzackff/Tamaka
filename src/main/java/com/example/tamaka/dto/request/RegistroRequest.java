package com.example.tamaka.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Datos para registrar un nuevo usuario")
public class RegistroRequest {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    @Schema(example = "ana@tamaka.co", description = "Email del usuario")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Schema(example = "secreto123", description = "Contraseña (mínimo 6 caracteres)")
    private String password;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(example = "Ana Torres", description = "Nombre completo")
    private String nombre;
}