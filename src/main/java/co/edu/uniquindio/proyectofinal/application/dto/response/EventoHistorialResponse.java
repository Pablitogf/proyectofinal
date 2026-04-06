package co.edu.uniquindio.proyectofinal.application.dto.response;

import java.time.LocalDateTime;

public record EventoHistorialResponse(

        Long secuencia,
        LocalDateTime fechaHora,
        String accion,
        String usuarioNombre,
        String observacion

) {}