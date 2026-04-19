package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CerrarSolicitudUseCase {

    private final SolicitudRepositorio repositorio;

    @Transactional
    public Solicitud ejecutar(String id, Usuario responsable) {
        Solicitud solicitud = repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.cerrar(responsable);
        repositorio.guardar(solicitud);
        return solicitud;
    }
}