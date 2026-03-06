package co.edu.uniquindio.proyectofinal.domain.entity;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * AGREGADO RAIZ: Solicitud
 */
public class Solicitud {
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

    private Solicitud(Builder builder) {
        this.id = UUID.randomUUID().toString();
        this.codigo = builder.codigo;
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
            throw new ReglaDominioException(String.format("No se puede %s - Estado actual: %s", accion, this.estado));
    }

    private void validarEstadoPermitido(List<EstadoSolicitud> permitidos, String accion) {
        if (!permitidos.contains(this.estado))
            throw new ReglaDominioException(String.format("No se puede %s - Estado actual: %s", accion, this.estado));
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
        registrarHistorial(String.format("Clasificada - %s, %s", prioridad, tipo), usuario);
    }

    public void asignarResponsable(Usuario responsable, String usuario) {
        validarNoCerrada();
        validarEstadoPermitido(List.of(EstadoSolicitud.CLASIFICADA, EstadoSolicitud.ASIGNADA), "asignar responsable");
        if (responsable == null)
            throw new ReglaDominioException("Responsable no puede ser nulo");
        if (this.responsable != null && this.responsable.equals(responsable))
            throw new ReglaDominioException("El responsable ya está asignado");
        this.responsable = responsable;
        this.estado = EstadoSolicitud.ASIGNADA;
        registrarHistorial("Responsable asignado: " + responsable.getNombre(), usuario);
    }

    public void iniciarAtencion(String usuario) {
        validarNoCerrada();
        validarTieneResponsable("iniciar atención");
        validarEstadoEsperado(EstadoSolicitud.ASIGNADA, "iniciar atención");
        this.estado = EstadoSolicitud.EN_ATENCION;
        registrarHistorial("Atención iniciada", usuario);
    }

    public void finalizarAtencion(String usuario) {
        validarNoCerrada();
        validarTieneResponsable("finalizar atención");
        validarEstadoEsperado(EstadoSolicitud.EN_ATENCION, "finalizar atención");
        this.estado = EstadoSolicitud.ATENDIDA;
        registrarHistorial("Atención finalizada", usuario);
    }

    public void cerrar(String usuario) {
        validarTieneResponsable("cerrar");
        validarEstadoEsperado(EstadoSolicitud.ATENDIDA, "cerrar");
        this.estado = EstadoSolicitud.CERRADA;
        this.fechaCierre = LocalDateTime.now();
        registrarHistorial("Solicitud cerrada", usuario);
    }

    public void reabrir(String motivo, String usuario) {
        validarEstadoEsperado(EstadoSolicitud.CERRADA, "reabrir");
        if (motivo == null || motivo.isBlank())
            throw new ReglaDominioException("Motivo es obligatorio");
        this.estado = EstadoSolicitud.ATENDIDA;
        this.fechaCierre = null;
        registrarHistorial("Reabierta: " + motivo, usuario);
    }

    public void actualizarPrioridad(Prioridad nuevaPrioridad, String usuario) {
        validarNoCerrada();
        if (nuevaPrioridad == null)
            throw new ReglaDominioException("Prioridad no puede ser nula");
        this.prioridad = nuevaPrioridad;
        registrarHistorial("Prioridad actualizada a " + nuevaPrioridad, usuario);
    }

    public void agregarComentario(String comentario, String usuario) {
        validarNoCerrada();
        if (comentario == null || comentario.isBlank())
            throw new ReglaDominioException("Comentario no puede estar vacío");
        registrarHistorial("Comentario: " + comentario, usuario);
    }

    // Getters
    public String getId() { return id; }
    public CodigoSolicitud getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public Usuario getSolicitante() { return solicitante; }
    public EstadoSolicitud getEstado() { return estado; }
    public Prioridad getPrioridad() { return prioridad; }
    public TipoSolicitud getTipo() { return tipo; }
    public Usuario getResponsable() { return responsable; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaModificacion() { return fechaModificacion; }
    public LocalDateTime getFechaCierre() { return fechaCierre; }
    public List<HistorialSolicitud> getHistorial() { return List.copyOf(historial); }
    public boolean estaCerrada() { return EstadoSolicitud.CERRADA.equals(estado); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitud solicitud = (Solicitud) o;
        return Objects.equals(id, solicitud.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    // Builder
    public static class Builder {
        private CodigoSolicitud codigo;
        private String descripcion;
        private Usuario solicitante;
        private Prioridad prioridad = Prioridad.MEDIA;
        private TipoSolicitud tipo = TipoSolicitud.OTRO;

        public Builder conCodigo(CodigoSolicitud codigo) { this.codigo = codigo; return this; }
        public Builder conDescripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public Builder conSolicitante(Usuario solicitante) { this.solicitante = solicitante; return this; }
        public Builder conPrioridad(Prioridad prioridad) { this.prioridad = prioridad; return this; }
        public Builder conTipo(TipoSolicitud tipo) { this.tipo = tipo; return this; }

        public Solicitud build() {
            if (codigo == null) throw new ReglaDominioException("Código obligatorio");
            if (descripcion == null || descripcion.isBlank()) throw new ReglaDominioException("Descripción obligatoria");
            if (solicitante == null) throw new ReglaDominioException("Solicitante obligatorio");
            return new Solicitud(this);
        }
    }
}