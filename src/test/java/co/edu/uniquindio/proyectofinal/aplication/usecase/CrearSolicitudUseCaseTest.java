package co.edu.uniquindio.proyectofinal.aplication.usecase;

import co.edu.uniquindio.proyectofinal.application.dto.request.CrearSolicitudRequest;
import co.edu.uniquindio.proyectofinal.application.usecase.CrearSolicitudUseCase;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita Mockito para JUnit 5
class CrearSolicitudUseCaseTest {

    @Mock
    private SolicitudRepositorio repositorio; // Crea un simulacro del repositorio

    @InjectMocks
    private CrearSolicitudUseCase crearSolicitudUseCase; // Inyecta el mock automáticamente

    @Test
    void debeCrearYGuardarUnaSolicitudExitosamente() {
        // 1. GIVEN (Preparación de datos)
        // Ajustado para que coincida con los tipos del DTO (Long para ID de tipo)
        CrearSolicitudRequest request = new CrearSolicitudRequest(
                1L, // Long tipoSolicitudId
                "Mi internet no funciona",
                "usuario-123"
        );

        // Mock del comportamiento del repositorio [cite: 236]
        when(repositorio.guardar(any(Solicitud.class))).thenAnswer(i -> i.getArguments()[0]);

        // 2. WHEN (Ejecución)
        Solicitud resultado = crearSolicitudUseCase.ejecutar(request);

        // 3. THEN (Verificaciones) [cite: 238]
        assertNotNull(resultado);
        verify(repositorio, times(1)).guardar(any(Solicitud.class)); // Verifica la persistencia [cite: 38]
    }

    @Test
    void debeFallarCuandoLaDescripcionEsNula() {
        // GIVEN
        CrearSolicitudRequest request = new CrearSolicitudRequest(
                1L,
                "Descripción con más de 20 caracteres para que sea válida",
                "WEB" // Agregamos el tercer argumento (canalOrigen)
        );

        // WHEN & THEN
        // Verificamos que lance una excepción si la descripción es nula (regla de negocio)
        assertThrows(RuntimeException.class, () -> {
            crearSolicitudUseCase.ejecutar(request);
        });

        // Verificamos que NUNCA se llamó al repositorio porque falló antes
        verify(repositorio, never()).guardar(any());
    }
}