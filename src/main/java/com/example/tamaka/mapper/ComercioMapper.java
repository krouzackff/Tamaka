package com.example.tamaka.mapper;

import com.example.tamaka.dto.response.ComercioResponse;
import com.example.tamaka.entity.Comercio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComercioMapper {

    @Mapping(source = "operador.id", target = "operadorId")
    ComercioResponse toResponse(Comercio comercio);
}