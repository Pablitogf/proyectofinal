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
}