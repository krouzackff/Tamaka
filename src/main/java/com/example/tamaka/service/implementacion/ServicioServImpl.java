package com.example.tamaka.service.implementacion;


import com.example.tamaka.dto.request.ServicioRequest;
import com.example.tamaka.dto.response.ServicioResponse;
import com.example.tamaka.entity.Comercio;
import com.example.tamaka.entity.Servicio;
import com.example.tamaka.mapper.ServicioMapper;
import com.example.tamaka.repository.ComercioRepository;
import com.example.tamaka.repository.ServicioRepository;
import com.example.tamaka.service.IServicioServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioServImpl implements IServicioServ {

    private final ServicioRepository servicioRepository;
    private final ComercioRepository comercioRepository;
    private final ServicioMapper servicioMapper;

    @Override
    @Transactional
    public ServicioResponse crear(Long comercioId, ServicioRequest request) {

        Comercio comercio = comercioRepository.findById(comercioId)
                .orElseThrow(() -> new RuntimeException("Comercio no encontrado"));

        if (!comercio.isActivo()) {
            throw new RuntimeException("El comercio no está disponible");
        }

        Servicio servicio = Servicio.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .minPersonas(request.getMinPersonas())
                .maxPersonas(request.getMaxPersonas())
                .precioReferencia(request.getPrecioReferencia())
                .condicionesCancelacion(request.getCondicionesCancelacion())
                .activo(true)
                .comercio(comercio)
                .build();

        return servicioMapper.toResponse(servicioRepository.save(servicio));
    }

    @Override
    public List<ServicioResponse> listarPorComercio(Long comercioId) {
        return servicioRepository.findByComercioId(comercioId)
                .stream()
                .map(servicioMapper::toResponse)
                .toList();
    }

    @Override
    public ServicioResponse obtener(Long id) {
        return servicioMapper.toResponse(obtenerEntidad(id));
    }

    @Override
    public Servicio obtenerEntidad(Long id) {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
    }
}