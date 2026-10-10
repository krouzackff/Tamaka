package com.example.tamaka.repository;

import com.example.tamaka.entity.FavoritoComercio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoComercioRepository extends JpaRepository<FavoritoComercio, Long> {

    List<FavoritoComercio> findByUsuarioId(Long usuarioId);

    Optional<FavoritoComercio> findByUsuarioIdAndComercioId(Long usuarioId, Long comercioId);

    boolean existsByUsuarioIdAndComercioId(Long usuarioId, Long comercioId);
}