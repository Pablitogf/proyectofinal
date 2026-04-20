package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.repository;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.repository.UsuarioRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.EstadoSolicitud;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.SolicitudEntity;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.mapper.SolicitudMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
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
}