package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.repository.SolicitudRepositorio;


public class AsignarResponsableUseCase {

    private final SolicitudRepositorio repositorio;

    public AsignarResponsableUseCase(SolicitudRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void ejecutar(String idSolicitud, Usuario responsable, Usuario quienAsigna) {

        Solicitud solicitud = repositorio.buscarPorId(idSolicitud)
                .orElseThrow();

        solicitud.asignarResponsable(responsable, quienAsigna);

        repositorio.guardar(solicitud);
    }
}