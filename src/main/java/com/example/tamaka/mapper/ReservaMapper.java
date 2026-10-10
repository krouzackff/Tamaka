package com.example.tamaka.mapper;

import com.example.tamaka.dto.response.ReservaResponse;
import com.example.tamaka.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(source = "servicio.id", target = "servicioId")
    @Mapping(source = "servicio.nombre", target = "servicioNombre")
    @Mapping(source = "servicio.comercio.id", target = "comercioId")
    @Mapping(source = "servicio.comercio.nombre", target = "comercioNombre")
    @Mapping(source = "turista.id", target = "turistaId")
    @Mapping(target = "pagada", ignore = true) // se calcula en el service
    ReservaResponse toResponse(Reserva reserva);
}