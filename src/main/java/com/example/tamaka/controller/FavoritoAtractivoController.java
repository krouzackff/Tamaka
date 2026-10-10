package com.example.tamaka.controller;

import com.example.tamaka.dto.response.AtractivoResponse;
import com.example.tamaka.service.IFavoritoAtractivoServ;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos/atractivos")
@RequiredArgsConstructor
@Tag(name = "Favoritos - Atractivos", description = "Atractivos guardados como favoritos")
public class FavoritoAtractivoController {

    private final IFavoritoAtractivoServ favoritoAtractivoService;

    @PostMapping("/{atractivoId}")
    @Operation(summary = "Guardar un atractivo como favorito")
    public ResponseEntity<AtractivoResponse> agregar(
            @RequestHeader("X-User-Id") Long usuarioId,
            @PathVariable Long atractivoId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(favoritoAtractivoService.agregar(usuarioId, atractivoId));
    }

    @GetMapping
    @Operation(summary = "Listar los atractivos favoritos del turista")
    public ResponseEntity<List<AtractivoResponse>> listar(
            @RequestHeader("X-User-Id") Long usuarioId) {
        return ResponseEntity.ok(favoritoAtractivoService.listar(usuarioId));
    }

    @DeleteMapping("/{atractivoId}")
    @Operation(summary = "Eliminar un atractivo de favoritos")
    public ResponseEntity<Void> eliminar(
            @RequestHeader("X-User-Id") Long usuarioId,
            @PathVariable Long atractivoId) {
        favoritoAtractivoService.eliminar(usuarioId, atractivoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{atractivoId}")
    @Operation(summary = "Consultar si un atractivo está en favoritos")
    public ResponseEntity<Boolean> esFavorito(
            @RequestHeader("X-User-Id") Long usuarioId,
            @PathVariable Long atractivoId) {
        return ResponseEntity.ok(favoritoAtractivoService.esFavorito(usuarioId, atractivoId));
    }
}