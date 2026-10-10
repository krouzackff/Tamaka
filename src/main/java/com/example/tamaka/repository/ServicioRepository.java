package com.example.tamaka.repository;

import com.example.tamaka.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    List<Servicio> findByComercioId(Long comercioId);

    List<Servicio> findByComercioIdAndActivoTrue(Long comercioId);

    List<Servicio> findByActivoTrue();
}
