package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;

/**
 * Value Object que representa un correo electrónico.
 */
public record Email(String direccion) {

    public Email {
        if (direccion == null || direccion.isBlank()) {
            throw new ReglaDominioException("El email no puede ser nulo o vacío");
        }
        // Validación simple de formato de email
        if (!direccion.contains("@")) {
            throw new ReglaDominioException("El formato del email no es válido");
        }
    }

    @Override
    public String toString() {
        return direccion;
    }
}