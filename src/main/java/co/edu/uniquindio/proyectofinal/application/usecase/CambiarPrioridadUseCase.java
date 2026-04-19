package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.application.dto.request.CambiarPrioridadRequest;
import co.edu.uniquindio.proyectofinal.domain.model.entity.*;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Prioridad; // Importar el Enum
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CambiarPrioridadUseCase {

    private final SolicitudRepositorio repository;

    public Solicitud ejecutar(String id, CambiarPrioridadRequest request) {

        Solicitud solicitud = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // 1. Corregido: Usar los parámetros requeridos por el constructor de Usuario
        // Usamos el coordinadorId que viene en tu record
        Usuario usuario = new Usuario(request.coordinadorId(), null, null);

        // 2. Corregido: Convertir el String nivelPrioridad al Enum Prioridad
        Prioridad nuevaPrioridad = Prioridad.valueOf(request.nivelPrioridad().toUpperCase());

        solicitud.priorizar(nuevaPrioridad, usuario);

        // 3. Corregido: Guardar y retornar la solicitud (asumiendo que guardar es void)
        repository.guardar(solicitud);

        return solicitud;
    }
}