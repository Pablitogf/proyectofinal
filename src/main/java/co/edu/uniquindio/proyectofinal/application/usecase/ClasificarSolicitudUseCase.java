package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Prioridad;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoSolicitud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClasificarSolicitudUseCase {

    private final SolicitudRepositorio repository;

    @Transactional
    public Solicitud ejecutar(String id, TipoSolicitud tipo, Prioridad prioridad, Usuario coordinador) {
        Solicitud solicitud = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.clasificar(prioridad, tipo, coordinador);
        repository.guardar(solicitud);
        return solicitud;
    }
}