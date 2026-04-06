package co.edu.uniquindio.proyectofinal.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CambiarPrioridadRequest(

        @NotBlank
        String nivelPrioridad,

        @NotBlank
        String coordinadorId,

        @NotBlank
        String justificacion

) {}