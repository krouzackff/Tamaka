package com.example.tamaka.service.implementacion;

package com.example.tamaka.service.impl;

import com.example.tamaka.dto.request.AceptarReservaRequest;
import com.example.tamaka.dto.request.ReservaRequest;
import com.example.tamaka.dto.response.ReservaResponse;
import com.example.tamaka.entity.Reserva;
import com.example.tamaka.entity.Servicio;
import com.example.tamaka.entity.Usuario;
import com.example.tamaka.exception.BusinessException;
import com.example.tamaka.mapper.ReservaMapper;
import com.example.tamaka.repository.PagoRepository;
import com.example.tamaka.repository.ReservaRepository;
import com.example.tamaka.repository.UsuarioRepository;
import com.example.tamaka.service.IReservaServ;
import com.example.tamaka.service.IServicioServ;
import com.example.tamaka.utilities.EstadoReserva;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReservaServImpl implements IReservaServ {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PagoRepository pagoRepository;
    private final IServicioServ servicioService;
    private final ReservaMapper reservaMapper;

    @Override
    @Transactional
    public ReservaResponse solicitar(Long turistaId, ReservaRequest request) {

        if (request.getFechaInicio().isAfter(request.getFechaFin())) {
            throw new BusinessException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        if (request.getPersonas() == null || request.getPersonas() <= 0) {
            throw new BusinessException("El número de personas debe ser mayor a cero");
        }

        Usuario turista = usuarioRepository.findById(turistaId)
                .orElseThrow(() -> new BusinessException("Turista no encontrado"));

        Servicio servicio = servicioService.obtenerEntidad(request.getServicioId());

        if (!servicio.isActivo()) {
            throw new BusinessException("El servicio no está disponible");
        }
        if (servicio.getMinPersonas() != null && request.getPersonas() < servicio.getMinPersonas()) {
            throw new BusinessException("El mínimo de personas es " + servicio.getMinPersonas());
        }
        if (servicio.getMaxPersonas() != null && request.getPersonas() > servicio.getMaxPersonas()) {
            throw new BusinessException("El máximo de personas es " + servicio.getMaxPersonas());
        }

        Reserva reserva = Reserva.builder()
                .turista(turista)
                .servicio(servicio)
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .personas(request.getPersonas())
                .estado(EstadoReserva.PENDIENTE)
                .valor(BigDecimal.ZERO)
                .build();

        Reserva guardada = reservaRepository.save(reserva);
        return toResponseConPagada(guardada);
    }

    @Override
    @Transactional
    public ReservaResponse aceptar(Long reservaId, AceptarReservaRequest request) {
        Reserva reserva = obtenerEntidad(reservaId);

        if (reserva.getEstado() != EstadoReserva.PENDIENTE) {
            throw new BusinessException("Solo se pueden aceptar reservas PENDIENTES");
        }

        reserva.setEstado(EstadoReserva.ACEPTADA);
        reserva.setValor(request.getValor());

        return toResponseConPagada(reservaRepository.save(reserva));
    }

    @Override
    @Transactional
    public ReservaResponse rechazar(Long reservaId) {
        Reserva reserva = obtenerEntidad(reservaId);

        if (reserva.getEstado() != EstadoReserva.PENDIENTE) {
            throw new BusinessException("Solo se pueden rechazar reservas PENDIENTES");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);

        return toResponseConPagada(reservaRepository.save(reserva));
    }

    @Override
    @Transactional
    public ReservaResponse cancelar(Long usuarioId, Long reservaId) {
        Reserva reserva = obtenerEntidad(reservaId);

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new BusinessException("La reserva ya está cancelada");
        }

        boolean esTurista = reserva.getTurista().getId().equals(usuarioId);
        boolean esOperador = reserva.getServicio().getComercio()
                .getOperador().getId().equals(usuarioId);

        if (!esTurista && !esOperador) {
            throw new BusinessException("No tienes permiso para cancelar esta reserva");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);

        return toResponseConPagada(reservaRepository.save(reserva));
    }

    @Override
    public ReservaResponse obtener(Long id) {
        return toResponseConPagada(obtenerEntidad(id));
    }

    @Override
    public Reserva obtenerEntidad(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Reserva no encontrada"));
    }

    private ReservaResponse toResponseConPagada(Reserva reserva) {
        ReservaResponse response = reservaMapper.toResponse(reserva);
        boolean pagada = pagoRepository.findByReservaId(reserva.getId()).isPresent();
        response.setPagada(pagada);
        return response;
    }
}