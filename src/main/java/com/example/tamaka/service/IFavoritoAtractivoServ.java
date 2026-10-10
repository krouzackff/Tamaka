package com.example.tamaka.service;

import com.example.tamaka.dto.response.AtractivoResponse;

import java.util.List;

public interface IFavoritoAtractivoServ {

    AtractivoResponse agregar(Long usuarioId, Long atractivoId);

    List<AtractivoResponse> listar(Long usuarioId);

    void eliminar(Long usuarioId, Long atractivoId);

    boolean esFavorito(Long usuarioId, Long atractivoId);
}