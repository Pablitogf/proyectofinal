package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.mapper;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.*;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.SolicitudEntity;

public class SolicitudMapper {

    public static SolicitudEntity toEntity(Solicitud s) {
        SolicitudEntity e = new SolicitudEntity();

        e.setId(s.getId());
        e.setCodigo(s.getCodigo().valor());
        e.setDescripcion(s.getDescripcion());
        e.setEstado(s.getEstado().name());

        e.setFechaCreacion(s.getFechaCreacion());
        e.setFechaModificacion(s.getFechaModificacion());
        e.setFechaCierre(s.getFechaCierre());

        if (s.getSolicitante() != null)
            e.setSolicitanteId(s.getSolicitante().getId());

        if (s.getResponsable() != null)
            e.setResponsableId(s.getResponsable().getId());

        if (s.getPrioridad() != null)
            e.setPrioridad(s.getPrioridad().name());

        if (s.getTipo() != null)
            e.setTipo(s.getTipo().name());

        return e;
    }

    public static Solicitud toDomain(
            SolicitudEntity e,
            Usuario solicitante,
            Usuario responsable
    ) {
        return Solicitud.Builder.reconstruirDesdeDB(
                e.getId(),
                new CodigoSolicitud(e.getCodigo()),
                e.getDescripcion(),
                solicitante,
                EstadoSolicitud.valueOf(e.getEstado()),
                e.getPrioridad() != null ? Prioridad.valueOf(e.getPrioridad()) : null,
                e.getTipo() != null ? TipoSolicitud.valueOf(e.getTipo()) : null,
                responsable,
                e.getFechaCreacion(),
                e.getFechaModificacion(),
                e.getFechaCierre()
        );
    }
}