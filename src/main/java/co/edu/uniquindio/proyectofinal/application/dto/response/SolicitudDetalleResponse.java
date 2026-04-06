package co.edu.uniquindio.proyectofinal.application.dto.response;

import java.time.LocalDateTime;

public record SolicitudDetalleResponse(

        String id,
        String codigo,
        String descripcion,
        String estado,
        String canalOrigen,
        String solicitante,
        String responsable,
        LocalDateTime fechaCreacion

) {}