package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.application.dto.request.ClasificarSolicitudRequest;
import co.edu.uniquindio.proyectofinal.domain.model.entity.*;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Prioridad;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoSolicitud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClasificarSolicitudUseCase {

    private final SolicitudRepositorio repository;

    public Solicitud ejecutar(String id, ClasificarSolicitudRequest request) {

        Solicitud solicitud = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        Usuario usuario = new Usuario(request.coordinadorId(), null, null);

        Prioridad prioridad = Prioridad.valueOf(request.prioridad().toUpperCase());
        TipoSolicitud tipo = TipoSolicitud.valueOf(request.tipo().toUpperCase());

        solicitud.clasificar(prioridad, tipo, usuario);

        repository.guardar(solicitud);

        return solicitud;
    }
}