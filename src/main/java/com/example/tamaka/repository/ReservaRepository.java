package com.example.tamaka.repository;

import com.example.tamaka.entity.Reserva;
import com.example.tamaka.utilities.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByTuristaId(Long turistaId);

    List<Reserva> findByTuristaIdAndEstado(Long turistaId, EstadoReserva estado);

    List<Reserva> findByServicioId(Long servicioId);

    List<Reserva> findByServicioComercioId(Long comercioId);

    List<Reserva> findByServicioComercioOperadorId(Long operadorId);

    List<Reserva> findByServicioIdAndEstado(Long servicioId, EstadoReserva estado);
}