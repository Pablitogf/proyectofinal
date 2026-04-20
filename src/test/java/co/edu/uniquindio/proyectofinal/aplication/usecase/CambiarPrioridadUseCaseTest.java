package co.edu.uniquindio.proyectofinal.aplication.usecase;

import co.edu.uniquindio.proyectofinal.application.usecase.CambiarPrioridadUseCase;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CambiarPrioridadUseCaseTest {

    @Mock
    private SolicitudRepositorio repository;

    @InjectMocks
    private CambiarPrioridadUseCase useCase;

    @Test
    void debeCambiarPrioridadExitosamente() {
        // 1. Arrange (Preparar datos de dominio) [cite: 239]
        String solicitudId = "SOL-001";
        Prioridad nuevaPrioridad = Prioridad.ALTA;
        
        Usuario coordinador = new Usuario(
                "ID-COORD", 
                new Email("coord@uniquindio.edu.co"), 
                TipoUser.COORDINADOR
        );

        // Crear una solicitud real mediante su lógica de dominio
        Solicitud solicitudMock = Solicitud.Builder.registrar(
                "Descripción de prueba con longitud suficiente", 
                new Usuario("ID-USER", new Email("user@test.com"), TipoUser.ESTUDIANTE)
        );

        // Configurar comportamiento de los mocks [cite: 240, 241]
        when(repository.buscarPorId(solicitudId)).thenReturn(Optional.of(solicitudMock));
        when(repository.guardar(any(Solicitud.class))).thenAnswer(i -> i.getArguments()[0]);

        // 2. Act (Ejecución)
        Solicitud resultado = useCase.ejecutar(solicitudId, nuevaPrioridad, coordinador);

        // 3. Assert (Verificaciones) [cite: 238]
        assertNotNull(resultado);
        assertEquals(nuevaPrioridad, resultado.getPrioridad());
        
        // Verificar orquestación: primero busca, luego guarda [cite: 241, 245]
        verify(repository).buscarPorId(solicitudId);
        verify(repository).guardar(solicitudMock);
    }

    @Test
    void debeLanzarExcepcionCuandoSolicitudNoExiste() {
        // Arrange
        String idNoExistente = "999";
        Prioridad prioridad = Prioridad.MEDIA;
        Usuario coordinador = new Usuario("ID", new Email("a@a.com"), TipoUser.COORDINADOR);

        when(repository.buscarPorId(idNoExistente)).thenReturn(Optional.empty());

        // Act & Assert [cite: 242]
        assertThrows(RuntimeException.class, () -> {
            useCase.ejecutar(idNoExistente, prioridad, coordinador);
        });

        // Verificar que NUNCA se intentó guardar si falló la búsqueda [cite: 243]
        verify(repository, never()).guardar(any());
    }
}