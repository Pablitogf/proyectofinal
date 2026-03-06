package co.edu.uniquindio.proyectofinal.domain.valueobject;

import java.time.LocalDateTime;

/**
 * Value Object que representa una entrada en el historial de la solicitud.
 * Es parte del agregado Solicitud y solo se crea a través de la raíz.
 */
public record HistorialSolicitud(
        LocalDateTime fecha,
        String usuario,
        String accion,
        EstadoSolicitud estadoResultante
) {
    public HistorialSolicitud {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("El usuario no puede ser nulo o vacío");
        }
        if (accion == null || accion.isBlank()) {
            throw new IllegalArgumentException("La acción no puede ser nula o vacía");
        }
        if (estadoResultante == null) {
            throw new IllegalArgumentException("El estado resultante no puede ser nulo");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Estado: %s)",
                fecha, usuario, accion, estadoResultante);
    }
}