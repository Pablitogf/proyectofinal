package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.repository.SolicitudRepositorio;

public class CrearSolicitudUseCase {

    private final SolicitudRepositorio repositorio;

    public CrearSolicitudUseCase(SolicitudRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Solicitud ejecutar(String descripcion, Usuario solicitante) {

        Solicitud solicitud = Solicitud.Builder
                .registrar(descripcion, solicitante);

        repositorio.guardar(solicitud);

        return solicitud;
    }
}