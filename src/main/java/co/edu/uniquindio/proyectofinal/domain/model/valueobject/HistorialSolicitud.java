package co.edu.uniquindio.proyectofinal.domain.model.valueobject;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;

import java.time.LocalDateTime;

/**
 * Value Object que representa una entrada en el historial de la solicitud.
 * Es parte del agregado Solicitud y solo se crea a través de la raíz.
 */
public record HistorialSolicitud(
        LocalDateTime fecha,
        Usuario usuario,
        String accion,
        EstadoSolicitud estadoResultante
) {
    public HistorialSolicitud {
        if (fecha == null) {
            throw new ReglaDominioException("La fecha no puede ser nula");
        }
        if (usuario == null) {
            throw new ReglaDominioException("El usuario no puede ser nulo");
        }
        if (accion == null || accion.isBlank()) {
            throw new ReglaDominioException("La acción no puede ser nula o vacía");
        }
        if (estadoResultante == null) {
            throw new ReglaDominioException("El estado resultante no puede ser nulo");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] Usuario: %s | Acción: %s | Estado: %s",
                fecha,
                usuario.getNombre(),
                accion,
                estadoResultante
        );
    }
}