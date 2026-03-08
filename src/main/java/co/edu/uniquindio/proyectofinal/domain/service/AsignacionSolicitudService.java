package co.edu.uniquindio.proyectofinal.domain.service;


import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;

/**
 * Servicio de Dominio: AsignacionSolicitudService
 */
public class AsignacionSolicitudService {

    public void asignar(Solicitud solicitud, Usuario responsable, Usuario asignador) {
        if (!asignador.esCoordinador()) {
            throw new ReglaDominioException("Solo coordinadores pueden asignar solicitudes");
        }

        if (!responsable.esDocente() && !responsable.esCoordinador()) {
            throw new ReglaDominioException("El responsable debe ser docente o coordinador");
        }

        solicitud.asignarResponsable(responsable, asignador);
    }
}