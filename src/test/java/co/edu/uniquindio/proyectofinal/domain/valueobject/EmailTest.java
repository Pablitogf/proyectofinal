package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void crearEmailValido() {

        Email email = new Email("pablo@uniquindio.edu.co");

        // Si el email guardado en el objeto no coincide con el creado, el test falla
        assertEquals("pablo@uniquindio.edu.co", email.direccion());
    }

    @Test
    void noPermitirEmailNulo() {

        // Si la clase permite crear un email con valor null, el test falla
        assertThrows(ReglaDominioException.class, () -> {
            new Email(null);
        });
    }

    @Test
    void emailDebeContenerArroba() {

        // Si el email no contiene '@' y aun así se crea el objeto, el test falla
        assertThrows(ReglaDominioException.class, () -> {
            new Email("pablouniquindio.edu.co");
        });
    }
}