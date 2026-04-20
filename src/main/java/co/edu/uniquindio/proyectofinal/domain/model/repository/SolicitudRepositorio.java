package co.edu.uniquindio.proyectofinal.domain.model.repository;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.EstadoSolicitud;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoSolicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SolicitudRepositorio {
    // IMPORTANTE: Que devuelva la Solicitud para que el Use Case pueda retornarla
    Solicitud guardar(Solicitud solicitud);

    Optional<Solicitud> buscarPorId(String id);
    List<Solicitud> listar();
    List<Solicitud> buscarPorEstado(EstadoSolicitud estado);
    List<Solicitud> buscarPorTipoOrdenadoInferencia(TipoSolicitud tipo);
    List<Solicitud> buscarPorTipoOrdenadoJpql(TipoSolicitud tipo);
    List<Solicitud> buscarPorCodigoOSolicitante(String codigo, String solicitanteId);
    Page<Solicitud> buscarPorEstadoDistinto(EstadoSolicitud estadoExcluido, Pageable pageable);
    Map<String, Long> reporteCantidadPorEstado();
}