package co.edu.uniquindio.proyectofinal.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CerrarSolicitudRequest(
        @NotBlank(message = "El ID del usuario es obligatorio")
        String usuarioId, // <--- Agregado

        @NotBlank(message = "La observación es obligatoria")
        @Size(min = 10, message = "La observación debe tener al menos 10 caracteres")
        String observacion
) {}