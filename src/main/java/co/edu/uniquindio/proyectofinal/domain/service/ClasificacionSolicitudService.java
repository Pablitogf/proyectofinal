package co.edu.uniquindio.proyectofinal.domain.service;

import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.TipoSolicitud;

/**
 * Servicio de Dominio: ClasificacionSolicitudService
 */
public class ClasificacionSolicitudService {

    public void clasificar(Solicitud solicitud, TipoSolicitud tipo, Usuario coordinador) {
        // Validacion: solo coordinadores clasifican
        if (!"COORDINADOR".equals(coordinador.getRol())) {
            throw new ReglaDominioException("Solo coordinadores pueden clasificar solicitudes");
        }

        // Decision: la prioridad se asigna segun tipo y solicitante
        var prioridad = determinarPrioridad(solicitud, tipo);
        solicitud.clasificar(prioridad, tipo, coordinador.getNombre());
    }

    private co.edu.uniquindio.solicitudes.domain.valueobject.Prioridad determinarPrioridad(
            Solicitud solicitud, TipoSolicitud tipo) {
        // Regla de negocio: prioridad segun tipo
        if (TipoSolicitud.SUPLETORIO.equals(tipo)) {
            return co.edu.uniquindio.solicitudes.domain.valueobject.Prioridad.ALTA;
        }
        return co.edu.uniquindio.solicitudes.domain.valueobject.Prioridad.MEDIA;
    }
}