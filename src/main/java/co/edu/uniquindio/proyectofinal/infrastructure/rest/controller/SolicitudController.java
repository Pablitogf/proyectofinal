/**
 * Controlador REST para la gestión de solicitudes, encargado de adaptar las peticiones HTTP
 * al lenguaje del dominio y orquestar la ejecución mediante casos de uso.
 */
package co.edu.uniquindio.proyectofinal.infrastructure.rest.controller;

import co.edu.uniquindio.proyectofinal.application.dto.request.*;
import co.edu.uniquindio.proyectofinal.application.dto.response.*;
import co.edu.uniquindio.proyectofinal.application.usecase.*;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Prioridad;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoSolicitud;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.mapper.SolicitudMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final CrearSolicitudUseCase crearSolicitudUseCase;
    private final ObtenerSolicitudUseCase obtenerSolicitudUseCase;
    private final AsignarResponsableUseCase asignarResponsableUseCase;
    private final CambiarPrioridadUseCase cambiarPrioridadUseCase;
    private final CerrarSolicitudUseCase cerrarSolicitudUseCase;
    private final ClasificarSolicitudUseCase clasificarSolicitudUseCase;
    private final SolicitudMapper mapper;

    @PostMapping("/{id}/crear")
    public ResponseEntity<SolicitudDetalleResponse> crear(@Valid @RequestBody CrearSolicitudRequest request) {
        // Si usuarioId es Long en el record, usamos .toString() para el nombre en el constructor
        Usuario solicitante = new Usuario(request.usuarioId().toString(), null, null);

        Solicitud solicitud = crearSolicitudUseCase.ejecutar(request.descripcion(), solicitante);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(solicitud.getId()).toUri();

        return ResponseEntity.created(location).body(mapper.toDetalleResponse(solicitud));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDetalleResponse> obtener(@PathVariable String id) {
        return ResponseEntity.ok(mapper.toDetalleResponse(obtenerSolicitudUseCase.ejecutar(id)));
    }

    @PutMapping("/{id}/clasificar")
    public ResponseEntity<SolicitudDetalleResponse> clasificar(
            @PathVariable String id,
            @Valid @RequestBody ClasificarSolicitudRequest request) {

        TipoSolicitud tipo = TipoSolicitud.valueOf(request.tipoSolicitudId().toString());
        Prioridad prioridad = Prioridad.valueOf(request.prioridad().toUpperCase());
        Usuario coordinador = new Usuario(request.coordinadorId(), null, null);

        Solicitud solicitud = clasificarSolicitudUseCase.ejecutar(id, tipo, prioridad, coordinador);

        return ResponseEntity.ok(mapper.toDetalleResponse(solicitud));
    }

    @PutMapping("/{id}/asignar")
    public ResponseEntity<SolicitudDetalleResponse> asignar(
            @PathVariable String id,
            @Valid @RequestBody AsignarResponsableRequest request) {

        Usuario responsable = new Usuario(request.responsableId(), null, null);
        Usuario quienAsigna = new Usuario(request.usuarioId(), null, null);

        Solicitud solicitud = asignarResponsableUseCase.ejecutar(id, responsable, quienAsigna);

        return ResponseEntity.ok(mapper.toDetalleResponse(solicitud));
    }

    @PutMapping("/{id}/priorizar")
    public ResponseEntity<SolicitudDetalleResponse> priorizar(
            @PathVariable String id,
            @Valid @RequestBody CambiarPrioridadRequest request) {

        Prioridad prioridad = Prioridad.valueOf(request.nivelPrioridad().toUpperCase());
        Usuario coordinador = new Usuario(request.coordinadorId(), null, null);

        Solicitud solicitud = cambiarPrioridadUseCase.ejecutar(id, prioridad, coordinador);

        return ResponseEntity.ok(mapper.toDetalleResponse(solicitud));
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<SolicitudDetalleResponse> cerrar(
            @PathVariable String id,
            @Valid @RequestBody CerrarSolicitudRequest request) {

        // Agregamos .toString() por si el ID en el DTO es numérico
        Usuario responsable = new Usuario(request.usuarioId().toString(), null, null);

        Solicitud solicitud = cerrarSolicitudUseCase.ejecutar(id, responsable);

        return ResponseEntity.ok(mapper.toDetalleResponse(solicitud));
    }
}