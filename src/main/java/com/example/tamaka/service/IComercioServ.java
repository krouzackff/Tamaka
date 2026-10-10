package com.example.tamaka.service;


import com.example.tamaka.dto.request.ComercioRequest;
import com.example.tamaka.dto.response.ComercioResponse;
import com.example.tamaka.entity.Comercio;

public interface IComercioServ {

    ComercioResponse crear(Long operadorId, ComercioRequest request);

    ComercioResponse obtener(Long id);

    Comercio obtenerEntidad(Long id);
}