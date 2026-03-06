package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void crearEmailValido() {
        Email email = new Email("pablo@uniquindio.edu.co");

        assertEquals("pablo@uniquindio.edu.co", email.direccion());
    }

    @Test
    void noPermitirEmailNulo() {
        assertThrows(ReglaDominioException.class, () -> {
            new Email(null);
        });
    }

    @Test
    void emailDebeContenerArroba() {
        assertThrows(ReglaDominioException.class, () -> {
            new Email("pablouniquindio.edu.co");
        });
    }
}