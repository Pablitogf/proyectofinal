package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.application.dto.request.CrearSolicitudRequest;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrearSolicitudUseCase {

    private final SolicitudRepositorio repositorio;

    public Solicitud ejecutar(CrearSolicitudRequest request) {

        Usuario solicitante = new Usuario(request.usuarioId());

        Solicitud solicitud = Solicitud.Builder
                .registrar(request.descripcion(), solicitante);

        return repositorio.guardar(solicitud);
    }
}