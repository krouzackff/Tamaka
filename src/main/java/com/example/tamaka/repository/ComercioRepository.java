package com.example.tamaka.repository;

import com.example.tamaka.entity.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {

    boolean existsByRnt(String rnt);

    List<Comercio> findByOperadorId(Long operadorId);

    List<Comercio> findByActivoTrue();
}
