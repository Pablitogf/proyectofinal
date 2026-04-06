package co.edu.uniquindio.proyectofinal.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CrearTipoSolicitudRequest(

        @NotBlank
        String nombre,

        String descripcion

) {}