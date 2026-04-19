package co.edu.uniquindio.proyectofinal.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AsignarResponsableRequest(
        @NotBlank String responsableId,
        @NotBlank String usuarioId // Verifica si aquí dice 'usuarioId' o 'coordinadorId'
) {}