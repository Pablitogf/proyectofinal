package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.valueobject.EstadoSolicitud;

import java.util.List;

public class ConsultarSolicitudesPorEstadoUseCase {

    private final SolicitudRepositorio repositorio;

    public ConsultarSolicitudesPorEstadoUseCase(SolicitudRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Solicitud> ejecutar(EstadoSolicitud estado) {
        return repositorio.buscarPorEstado(estado);
    }
}
