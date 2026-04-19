package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.EstadoSolicitud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultarSolicitudesPorEstadoUseCase {

    private final SolicitudRepositorio repositorio;

    @Transactional(readOnly = true)
    public List<Solicitud> ejecutar(EstadoSolicitud estado) {
        return repositorio.buscarPorEstado(estado);
    }
}