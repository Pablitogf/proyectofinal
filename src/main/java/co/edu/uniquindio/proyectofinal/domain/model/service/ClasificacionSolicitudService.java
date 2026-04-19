package co.edu.uniquindio.proyectofinal.domain.model.service;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Prioridad;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoSolicitud;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoUser;

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

    private Prioridad determinarPrioridad(
            Solicitud solicitud, TipoSolicitud tipo) {
        // Regla de negocio: prioridad segun tipo
        if (TipoSolicitud.SUPLETORIO.equals(tipo)) {
            return Prioridad.ALTA;
        }
        return Prioridad.MEDIA;
    }
}