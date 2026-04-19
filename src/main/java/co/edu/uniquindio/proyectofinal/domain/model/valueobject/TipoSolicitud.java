package co.edu.uniquindio.proyectofinal.domain.model.valueobject;

/**
 * Value Object que representa el tipo de solicitud (Ej: Homologación, Cancelación, etc.)
 * Por simplicidad, lo modelamos como un Enum.
 */
public enum TipoSolicitud {
    HOMOLOGACION,
    CANCELACION_SEMESTRE,
    SUPLETORIO,
    CERTIFICADO,
    SOPORTE,
    OTRO;

    @Override
    public String toString() {
        return this.name();
    }
}