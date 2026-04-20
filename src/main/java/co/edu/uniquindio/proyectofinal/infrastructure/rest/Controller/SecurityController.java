package co.edu.uniquindio.proyectofinal.infrastructure.rest.controller;

import co.edu.uniquindio.proyectofinal.application.service.SecurityService;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.dto.LoginRequest;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.dto.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(securityService.login(request));
    }
}
