package com.example.tamaka.service;


import com.example.tamaka.dto.request.ServicioRequest;
import com.example.tamaka.dto.response.ServicioResponse;
import com.example.tamaka.entity.Servicio;

import java.util.List;

public interface IServicioServ {

    ServicioResponse crear(Long comercioId, ServicioRequest request);

    List<ServicioResponse> listarPorComercio(Long comercioId);

    ServicioResponse obtener(Long id);

    Servicio obtenerEntidad(Long id);
}