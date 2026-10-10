package com.example.tamaka.controller;

import com.example.tamaka.dto.request.ComercioRequest;
import com.example.tamaka.dto.response.ComercioResponse;
import com.example.tamaka.service.IComercioServ;
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
@RequestMapping("/api/comercios")
@RequiredArgsConstructor
@Tag(name = "Comercios", description = "Gestión de comercios turísticos (requiere RNT)")
public class ComercioController {

    private final IComercioServ comercioService;

    @PostMapping
    @Operation(
            summary = "Crear un comercio",
            description = """
                    Registra un comercio. Reglas:
                    - El RNT es obligatorio.
                    - El RNT debe ser único.
                    - El comercio se activa automáticamente.
                    - Al usuario que lo crea se le asigna el rol OPERADOR.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Comercio creado"),
            @ApiResponse(responseCode = "400", description = "RNT obligatorio, RNT duplicado o usuario inexistente")
    })
    public ResponseEntity<ComercioResponse> crear(
            @RequestHeader("X-User-Id") Long operadorId,
            @Valid @RequestBody ComercioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comercioService.crear(operadorId, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar un comercio por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comercio encontrado"),
            @ApiResponse(responseCode = "400", description = "Comercio no encontrado")
    })
    public ResponseEntity<ComercioResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(comercioService.obtener(id));
    }
}