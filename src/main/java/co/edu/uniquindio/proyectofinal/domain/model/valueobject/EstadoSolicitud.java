package co.edu.uniquindio.proyectofinal.domain.model.valueobject;

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

}