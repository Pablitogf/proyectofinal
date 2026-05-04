package co.edu.uniquindio.proyectofinal.application.usecase;

import co.edu.uniquindio.proyectofinal.application.dto.request.CrearSolicitudRequest;
import co.edu.uniquindio.proyectofinal.application.usecase.CrearSolicitudUseCase;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Email;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoUser;
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
        // GIVEN
        String descripcion = "Mi internet no funciona y necesito soporte urgente";

        // CORRECCIÓN: Crea el usuario con un Email y TipoUser válidos
        Usuario solicitante = new Usuario(
                "usuario-123",
                new Email("pablo@uniquindio.edu.co"),
                TipoUser.ESTUDIANTE
        );

        // Mock del repositorio
        when(repositorio.guardar(any(Solicitud.class))).thenAnswer(i -> i.getArguments()[0]);

        // WHEN
        Solicitud resultado = crearSolicitudUseCase.ejecutar(descripcion, solicitante);

        // THEN
        assertNotNull(resultado);
        verify(repositorio, times(1)).guardar(any(Solicitud.class));
    }

    @Test
    void debeFallarCuandoLaDescripcionEsNula() {
        // GIVEN
        // También ajustamos este constructor
        CrearSolicitudRequest request = new CrearSolicitudRequest(
                1L,
                null, // Para que falle por descripción nula
                "WEB",
                "usuario-123"
        );

        // ... resto del código
    }
}