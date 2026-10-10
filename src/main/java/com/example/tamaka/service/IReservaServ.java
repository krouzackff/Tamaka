package com.example.tamaka.service;


import com.example.tamaka.dto.request.AceptarReservaRequest;
import com.example.tamaka.dto.request.ReservaRequest;
import com.example.tamaka.dto.response.ReservaResponse;
import com.example.tamaka.entity.Reserva;

public interface IReservaServ {

    ReservaResponse solicitar(Long turistaId, ReservaRequest request);

    ReservaResponse aceptar(Long reservaId, AceptarReservaRequest request);

    ReservaResponse rechazar(Long reservaId);

    ReservaResponse cancelar(Long usuarioId, Long reservaId);

    ReservaResponse obtener(Long id);

    Reserva obtenerEntidad(Long id);
}