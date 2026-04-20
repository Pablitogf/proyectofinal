package co.edu.uniquindio.proyectofinal.infrastructure.security.services;

import co.edu.uniquindio.proyectofinal.application.service.SecurityService;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.dto.LoginRequest;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.dto.TokenResponse;
import co.edu.uniquindio.proyectofinal.infrastructure.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service("securityService")
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.expiry}")
    private long expiryInMinutes;

    @Override
    public TokenResponse login(LoginRequest request) {
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        final var roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        final var now = Instant.now();
        final var expireAt = now.plus(expiryInMinutes, ChronoUnit.MINUTES);

        return new TokenResponse(
                jwtTokenProvider.generateTokenAsString(authentication.getName(), roles, now, expireAt),
                "Bearer",
                expireAt,
                roles
        );
    }
}
