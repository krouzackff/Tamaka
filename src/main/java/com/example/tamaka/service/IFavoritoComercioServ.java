package com.example.tamaka.service;

import com.example.tamaka.dto.response.ComercioResponse;

import java.util.List;

public interface IFavoritoComercioServ {

    ComercioResponse agregar(Long usuarioId, Long comercioId);

    List<ComercioResponse> listar(Long usuarioId);

    void eliminar(Long usuarioId, Long comercioId);

    boolean esFavorito(Long usuarioId, Long comercioId);
}