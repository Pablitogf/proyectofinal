package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodigoSolicitudTest {

    @Test
    void crearCodigoSolicitudValido() {
        CodigoSolicitud codigo = new CodigoSolicitud("SOL-2024-0001");

        assertEquals("SOL-2024-0001", codigo.valor());
    }

    @Test
    void noPermitirCodigoNulo() {
        assertThrows(ReglaDominioException.class, () -> {
            new CodigoSolicitud(null);
        });
    }

    @Test
    void codigoDebeCumplirFormato() {
        assertThrows(ReglaDominioException.class, () -> {
            new CodigoSolicitud("ABC-123");
        });
    }
}