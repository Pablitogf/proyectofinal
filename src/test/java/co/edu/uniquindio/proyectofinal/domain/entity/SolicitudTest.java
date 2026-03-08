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

    private Usuario crearCoordinador() {
        return new Usuario(
                "Coordinador",
                new Email("coord@uniquindio.edu.co"),
                TipoUser.COORDINADOR
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

        Solicitud solicitud = crearSolicitud();

        assertNotNull(solicitud.getCodigo());
        assertEquals(EstadoSolicitud.CREADA, solicitud.getEstado());
        assertEquals("Problema con matrícula", solicitud.getDescripcion());
    }

    @Test
    void noPermiteCrearSolicitudSinDescripcion() {

        assertThrows(ReglaDominioException.class, () ->
                new Solicitud.Builder()
                        .solicitante(crearUsuario())
                        .build()
        );
    }

    @Test
    void noPermiteCrearSolicitudSinSolicitante() {

        assertThrows(ReglaDominioException.class, () ->
                new Solicitud.Builder()
                        .descripcion("Test")
                        .build()
        );
    }

    @Test
    void clasificarSolicitudCorrectamente() {

        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearCoordinador();

        solicitud.clasificar(
                Prioridad.MEDIA,
                TipoSolicitud.CERTIFICADO,
                coordinador
        );

        assertEquals(EstadoSolicitud.CLASIFICADA, solicitud.getEstado());
        assertEquals(Prioridad.MEDIA, solicitud.getPrioridad());
        assertEquals(TipoSolicitud.CERTIFICADO, solicitud.getTipo());
    }

    @Test
    void noPermiteClasificarSinPrioridad() {

        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearCoordinador();

        assertThrows(ReglaDominioException.class, () ->
                solicitud.clasificar(null, TipoSolicitud.CERTIFICADO, coordinador)
        );
    }

    @Test
    void noPermiteClasificarSinTipo() {

        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearCoordinador();

        assertThrows(ReglaDominioException.class, () ->
                solicitud.clasificar(Prioridad.ALTA, null, coordinador)
        );
    }

    @Test
    void asignarResponsableCorrectamente() {

        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearCoordinador();

        Usuario responsable = new Usuario(
                "Carlos",
                new Email("carlos@uniquindio.edu.co"),
                TipoUser.DOCENTE
        );

        solicitud.clasificar(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, coordinador);
        solicitud.asignarResponsable(responsable, coordinador);

        assertEquals(EstadoSolicitud.ASIGNADA, solicitud.getEstado());
        assertEquals(responsable, solicitud.getResponsable());
    }

    @Test
    void noPermiteAsignarResponsableNulo() {

        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearCoordinador();

        solicitud.clasificar(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, coordinador);

        assertThrows(ReglaDominioException.class, () ->
                solicitud.asignarResponsable(null, coordinador)
        );
    }

    @Test
    void priorizarSolicitudCorrectamente() {

        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearCoordinador();

        solicitud.priorizar(Prioridad.CRITICA, coordinador);

        assertEquals(Prioridad.CRITICA, solicitud.getPrioridad());
    }

    @Test
    void debeCrearSolicitudEnEstadoCreada() {

        Solicitud solicitud = crearSolicitud();

        assertEquals(EstadoSolicitud.CREADA, solicitud.getEstado());
        assertNotNull(solicitud.getFechaCreacion());
    }

    @Test
    void debeIniciarAtencionCorrectamente() {

        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearCoordinador();
        Usuario responsable = crearUsuario();

        solicitud.clasificar(Prioridad.ALTA, TipoSolicitud.SOPORTE, coordinador);
        solicitud.asignarResponsable(responsable, coordinador);

        solicitud.iniciarAtencion(responsable);

        assertEquals(EstadoSolicitud.EN_ATENCION, solicitud.getEstado());
    }

    @Test
    void noDebePermitirCerrarSiNoEstaAtendida() {

        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearCoordinador();

        assertThrows(ReglaDominioException.class, () -> {
            solicitud.cerrar(coordinador);
        });
    }

    @Test
    void debePasarPorTodoElCicloDeVidaYRegistrarHistorial() {

        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearCoordinador();
        Usuario responsable = crearUsuario();

        solicitud.clasificar(Prioridad.ALTA, TipoSolicitud.SOPORTE, coordinador);
        assertEquals(EstadoSolicitud.CLASIFICADA, solicitud.getEstado());

        solicitud.asignarResponsable(responsable, coordinador);
        assertEquals(EstadoSolicitud.ASIGNADA, solicitud.getEstado());

        solicitud.iniciarAtencion(responsable);
        assertEquals(EstadoSolicitud.EN_ATENCION, solicitud.getEstado());

        solicitud.finalizarAtencion(responsable);
        assertEquals(EstadoSolicitud.ATENDIDA, solicitud.getEstado());

        solicitud.cerrar(coordinador);
        assertEquals(EstadoSolicitud.CERRADA, solicitud.getEstado());

        assertEquals(6, solicitud.getHistorial().size());
    }
}