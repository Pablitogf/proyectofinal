package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.EstadoSolicitud;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoSolicitud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsultasAvanzadasSolicitudUseCase {

    private final SolicitudRepositorio solicitudRepositorio;

    @Transactional(readOnly = true)
    public List<Solicitud> homologacionesOrdenadasPorInferencia() {
        return solicitudRepositorio.buscarPorTipoOrdenadoInferencia(TipoSolicitud.HOMOLOGACION);
    }

    @Transactional(readOnly = true)
    public List<Solicitud> homologacionesOrdenadasPorJpql() {
        return solicitudRepositorio.buscarPorTipoOrdenadoJpql(TipoSolicitud.HOMOLOGACION);
    }

    @Transactional(readOnly = true)
    public List<Solicitud> buscarPorCodigoOSolicitante(String codigo, String solicitanteId) {
        return solicitudRepositorio.buscarPorCodigoOSolicitante(codigo, solicitanteId);
    }

    @Transactional(readOnly = true)
    public Page<Solicitud> listarNoCerradasPaginado(int pagina, int tamano) {
        PageRequest pageRequest = PageRequest.of(
                Math.max(pagina, 0),
                Math.max(tamano, 1),
                Sort.by("fechaCreacion").descending()
        );
        return solicitudRepositorio.buscarPorEstadoDistinto(EstadoSolicitud.CERRADA, pageRequest);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Map<String, Long> reporteNativoCantidadPorEstado() {
        return solicitudRepositorio.reporteCantidadPorEstado();
    }
}
