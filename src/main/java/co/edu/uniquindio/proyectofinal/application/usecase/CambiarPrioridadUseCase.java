package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Prioridad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CambiarPrioridadUseCase {

    private final SolicitudRepositorio repository;

    @Transactional
    public Solicitud ejecutar(String id, Prioridad prioridad, Usuario coordinador) {
        Solicitud solicitud = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.priorizar(prioridad, coordinador);
        repository.guardar(solicitud);
        return solicitud;
    }
}