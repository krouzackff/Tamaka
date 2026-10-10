package com.example.tamaka.service;

import com.example.tamaka.dto.request.LoginRequest;
import com.example.tamaka.dto.request.RegistroRequest;
import com.example.tamaka.dto.response.UsuarioResponse;

public interface IAuthService {

    UsuarioResponse registrar(RegistroRequest request);

    UsuarioResponse login(LoginRequest request);
}
