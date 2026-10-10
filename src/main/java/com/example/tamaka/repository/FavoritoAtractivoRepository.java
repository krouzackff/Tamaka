package com.example.tamaka.repository;

import com.example.tamaka.entity.FavoritoAtractivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoAtractivoRepository extends JpaRepository<FavoritoAtractivo, Long> {

    List<FavoritoAtractivo> findByUsuarioId(Long usuarioId);

    Optional<FavoritoAtractivo> findByUsuarioIdAndAtractivoId(Long usuarioId, Long atractivoId);

    boolean existsByUsuarioIdAndAtractivoId(Long usuarioId, Long atractivoId);
}