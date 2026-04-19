package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;


public class CambiarEstadoUseCase {

    private final SolicitudRepositorio repositorio;

    public CambiarEstadoUseCase(SolicitudRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void iniciarAtencion(String id, Usuario usuario) {

        Solicitud solicitud = repositorio.buscarPorId(id)
                .orElseThrow();

        solicitud.iniciarAtencion(usuario);

        repositorio.guardar(solicitud);
    }

    public void finalizarAtencion(String id, Usuario usuario) {

        Solicitud solicitud = repositorio.buscarPorId(id)
                .orElseThrow();

        solicitud.finalizarAtencion(usuario);

        repositorio.guardar(solicitud);
    }
}