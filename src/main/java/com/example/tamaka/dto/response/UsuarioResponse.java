package com.example.tamaka.dto.response;

import lombok.Data;
import java.util.Set;

@Data
public class UsuarioResponse {
    private Long id;
    private String email;
    private String nombre;
    private Set<String> roles;
}