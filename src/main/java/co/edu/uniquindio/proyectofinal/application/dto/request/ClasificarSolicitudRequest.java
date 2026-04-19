package co.edu.uniquindio.proyectofinal.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClasificarSolicitudRequest(
        @NotNull
        Long tipoSolicitudId,

        @NotBlank
        String prioridad,

        @NotBlank
        @Size(min = 10, max = 500)
        String justificacion,

        @NotBlank
        String coordinadorId // Agrega este campo
) {}