package co.edu.uniquindio.proyectofinal.domain.model.repository;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.EstadoSolicitud;
import java.util.List;
import java.util.Optional;

public interface SolicitudRepositorio {
    // IMPORTANTE: Que devuelva la Solicitud para que el Use Case pueda retornarla
    Solicitud guardar(Solicitud solicitud);

    Optional<Solicitud> buscarPorId(String id);
    List<Solicitud> listar();
    List<Solicitud> buscarPorEstado(EstadoSolicitud estado);
}