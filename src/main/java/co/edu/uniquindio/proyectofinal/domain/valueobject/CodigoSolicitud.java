package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;

/**
 * Value Object que representa un código único de solicitud.
 * Formato: SOL-0001, SOL-0002, SOL-0003...
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

    @Override
    public String toString() {
        return valor;
    }
}