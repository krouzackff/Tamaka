package com.example.tamaka.service.implementacion;


import com.example.tamaka.dto.request.LoginRequest;
import com.example.tamaka.dto.request.RegistroRequest;
import com.example.tamaka.dto.response.UsuarioResponse;
import com.example.tamaka.entity.Usuario;
import com.example.tamaka.mapper.UsuarioMapper;
import com.example.tamaka.repository.UsuarioRepository;
import com.example.tamaka.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServImpl implements IAuthService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Set<Usuario.Rol> roles = new HashSet<>();
        roles.add(Usuario.Rol.TURISTA);

        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .password(request.getPassword()) // en producción: hash
                .nombre(request.getNombre())
                .roles(roles)
                .build();

        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!usuario.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return usuarioMapper.toResponse(usuario);
    }
}