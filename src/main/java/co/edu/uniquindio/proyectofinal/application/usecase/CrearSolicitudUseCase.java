package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoSolicitud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CrearSolicitudUseCase {

    private final SolicitudRepositorio repositorio;

    @Transactional
    public Solicitud ejecutar(String descripcion, Usuario solicitante) {
        // El Use Case orquesta: crea la entidad usando el dominio y la persiste [cite: 106, 110]
        Solicitud solicitud = Solicitud.Builder.registrar(descripcion, solicitante);
        return repositorio.guardar(solicitud);
    }
}