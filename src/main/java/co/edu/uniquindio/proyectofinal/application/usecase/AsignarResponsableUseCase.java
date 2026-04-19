package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.application.dto.request.AsignarResponsableRequest;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsignarResponsableUseCase {

    private final SolicitudRepositorio repositorio;

    public Solicitud ejecutar(String idSolicitud, AsignarResponsableRequest request) {

        Solicitud solicitud = repositorio.buscarPorId(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // Se usa el constructor de 3 argumentos: nombre, email, tipo
        // Usamos los IDs del request como nombres temporales para que compile
        Usuario responsable = new Usuario(request.responsableId(), null, null);
        Usuario quienAsigna = new Usuario(request.usuarioId(), null, null);

        solicitud.asignarResponsable(responsable, quienAsigna);

        // Guardamos (el método es void)
        repositorio.guardar(solicitud);

        // Retornamos la solicitud actualizada para el Controller
        return solicitud;
    }
}