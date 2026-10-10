package com.example.tamaka.service;

import com.example.tamaka.dto.request.PagoRequest;
import com.example.tamaka.dto.response.PagoResponse;

public interface IPagoServ {

    PagoResponse pagar(PagoRequest request);

    PagoResponse obtenerPorReserva(Long reservaId);
}