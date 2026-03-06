package co.edu.uniquindio.proyectofinal.domain.service;

import co.edu.uniquindio.solicitudes.domain.entity.Solicitud;

import java.util.List;

/**
 * Servicio de Dominio: NotificadorSolicitudes
 */
public class NotificadorSolicitudes {

    public List<String> determinarDestinatarios(Solicitud solicitud, TipoNotificacion tipo) {
        return switch (tipo) {
            case NUEVA_SOLICITUD -> List.of("coordinadores@uniquindio.edu.co");
            case ASIGNACION -> List.of(
                    solicitud.getResponsable().getEmail().toString(),
                    solicitud.getSolicitante().getEmail().toString()
            );
            case CAMBIO_ESTADO -> List.of(solicitud.getSolicitante().getEmail().toString());
            case CIERRE -> List.of(
                    solicitud.getSolicitante().getEmail().toString(),
                    solicitud.getResponsable().getEmail().toString()
            );
        };
    }

    public enum TipoNotificacion {
        NUEVA_SOLICITUD, ASIGNACION, CAMBIO_ESTADO, CIERRE
    }
}