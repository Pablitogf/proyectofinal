package co.edu.uniquindio.proyectofinal.domain.entity;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * AGREGADO RAIZ: Solicitud
 */
@Getter
public class Solicitud {

    private static int contador = 1;

    private final String id;
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
        this.id = UUID.randomUUID().toString();
        this.codigo = generarCodigo();
        this.descripcion = builder.descripcion;
        this.solicitante = builder.solicitante;
        this.estado = EstadoSolicitud.CREADA;
        this.prioridad = builder.prioridad;
        this.tipo = builder.tipo;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = this.fechaCreacion;

        registrarHistorial("Solicitud creada", "SISTEMA");
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


    private void registrarHistorial(String accion, String usuario) {
        historial.add(new HistorialSolicitud(LocalDateTime.now(), usuario, accion, this.estado));
        this.fechaModificacion = LocalDateTime.now();
    }


    public void clasificar(Prioridad prioridad, TipoSolicitud tipo, String usuario) {
        validarNoCerrada();
        validarEstadoEsperado(EstadoSolicitud.CREADA, "clasificar");

        if (prioridad == null || tipo == null)
            throw new ReglaDominioException("Prioridad y tipo son obligatorios");

        this.prioridad = prioridad;
        this.tipo = tipo;
        this.estado = EstadoSolicitud.CLASIFICADA;

        registrarHistorial(
                String.format("Clasificada - %s, %s", prioridad, tipo),
                usuario
        );
    }


    public void asignarResponsable(Usuario responsable, String usuario) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitud solicitud = (Solicitud) o;
        return Objects.equals(id, solicitud.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
    }
}