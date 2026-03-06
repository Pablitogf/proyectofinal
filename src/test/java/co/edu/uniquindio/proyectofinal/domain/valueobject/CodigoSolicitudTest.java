package co.edu.uniquindio.proyectofinal.domain.valueobject;

import co.edu.uniquindio.solicitudes.domain.exception.ReglaDominioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CodigoSolicitudTest {

    @Test
    void debeCrearCodigoValido() {
        CodigoSolicitud codigo = new CodigoSolicitud("SOL-2024-0123");
        assertEquals("SOL-2024-0123", codigo.valor());
    }

    @Test
    void codigosConMismoValorDebenSerIguales() {
        CodigoSolicitud c1 = new CodigoSolicitud("SOL-2024-0123");
        CodigoSolicitud c2 = new CodigoSolicitud("SOL-2024-0123");

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "SOL-2024-ABC",      // Formato incorrecto (letras en ultima parte)
            "SOL-24-0123",       // Año incompleto
            "SOL20240123",       // Sin guiones
            "SOL-2024-012",      // Solo 3 digitos finales
            "SOL-2024-01234",    // 5 digitos finales
            "S-2024-0123",       // Prefijo incorrecto
            "SOL-ABCD-0123",     // Año no numerico
            "sol-2024-0123"      // Minusculas
    })
    void noDebeCrearCodigoInvalido(String codigoInvalido) {
        ReglaDominioException exception = assertThrows(
                ReglaDominioException.class,
                () -> new CodigoSolicitud(codigoInvalido)
        );

        assertEquals("El formato del código de solicitud es inválido. Debe ser SOL-YYYY-XXXX",
                exception.getMessage());
    }

    @Test
    void noDebeAceptarCodigoNulo() {
        assertThrows(ReglaDominioException.class, () -> new CodigoSolicitud(null));
    }

    @Test
    void noDebeAceptarCodigoVacio() {
        assertThrows(ReglaDominioException.class, () -> new CodigoSolicitud(""));
    }

    // Prueba adicional 1: Año valido (rango)
    @Test
    void debeAceptarAniosValidos() {
        CodigoSolicitud codigo = new CodigoSolicitud("SOL-2023-0123");
        assertEquals("SOL-2023-0123", codigo.valor());
    }

    // Prueba adicional 2: Numeros finales en rango correcto
    @Test
    void debeAceptarRangoCompletoDeNumerosFinales() {
        CodigoSolicitud codigo = new CodigoSolicitud("SOL-2024-9999");
        assertEquals("SOL-2024-9999", codigo.valor());
    }
}