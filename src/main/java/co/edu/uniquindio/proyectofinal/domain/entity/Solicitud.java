package co.edu.uniquindio.proyectofinal.domain.entity;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AGREGADO RAIZ: Solicitud
 */
@Getter
public class Solicitud {

    private static int contador = 1;

    private final CodigoSolicitud codigo;
    private final String descripcion;
    private final Usuario solicitante;

    private EstadoSolicitud estado;
    private Prioridad prioridad;
    private TipoSolicitud tipo;
    private Usuario responsable;

    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaCierre;

    private final List<HistorialSolicitud> historial = new ArrayList<>();


    private static CodigoSolicitud generarCodigo() {
        String codigo = String.format("SOL-%04d", contador++);
        return new CodigoSolicitud(codigo);
    }


    private Solicitud(Builder builder) {

        this.codigo = generarCodigo();
        this.descripcion = builder.descripcion;
        this.solicitante = builder.solicitante;

        this.estado = EstadoSolicitud.CREADA;

        this.prioridad = builder.prioridad;
        this.tipo = builder.tipo;

        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = this.fechaCreacion;

        registrarHistorial("Solicitud creada", solicitante);
    }


    private void validarNoCerrada() {
        if (EstadoSolicitud.CERRADA.equals(this.estado))
            throw new ReglaDominioException("Solicitud cerrada no puede modificarse");
    }

    private void validarEstadoEsperado(EstadoSolicitud esperado, String accion) {
        if (!this.estado.equals(esperado))
            throw new ReglaDominioException(
                    String.format("No se puede %s - Estado actual: %s", accion, this.estado)
            );
    }

    private void validarEstadoPermitido(List<EstadoSolicitud> permitidos, String accion) {
        if (!permitidos.contains(this.estado))
            throw new ReglaDominioException(
                    String.format("No se puede %s - Estado actual: %s", accion, this.estado)
            );
    }

    private void validarTieneResponsable(String accion) {
        if (this.responsable == null)
            throw new ReglaDominioException("No se puede " + accion + " - Sin responsable asignado");
    }


    private void registrarHistorial(String accion, Usuario usuario) {
        historial.add(new HistorialSolicitud(LocalDateTime.now(), usuario, accion, this.estado));
        this.fechaModificacion = LocalDateTime.now();
    }


    public void clasificar(Prioridad prioridad, TipoSolicitud tipo, Usuario usuario) {

        validarNoCerrada();
        validarEstadoEsperado(EstadoSolicitud.CREADA, "clasificar");

        if (prioridad == null || tipo == null)
            throw new ReglaDominioException("Prioridad y tipo son obligatorios");

        if (usuario == null)
            throw new ReglaDominioException("Usuario que clasifica es obligatorio");

        this.prioridad = prioridad;
        this.tipo = tipo;
        this.estado = EstadoSolicitud.CLASIFICADA;

        registrarHistorial(
                String.format("Clasificada - %s, %s", prioridad, tipo),
                usuario
        );
    }


    public void asignarResponsable(Usuario responsable, Usuario usuario) {

        validarNoCerrada();
        validarEstadoPermitido(
                List.of(EstadoSolicitud.CLASIFICADA, EstadoSolicitud.ASIGNADA),
                "asignar responsable"
        );

        if (responsable == null)
            throw new ReglaDominioException("Responsable no puede ser nulo");

        if (this.responsable != null && this.responsable.equals(responsable))
            throw new ReglaDominioException("El responsable ya está asignado");

        this.responsable = responsable;
        this.estado = EstadoSolicitud.ASIGNADA;

        registrarHistorial(
                "Responsable asignado: " + responsable.getNombre(),
                usuario
        );
    }


    public void priorizar(Prioridad nuevaPrioridad, Usuario usuario) {

        validarNoCerrada();

        if (nuevaPrioridad == null)
            throw new ReglaDominioException("La prioridad es obligatoria");

        if (usuario == null)
            throw new ReglaDominioException("Usuario que prioriza es obligatorio");

        this.prioridad = nuevaPrioridad;

        registrarHistorial(
                "Prioridad cambiada a " + nuevaPrioridad,
                usuario
        );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solicitud solicitud)) return false;
        return Objects.equals(codigo, solicitud.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }


    public static class Builder {

        private String descripcion;
        private Usuario solicitante;
        private Prioridad prioridad;
        private TipoSolicitud tipo;

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder solicitante(Usuario solicitante) {
            this.solicitante = solicitante;
            return this;
        }

        public Builder prioridad(Prioridad prioridad) {
            this.prioridad = prioridad;
            return this;
        }

        public Builder tipo(TipoSolicitud tipo) {
            this.tipo = tipo;
            return this;
        }

        public Solicitud build() {

            if (descripcion == null || descripcion.isBlank())
                throw new ReglaDominioException("La descripción es obligatoria");

            if (solicitante == null)
                throw new ReglaDominioException("El solicitante es obligatorio");

            return new Solicitud(this);
        }

        public static Solicitud registrar(String descripcion, Usuario solicitante) {
            return new Builder()
                    .descripcion(descripcion)
                    .solicitante(solicitante)
                    .build();
        }
    }
}