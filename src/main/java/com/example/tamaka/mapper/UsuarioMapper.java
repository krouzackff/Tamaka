package com.example.tamaka.mapper;


import com.example.tamaka.dto.response.UsuarioResponse;
import com.example.tamaka.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "roles", expression = "java(rolesToString(usuario.getRoles()))")
    UsuarioResponse toResponse(Usuario usuario);

    default Set<String> rolesToString(Set<Usuario.Rol> roles) {
        if (roles == null) return Set.of();
        return roles.stream().map(Enum::name).collect(Collectors.toSet());
    }
}