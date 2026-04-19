package co.edu.uniquindio.proyectofinal.infrastructure.rest.mapper;

import co.edu.uniquindio.proyectofinal.application.dto.response.*;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SolicitudMapper {

    // Mapeo para Detalle (Extrae el String del Value Object y el nombre del Usuario)
    @Mapping(target = "codigo", source = "codigo.valor")
    @Mapping(target = "solicitante", source = "solicitante.nombre")
    @Mapping(target = "responsable", source = "responsable.nombre")
    SolicitudDetalleResponse toDetalleResponse(Solicitud solicitud);

    // Mapeo para Resumen (Soluciona el error que me pasaste)
    @Mapping(target = "codigo", source = "codigo.valor")
    SolicitudResumenResponse toResumenResponse(Solicitud solicitud);

    List<SolicitudResumenResponse> toResumenResponseList(List<Solicitud> solicitudes);
}