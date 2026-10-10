package com.example.tamaka.controller;

import com.example.tamaka.dto.response.ComercioResponse;
import com.example.tamaka.service.IFavoritoComercioServ;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos/comercios")
@RequiredArgsConstructor
@Tag(name = "Favoritos - Comercios", description = "Comercios guardados como favoritos")
public class FavoritoComercioController {

    private final IFavoritoComercioServ favoritoComercioService;

    @PostMapping("/{comercioId}")
    @Operation(summary = "Guardar un comercio como favorito")
    public ResponseEntity<ComercioResponse> agregar(
            @RequestHeader("X-User-Id") Long usuarioId,
            @PathVariable Long comercioId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(favoritoComercioService.agregar(usuarioId, comercioId));
    }

    @GetMapping
    @Operation(summary = "Listar los comercios favoritos del turista")
    public ResponseEntity<List<ComercioResponse>> listar(
            @RequestHeader("X-User-Id") Long usuarioId) {
        return ResponseEntity.ok(favoritoComercioService.listar(usuarioId));
    }

    @DeleteMapping("/{comercioId}")
    @Operation(summary = "Eliminar un comercio de favoritos")
    public ResponseEntity<Void> eliminar(
            @RequestHeader("X-User-Id") Long usuarioId,
            @PathVariable Long comercioId) {
        favoritoComercioService.eliminar(usuarioId, comercioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{comercioId}")
    @Operation(summary = "Consultar si un comercio está en favoritos")
    public ResponseEntity<Boolean> esFavorito(
            @RequestHeader("X-User-Id") Long usuarioId,
            @PathVariable Long comercioId) {
        return ResponseEntity.ok(favoritoComercioService.esFavorito(usuarioId, comercioId));
    }
}