package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.repository;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.repository.UsuarioRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.EstadoSolicitud;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Prioridad;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoSolicitud;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.SolicitudEntity;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.mapper.SolicitudMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class SolicitudRepositorioJpa implements SolicitudRepositorio {

    private final SolicitudJpaDataRepository dataRepository;
    private final UsuarioRepositorio usuarioRepositorio;

    public SolicitudRepositorioJpa(SolicitudJpaDataRepository dataRepository,
                                   UsuarioRepositorio usuarioRepositorio) {
        this.dataRepository = dataRepository;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Solicitud guardar(Solicitud solicitud) {
        SolicitudEntity entity = SolicitudMapper.toEntity(solicitud);
        SolicitudEntity saved = dataRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Solicitud> buscarPorId(String id) {
        return dataRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Solicitud> listar() {
        return dataRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Solicitud> buscarPorEstado(EstadoSolicitud estado) {
        return dataRepository.findByEstado(estado.name())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Solicitud> buscarPorTipoOrdenadoInferencia(TipoSolicitud tipo) {
        return dataRepository.findByTipo(tipo.name())
                .stream()
                .map(this::toDomain)
                .sorted(
                        Comparator.comparingInt((Solicitud s) -> prioridadPeso(s.getPrioridad())).reversed()
                                .thenComparing(Solicitud::getFechaCreacion, Comparator.reverseOrder())
                )
                .toList();
    }

    @Override
    public List<Solicitud> buscarPorTipoOrdenadoJpql(TipoSolicitud tipo) {
        return dataRepository.buscarPorTipoOrdenadoJpql(tipo.name())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Solicitud> buscarPorCodigoOSolicitante(String codigo, String solicitanteId) {
        return dataRepository.buscarPorCodigoOSolicitante(codigo, solicitanteId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Page<Solicitud> buscarPorEstadoDistinto(EstadoSolicitud estadoExcluido, Pageable pageable) {
        return dataRepository.findByEstadoNot(estadoExcluido.name(), pageable)
                .map(this::toDomain);
    }

    @Override
    public Map<String, Long> reporteCantidadPorEstado() {
        Map<String, Long> reporte = new LinkedHashMap<>();
        for (Object[] fila : dataRepository.reporteCantidadPorEstadoNativo()) {
            String estado = fila[0] != null ? fila[0].toString() : "SIN_ESTADO";
            long cantidad = fila[1] != null ? ((Number) fila[1]).longValue() : 0L;
            reporte.put(estado, cantidad);
        }
        return reporte;
    }

    private Solicitud toDomain(SolicitudEntity e) {

        Usuario solicitante = usuarioRepositorio
                .buscarPorId(e.getSolicitanteId())
                .orElse(null);

        Usuario responsable = null;
        if (e.getResponsableId() != null) {
            responsable = usuarioRepositorio
                    .buscarPorId(e.getResponsableId())
                    .orElse(null);
        }

        return SolicitudMapper.toDomain(e, solicitante, responsable);
    }

    private int prioridadPeso(Prioridad prioridad) {
        if (prioridad == null) return 0;
        return switch (prioridad) {
            case CRITICA -> 4;
            case ALTA -> 3;
            case MEDIA -> 2;
            case BAJA -> 1;
        };
    }
}