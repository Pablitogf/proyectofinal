package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;

/**
 * Value Object que representa un código único de solicitud.
 */
public record CodigoSolicitud(String valor) {

    public CodigoSolicitud {
        if (valor == null || valor.isBlank()) {
            throw new ReglaDominioException("El código de solicitud no puede ser nulo o vacío");
        }
        // Formato SOL-0001
        if (!valor.matches("^SOL-\\d{4}$")) {
            throw new ReglaDominioException("El formato del código es inválido. Debe ser SOL-0001");
        }
    }
}