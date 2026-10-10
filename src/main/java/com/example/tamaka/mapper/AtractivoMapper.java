package com.example.tamaka.mapper;

import com.example.tamaka.dto.response.AtractivoResponse;
import com.example.tamaka.entity.Atractivo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtractivoMapper {

    AtractivoResponse toResponse(Atractivo atractivo);
}