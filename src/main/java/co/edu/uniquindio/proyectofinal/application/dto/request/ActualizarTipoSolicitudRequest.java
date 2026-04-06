package co.edu.uniquindio.proyectofinal.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ActualizarTipoSolicitudRequest(

        @NotBlank
        String nombre,

        String descripcion

) {}