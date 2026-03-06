package co.edu.uniquindio.proyectofinal.domain.valueobject;

/**
 * Value Object que representa la prioridad de una solicitud.
 * Es un concepto cerrado con valores predefinidos (Enum).
 */
public enum Prioridad {
    BAJA,
    MEDIA,
    ALTA,
    CRITICA;

    @Override
    public String toString() {
        return this.name();
    }
}