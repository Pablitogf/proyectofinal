package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;


public class CerrarSolicitudUseCase {

    private final SolicitudRepositorio repositorio;

    public CerrarSolicitudUseCase(SolicitudRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void ejecutar(String idSolicitud, Usuario usuario) {

        Solicitud solicitud = repositorio.buscarPorId(idSolicitud)
                .orElseThrow();

        solicitud.cerrar(usuario);

        repositorio.guardar(solicitud);
    }
}