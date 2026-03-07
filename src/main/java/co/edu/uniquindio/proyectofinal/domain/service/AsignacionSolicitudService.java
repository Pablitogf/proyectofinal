package co.edu.uniquindio.proyectofinal.domain.service;


import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;

/**
 * Servicio de Dominio: AsignacionSolicitudService
 */
public class AsignacionSolicitudService {

    public void asignar(Solicitud solicitud, Usuario responsable, Usuario asignador) {
        // Validacion cruzada: roles
        if (!"COORDINADOR".equals(asignador.getRolUser())) {
            throw new ReglaDominioException("Solo coordinadores pueden asignar solicitudes");
        }

        if (!"DOCENTE".equals(responsable.getRolUser()) && !"COORDINADOR".equals(responsable.getRolUser())) {
            throw new ReglaDominioException("El responsable debe ser docente o coordinador");
        }

        solicitud.asignarResponsable(responsable, asignador);
    }
}