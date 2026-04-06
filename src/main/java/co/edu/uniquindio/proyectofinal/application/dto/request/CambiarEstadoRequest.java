package co.edu.uniquindio.proyectofinal.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CambiarEstadoRequest(

        @NotBlank
        String nuevoEstado,

        String observacion

) {}