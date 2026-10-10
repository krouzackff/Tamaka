package com.example.tamaka.controller;

import com.example.tamaka.dto.request.LoginRequest;
import com.example.tamaka.dto.request.RegistroRequest;
import com.example.tamaka.dto.response.UsuarioResponse;
import com.example.tamaka.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Registro e inicio de sesión de usuarios")
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/registro")
    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Crea una cuenta con rol TURISTA. El email debe ser único."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Email ya registrado o datos inválidos")
    })
    public ResponseEntity<UsuarioResponse> registrar(@Valid @RequestBody RegistroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registrar(request));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Iniciar sesión",
            description = "Valida las credenciales del usuario. No genera token (MVP sin JWT)."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credenciales válidas"),
            @ApiResponse(responseCode = "400", description = "Credenciales inválidas")
    })
    public ResponseEntity<UsuarioResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}