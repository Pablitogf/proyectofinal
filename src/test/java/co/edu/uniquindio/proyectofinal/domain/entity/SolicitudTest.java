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
                .codigo(new CodigoSolicitud("SOL-001"))
                .descripcion("Problema con matrícula")
                .solicitante(crearUsuario())
                .build();
    }

    @Test
    void crearSolicitudConDatosValidos() {
        Solicitud solicitud = crearSolicitud();

        assertNotNull(solicitud.getId());
        assertEquals(EstadoSolicitud.CREADA, solicitud.getEstado());
        assertEquals("Problema con matrícula", solicitud.getDescripcion());
    }

    @Test
    void noPermiteSolicitudesSinCodigo() {
        assertThrows(ReglaDominioException.class, () -> {
            new Solicitud.Builder()
                    .descripcion("Error sistema")
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

        assertEquals(EstadoSolicitud.ASIGNADA, solicitud.getEstado());
        assertEquals(responsable, solicitud.getResponsable());
    }
}