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
    void crearSolicitudConDatosValidos() {

        Solicitud solicitud = crearSolicitud();

        // Si la solicitud no genera un ID automáticamente, el test falla
        assertNotNull(solicitud.getId());

        // Si el estado inicial no es CREADA, el test falla
        assertEquals(EstadoSolicitud.CREADA, solicitud.getEstado());

        // Si se cambia o elimina la descripción, el test falla
        assertEquals("Problema con matrícula", solicitud.getDescripcion());

        // Si el código automático no se genera, el test falla
        assertNotNull(solicitud.getCodigo());
    }

    @Test
    void noPermiteSolicitudesSinDescripcion() {

        // Si el Builder permite crear una solicitud sin descripción, el test falla
        assertThrows(ReglaDominioException.class, () -> {
            new Solicitud.Builder()
                    .solicitante(crearUsuario())
                    .build();
        });
    }

    @Test
    void asignarResponsableCambiaEstado() {

        Solicitud solicitud = crearSolicitud();

        Usuario responsable = new Usuario(
                "Carlos",
                new Email("carlos@uniquindio.edu.co"),
                TipoUser.DOCENTE
        );

        solicitud.clasificar(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, "coord");
        solicitud.asignarResponsable(responsable, "coord");

        // Si el estado no cambia a ASIGNADA después de asignar responsable, el test falla
        assertEquals(EstadoSolicitud.ASIGNADA, solicitud.getEstado());

        // Si el responsable no queda asignado correctamente, el test falla
        assertEquals(responsable, solicitud.getResponsable());
    }
}