package co.edu.uniquindio.proyectofinal.domain.entity;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudTest {

    private Usuario crearUsuario() {
        return new Usuario(
                "Pablo",
                new Email("pablo@uniquindio.edu.co"),
                TipoUser.ESTUDIANTE
        );
    }

    private Solicitud crearSolicitud() {
        return new Solicitud.Builder()
                .descripcion("Problema con matrícula")
                .solicitante(crearUsuario())
                .build();
    }

    @Test
    void crearSolicitudCorrectamente() {

        // Verifica que una solicitud se cree con datos válidos
        Solicitud solicitud = crearSolicitud();

        assertNotNull(solicitud.getId());
        assertNotNull(solicitud.getCodigo());
        assertEquals(EstadoSolicitud.CREADA, solicitud.getEstado());
        assertEquals("Problema con matrícula", solicitud.getDescripcion());
    }

    @Test
    void noPermiteCrearSolicitudSinDescripcion() {

        // Verifica que no se pueda crear sin descripción
        assertThrows(ReglaDominioException.class, () ->
                new Solicitud.Builder()
                        .solicitante(crearUsuario())
                        .build()
        );
    }



    @Test
    void noPermiteCrearSolicitudSinSolicitante() {

        // Verifica que el solicitante sea obligatorio
        assertThrows(ReglaDominioException.class, () ->
                new Solicitud.Builder()
                        .descripcion("Test")
                        .build()
        );
    }

    @Test
    void clasificarSolicitudCorrectamente() {

        // Verifica que clasificar cambie estado, prioridad y tipo
        Solicitud solicitud = crearSolicitud();

        solicitud.clasificar(
                Prioridad.MEDIA,
                TipoSolicitud.CERTIFICADO,
                "coordinador"
        );

        assertEquals(EstadoSolicitud.CLASIFICADA, solicitud.getEstado());
        assertEquals(Prioridad.MEDIA, solicitud.getPrioridad());
        assertEquals(TipoSolicitud.CERTIFICADO, solicitud.getTipo());
    }

    @Test
    void noPermiteClasificarSinPrioridad() {

        // Verifica que la prioridad sea obligatoria
        Solicitud solicitud = crearSolicitud();

        assertThrows(ReglaDominioException.class, () ->
                solicitud.clasificar(null, TipoSolicitud.CERTIFICADO, "coord")
        );
    }

    @Test
    void noPermiteClasificarSinTipo() {

        // Verifica que el tipo sea obligatorio
        Solicitud solicitud = crearSolicitud();

        assertThrows(ReglaDominioException.class, () ->
                solicitud.clasificar(Prioridad.ALTA, null, "coord")
        );
    }

    @Test
    void asignarResponsableCorrectamente() {

        // Verifica que asignar responsable cambie el estado
        Solicitud solicitud = crearSolicitud();

        Usuario responsable = new Usuario(
                "Carlos",
                new Email("carlos@uniquindio.edu.co"),
                TipoUser.DOCENTE
        );

        solicitud.clasificar(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, "coord");
        solicitud.asignarResponsable(responsable, "coord");

        assertEquals(EstadoSolicitud.ASIGNADA, solicitud.getEstado());
        assertEquals(responsable, solicitud.getResponsable());
    }

    @Test
    void noPermiteAsignarResponsableNulo() {

        // Verifica que el responsable no pueda ser nulo
        Solicitud solicitud = crearSolicitud();

        solicitud.clasificar(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, "coord");

        assertThrows(ReglaDominioException.class, () ->
                solicitud.asignarResponsable(null, "coord")
        );
    }

    @Test
    void priorizarSolicitudCorrectamente() {

        // Verifica que una solicitud pueda cambiar su prioridad correctamente
        Solicitud solicitud = crearSolicitud();

        solicitud.priorizar(Prioridad.CRITICA, "coordinador");

        assertEquals(Prioridad.CRITICA, solicitud.getPrioridad());
    }

    @Test
    void debeCrearSolicitudEnEstadoCreada() {
        // Arrange & Act
        Solicitud solicitud = crearSolicitud();

        // Assert
        assertEquals(EstadoSolicitud.CREADA, solicitud.getEstado());
        assertNotNull(solicitud.getFechaCreacion());
    }

    @Test
    void debeClasificarSolicitudCorrectamente() {
        // Arrange
        Solicitud solicitud = crearSolicitud();

        // Act
        solicitud.clasificar(Prioridad.ALTA, TipoSolicitud.SOPORTE_TECNICO, "ADMIN_01");

        // Assert
        assertEquals(EstadoSolicitud.CLASIFICADA, solicitud.getEstado());
    }

    @Test
    void debeAsignarResponsableCorrectamente() {
        // Arrange
        Solicitud solicitud = crearSolicitud();
        Usuario responsable = crearUsuario();
        solicitud.clasificar(Prioridad.ALTA, TipoSolicitud.SOPORTE_TECNICO, "ADMIN_01");

        // Act
        solicitud.asignarResponsable(responsable, "ADMIN_01");

        // Assert
        assertEquals(EstadoSolicitud.ASIGNADA, solicitud.getEstado());
    }

    @Test
    void debeIniciarAtencionCorrectamente() {
        // Arrange
        Solicitud solicitud = crearSolicitud();
        Usuario responsable = crearUsuario();
        solicitud.clasificar(Prioridad.ALTA, TipoSolicitud.SOPORTE_TECNICO, "ADMIN_01");
        solicitud.asignarResponsable(responsable, "ADMIN_01");

        // Act
        solicitud.iniciarAtencion("Pablo");

        // Assert
        assertEquals(EstadoSolicitud.EN_ATENCION, solicitud.getEstado());
    }

    @Test
    void noDebePermitirCerrarSiNoEstaAtendida() {
        // RF-04: Prueba de Invariante
        Solicitud solicitud = crearSolicitud();

        // Intentar cerrar directamente desde CREADA debe fallar
        assertThrows(ReglaDominioException.class, () -> {
            solicitud.cerrar("ADMIN_01");
        });
    }

    @Test
    void debePasarPorTodoElCicloDeVidaYRegistrarHistorial() {
        // 1. Arrange: Usamos tus métodos de referencia
        Solicitud solicitud = crearSolicitud();
        Usuario responsable = crearUsuario();

        // 2. Act & Assert: Paso a paso siguiendo tus métodos de Solicitud

        // Clasificar: necesita (Prioridad, TipoSolicitud, String usuario)
        solicitud.clasificar(Prioridad.ALTA, TipoSolicitud.SOPORTE_TECNICO, "ADMIN_01");
        assertEquals(EstadoSolicitud.CLASIFICADA, solicitud.getEstado());

        // Asignar: necesita (Usuario responsable, String usuarioActuante)
        solicitud.asignarResponsable(responsable, "ADMIN_01");
        assertEquals(EstadoSolicitud.ASIGNADA, solicitud.getEstado());

        // Iniciar Atención: necesita (String usuario)
        solicitud.iniciarAtencion("Pablo");
        assertEquals(EstadoSolicitud.EN_ATENCION, solicitud.getEstado());

        // Finalizar Atención: necesita (String usuario)
        solicitud.finalizarAtencion("Pablo");
        assertEquals(EstadoSolicitud.ATENDIDA, solicitud.getEstado());

        // Cerrar: necesita (String usuario)
        solicitud.cerrar("ADMIN_01");
        assertEquals(EstadoSolicitud.CERRADA, solicitud.getEstado());

        // RF-06: Verificamos historial (1 inicial + 5 pasos = 6)
        assertEquals(6, solicitud.getHistorial().size());
    }

    @Test
    void noDebePermitirCerrarSiNoEstaAtendida() {
        // Invariante: No se puede cerrar si está en CREADA
        Solicitud solicitud = crearSolicitud();

        assertThrows(ReglaDominioException.class, () -> {
            solicitud.cerrar("ADMIN_01");
        });
    }
}