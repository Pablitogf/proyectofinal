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
                .descripcion("Solicitud de certificado")
                .solicitante(usuario)
                .build();

        // Si la solicitud se crea sin estado, el test falla
        assertNotNull(solicitud.getEstado());

        // Si el estado inicial no es CREADA, el test falla
        assertEquals(EstadoSolicitud.CREADA, solicitud.getEstado());
    }
}