package co.edu.uniquindio.proyectofinal.infrastructure.rest.controller;

import co.edu.uniquindio.proyectofinal.application.dto.request.*;
import co.edu.uniquindio.proyectofinal.application.dto.response.*;
import co.edu.uniquindio.proyectofinal.application.usecase.*;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.mapper.SolicitudMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final CrearSolicitudUseCase crearSolicitudUseCase;
    private final ObtenerSolicitudUseCase obtenerSolicitudUseCase;
    private final AsignarResponsableUseCase asignarResponsableUseCase;
    private final CambiarPrioridadUseCase cambiarPrioridadUseCase;
    private final CerrarSolicitudUseCase cerrarSolicitudUseCase; // Ejercicio 1 de la guia
    private final SolicitudMapper mapper;

    @PostMapping
    public ResponseEntity<SolicitudDetalleResponse> crear(@Valid @RequestBody CrearSolicitudRequest request) {
        return ResponseEntity.status(201).body(mapper.toDetalleResponse(crearSolicitudUseCase.ejecutar(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDetalleResponse> obtener(@PathVariable String id) {
        return ResponseEntity.ok(mapper.toDetalleResponse(obtenerSolicitudUseCase.ejecutar(id)));
    }

    @PutMapping("/{id}/asignar")
    public ResponseEntity<SolicitudDetalleResponse> asignar(@PathVariable String id, @Valid @RequestBody AsignarResponsableRequest request) {
        return ResponseEntity.ok(mapper.toDetalleResponse(asignarResponsableUseCase.ejecutar(id, request)));
    }

    @PutMapping("/{id}/prioridad")
    public ResponseEntity<SolicitudDetalleResponse> priorizar(@PathVariable String id, @Valid @RequestBody CambiarPrioridadRequest request) {
        return ResponseEntity.ok(mapper.toDetalleResponse(cambiarPrioridadUseCase.ejecutar(id, request)));
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<SolicitudDetalleResponse> cerrar(@PathVariable String id, @Valid @RequestBody CerrarSolicitudRequest request) {
        return ResponseEntity.ok(mapper.toDetalleResponse(cerrarSolicitudUseCase.ejecutar(id, request)));
    }
}