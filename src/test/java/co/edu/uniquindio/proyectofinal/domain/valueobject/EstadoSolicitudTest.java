package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.entity.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoSolicitudTest {

    @Test
    void solicitudSiempreDebeCrearseConEstadoCreada() {

        Usuario usuario = new Usuario(
                "Pablo",
                new Email("pablo@uniquindio.edu.co"),
                TipoUser.ESTUDIANTE
        );

        Solicitud solicitud = new Solicitud.Builder()
                .codigo(new CodigoSolicitud("SOL-01"))
                .descripcion("Solicitud de certificado")
                .solicitante(usuario)
                .build();

        assertNotNull(solicitud.getEstado());
        assertEquals(EstadoSolicitud.CREADA, solicitud.getEstado());
    }
}