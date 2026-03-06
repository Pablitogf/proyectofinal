package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.solicitudes.domain.exception.ReglaDominioException;

/**
 * Value Object que representa un código único de solicitud.
 * Ejemplo de formato: SOL-2024-0001
 */
public record CodigoSolicitud(String valor) {

    public CodigoSolicitud {
        if (valor == null || valor.isBlank()) {
            throw new ReglaDominioException("El código de solicitud no puede ser nulo o vacío");
        }
        // Ejemplo de validación de formato (puede ajustarse)
        if (!valor.matches("^SOL-\\d{4}-\\d{4}$")) {
            throw new ReglaDominioException("El formato del código de solicitud es inválido. Debe ser SOL-YYYY-XXXX");
        }
    }
}