package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CambiarEstadoUseCase {

    private final SolicitudRepositorio repositorio;

    @Transactional
    public Solicitud iniciarAtencion(String id, Usuario usuario) {
        Solicitud solicitud = repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitud.iniciarAtencion(usuario);
        repositorio.guardar(solicitud);
        return solicitud;
    }

    @Transactional
    public Solicitud finalizarAtencion(String id, Usuario usuario) {
        Solicitud solicitud = repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitud.finalizarAtencion(usuario);
        repositorio.guardar(solicitud);
        return solicitud;
    }
}