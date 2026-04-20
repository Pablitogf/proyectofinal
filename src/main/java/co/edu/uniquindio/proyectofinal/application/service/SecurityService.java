package co.edu.uniquindio.proyectofinal.application.service;

import co.edu.uniquindio.proyectofinal.infrastructure.rest.dto.LoginRequest;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.dto.TokenResponse;

public interface SecurityService {
    TokenResponse login(LoginRequest request);
}
