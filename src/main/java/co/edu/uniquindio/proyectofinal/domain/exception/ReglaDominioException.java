package co.edu.uniquindio.proyectofinal.domain.exception;

// Excepción genérica para reglas de negocio
public class ReglaDominioException extends RuntimeException {
    public ReglaDominioException(String mensaje) {
        super(mensaje);
    }
}