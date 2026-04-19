package co.edu.uniquindio.proyectofinal.aplication.domain.valueobject;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.CodigoSolicitud;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodigoSolicitudTest {

    @Test
    void crearCodigoSolicitudValido() {

        // Arrange: se crea un código con formato correcto
        CodigoSolicitud codigo = new CodigoSolicitud("SOL-0001");

        // Assert: se verifica que el valor guardado sea el mismo
        assertEquals("SOL-0001", codigo.valor());
    }

    @Test
    void noPermitirCodigoNulo() {

        // Verifica que no se pueda crear un código con valor null
        assertThrows(ReglaDominioException.class,
                () -> new CodigoSolicitud(null)
        );
    }

    @Test
    void noPermitirCodigoVacio() {

        // Verifica que no se permita un código vacío
        assertThrows(ReglaDominioException.class,
                () -> new CodigoSolicitud("")
        );
    }

    @Test
    void codigoDebeCumplirFormato() {

        // Verifica que el código cumpla el formato SOL-0001
        assertThrows(ReglaDominioException.class,
                () -> new CodigoSolicitud("ABC-123")
        );
    }
}