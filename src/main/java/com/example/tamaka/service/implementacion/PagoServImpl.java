package com.example.tamaka.service.implementacion;

import com.example.tamaka.dto.request.PagoRequest;
import com.example.tamaka.dto.response.PagoResponse;
import com.example.tamaka.entity.Pago;
import com.example.tamaka.entity.Reserva;
import com.example.tamaka.mapper.PagoMapper;
import com.example.tamaka.repository.PagoRepository;
import com.example.tamaka.repository.ReservaRepository;
import com.example.tamaka.service.IPagoServ;
import com.example.tamaka.utilities.EstadoReserva;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagoServImpl implements IPagoServ {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;
    private final PagoMapper pagoMapper;

    @Override
    @Transactional
    public PagoResponse pagar(PagoRequest request) {

        Reserva reserva = reservaRepository.findById(request.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (reserva.getEstado() != EstadoReserva.ACEPTADA) {
            throw new RuntimeException("Solo se pueden pagar reservas ACEPTADAS");
        }

        if (pagoRepository.findByReservaId(reserva.getId()).isPresent()) {
            throw new RuntimeException("Esta reserva ya tiene un pago registrado");
        }

        // 🔌 Pasarela simulada: siempre aprueba.
        // El simple hecho de crear el Pago significa que se pagó.
        Pago pago = Pago.builder()
                .reserva(reserva)
                .valor(reserva.getValor())
                .referencia(UUID.randomUUID().toString())
                .fechaPago(LocalDateTime.now())
                .build();

        return pagoMapper.toResponse(pagoRepository.save(pago));
    }

    @Override
    public PagoResponse obtenerPorReserva(Long reservaId) {
        Pago pago = pagoRepository.findByReservaId(reservaId)
                .orElseThrow(() -> new RuntimeException("No hay pago para esa reserva"));
        return pagoMapper.toResponse(pago);
    }
}
