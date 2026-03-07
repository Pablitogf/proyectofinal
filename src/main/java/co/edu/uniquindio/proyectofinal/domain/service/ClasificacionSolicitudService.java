package co.edu.uniquindio.proyectofinal.domain.service;

import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.TipoSolicitud;
import co.edu.uniquindio.proyectofinal.domain.valueobject.TipoUser;

/**
 * Servicio de Dominio: ClasificacionSolicitudService
 */
public class ClasificacionSolicitudService {

    public void clasificar(Solicitud solicitud, TipoSolicitud tipo, Usuario coordinador) {

        // Validación: solo coordinadores clasifican
        if (coordinador.getRolUser() != TipoUser.COORDINADOR) {
            throw new ReglaDominioException("Solo coordinadores pueden clasificar solicitudes");
        }

        // Decisión: la prioridad se asigna según tipo y solicitante
        var prioridad = determinarPrioridad(solicitud, tipo);
        solicitud.clasificar(prioridad, tipo, coordinador);
    }

    private co.edu.uniquindio.proyectofinal.domain.valueobject.Prioridad determinarPrioridad(
            Solicitud solicitud, TipoSolicitud tipo) {
        // Regla de negocio: prioridad segun tipo
        if (TipoSolicitud.SUPLETORIO.equals(tipo)) {
            return co.edu.uniquindio.proyectofinal.domain.valueobject.Prioridad.ALTA;
        }
        return co.edu.uniquindio.proyectofinal.domain.valueobject.Prioridad.MEDIA;
    }
}