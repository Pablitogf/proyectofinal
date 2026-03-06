package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.solicitudes.domain.exception.ReglaDominioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @Test
    void emailsConMismoValorDebenSerIguales() {
        Email e1 = new Email("usuario@uniquindio.edu.co");
        Email e2 = new Email("usuario@uniquindio.edu.co");

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "correo-invalido",
            "@faltausuario.com",
            "usuario@.com",
            "usuario@dominio",
            "usuario@dominio.",
            "espacio@dominio .com",
            "usuario@dominio..com",
            "usuario@dominio.c"
    })
    void noDebeCrearEmailInvalido(String emailInvalido) {
        ReglaDominioException exception = assertThrows(
                ReglaDominioException.class,
                () -> new Email(emailInvalido)
        );

        assertEquals("El formato del email no es válido", exception.getMessage());
    }

    @Test
    void noDebeAceptarEmailNulo() {
        assertThrows(ReglaDominioException.class, () -> new Email(null));
    }

    @Test
    void noDebeAceptarEmailVacio() {
        assertThrows(ReglaDominioException.class, () -> new Email(""));
    }

    @Test
    void noDebeAceptarEmailConSoloEspacios() {
        assertThrows(ReglaDominioException.class, () -> new Email("   "));
    }

    // Prueba adicional 1: Email con subdominios
    @Test
    void debeAceptarEmailConSubdominios() {
        Email email = new Email("usuario@estudiante.uniquindio.edu.co");
        assertEquals("usuario@estudiante.uniquindio.edu.co", email.direccion());
    }

    // Prueba adicional 2: Email con caracteres especiales permitidos
    @Test
    void debeAceptarEmailConCaracteresEspecialesValidos() {
        Email email = new Email("usuario.nombre+etiqueta@dominio.com");
        assertEquals("usuario.nombre+etiqueta@dominio.com", email.direccion());
    }
}