package co.edu.uniquindio.proyectofinal.domain.repository;

import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.valueobject.EstadoSolicitud;

import java.util.List;
import java.util.Optional;

public interface SolicitudRepositorio {

    void guardar(Solicitud solicitud);

    Optional<Solicitud> buscarPorId(String id);

    List<Solicitud> listar();

    List<Solicitud> buscarPorEstado(EstadoSolicitud estado);
}