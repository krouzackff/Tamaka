package com.example.tamaka.repository;

import com.example.tamaka.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    Optional<Pago> findByReservaId(Long reservaId);

    boolean existsByReservaId(Long reservaId);

    Optional<Pago> findByReferencia(String referencia);
}
