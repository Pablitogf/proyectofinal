package co.edu.uniquindio.proyectofinal.domain.exception;
/**
 * Excepción específica del dominio para violaciones de reglas de negocio.
 */
public class ReglaDominioException extends RuntimeException {
    public ReglaDominioException(String message) {
        super(message);
    }
}