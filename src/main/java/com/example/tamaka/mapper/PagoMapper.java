package com.example.tamaka.mapper;

import com.example.tamaka.dto.response.PagoResponse;
import com.example.tamaka.entity.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    @Mapping(source = "reserva.id", target = "reservaId")
    PagoResponse toResponse(Pago pago);
}