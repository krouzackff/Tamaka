package com.example.tamaka.mapper;

import com.example.tamaka.dto.response.ServicioResponse;
import com.example.tamaka.entity.Servicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServicioMapper {

    @Mapping(source = "comercio.id", target = "comercioId")
    ServicioResponse toResponse(Servicio servicio);
}