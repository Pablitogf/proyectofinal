package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodigoSolicitudTest {

    @Test
    void crearCodigoSolicitudValido() {

        CodigoSolicitud codigo = new CodigoSolicitud("SOL-2024-0001");

        // Si el valor guardado en el objeto no coincide con el código creado, el test falla
        assertEquals("SOL-2024-0001", codigo.valor());
    }

    @Test
    void noPermitirCodigoNulo() {

        // Si la clase permite crear un código con valor null, el test falla
        assertThrows(ReglaDominioException.class, () -> {
            new CodigoSolicitud(null);
        });
    }

    @Test
    void codigoDebeCumplirFormato() {

        // Si el código no cumple el formato esperado y aun así se crea, el test falla
        assertThrows(ReglaDominioException.class, () -> {
            new CodigoSolicitud("ABC-123");
        });
    }
}