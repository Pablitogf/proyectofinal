package co.edu.uniquindio.proyectofinal.application.dto.response;

import java.time.LocalDateTime;

public record SolicitudResumenResponse(

        String id,
        String codigo,
        String descripcionBreve,
        String estado,
        String solicitanteNombre,
        String responsableNombre,
        LocalDateTime fechaCreacion

) {}