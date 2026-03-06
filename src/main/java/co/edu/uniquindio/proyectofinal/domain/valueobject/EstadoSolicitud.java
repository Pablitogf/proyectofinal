package co.edu.uniquindio.proyectofinal.domain.valueobject;

/**
 * Value Object que representa el estado de una solicitud.
 * Es un concepto cerrado con valores predefinidos (Enum).
 */
public enum EstadoSolicitud {
    CREADA,
    CLASIFICADA,
    ASIGNADA,
    EN_ATENCION,
    ATENDIDA,
    CERRADA;

    @Override
    public String toString() {
        return this.name();
    }
}