package co.edu.uniquindio.proyectofinal.domain.entity;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.Email;
import co.edu.uniquindio.proyectofinal.domain.valueobject.TipoUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void crearUsuario() {

        Email email = new Email("test@uniquindio.edu.co");

        Usuario usuario = new Usuario("Pablo", email, TipoUser.COORDINADOR);

        // Si se cambia o se quita el nombre "Pablo", el test falla
        assertEquals("Pablo", usuario.getNombre());

        // Si el email del usuario no es igual al creado, el test falla
        assertEquals(email, usuario.getEmail());

        // Si el rol no es COORDINADOR el test falla
        assertEquals(TipoUser.COORDINADOR, usuario.getRolUser());

        // Si el usuario no genera un id automáticamente, el test falla
        assertNotNull(usuario.getId());
    }

    @Test
    void noPermiteNombreVacio() {

        Email email = new Email("test@uniquindio.edu.co");

        // Si el constructor no lanza la excepción cuando el nombre está vacío, el test falla
        assertThrows(ReglaDominioException.class, () -> {
            new Usuario("", email, TipoUser.ESTUDIANTE);
        });
    }

    @Test
    void cambiarEmail() {

        Usuario usuario = new Usuario(
                "Pablo",
                new Email("pablo@uniquindio.edu.co"),
                TipoUser.ESTUDIANTE
        );

        Email nuevoEmail = new Email("nuevo@uniquindio.edu.co");

        usuario.cambiarEmail(nuevoEmail);

        // Si el email no se actualiza correctamente, el test falla
        assertEquals(nuevoEmail, usuario.getEmail());
    }
}