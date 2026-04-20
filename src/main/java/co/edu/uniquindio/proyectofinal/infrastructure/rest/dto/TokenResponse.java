package co.edu.uniquindio.proyectofinal.infrastructure.rest.dto;

import java.time.Instant;
import java.util.Collection;

public record TokenResponse(
        String token,
        String type,
        Instant expireAt,
        Collection<String> roles
) {
}
