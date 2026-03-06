package co.edu.uniquindio.proyectofinal.domain.valueobject;

/**
 * Value Object que representa el tipo de solicitud (Ej: Homologación, Cancelación, etc.)
 * Por simplicidad, lo modelamos como un Enum.
 */

public enum TipoUser {
    ESTUDIANTE,
    DOCENTE,
    COORDINADOR,
    OTRO;

    @Override
    public String toString(){
        return this.name();
    }

}
