package co.edu.uniquindio.proyectofinal.domain.entity;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.Email;
import co.edu.uniquindio.proyectofinal.domain.valueobject.TipoUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void CrearUsuario() {
        Email email = new Email("test@uniquindio.edu.co");

        Usuario usuario = new Usuario("Pablo", email, TipoUser.COORDINADOR);

        assertEquals("Pablo", usuario.getNombre());
        assertEquals(email, usuario.getEmail());
        assertEquals(TipoUser.COORDINADOR, usuario.getRolUser());
        assertNotNull(usuario.getId());
    }

    @Test
    void noPermiteNombreVacio() {
        Email email = new Email("test@uniquindio.edu.co");

        assertThrows(ReglaDominioException.class, () -> {
            new Usuario("", email, TipoUser.ESTUDIANTE);
        });
    }

    @Test
    void CambiarEmail() {
        Usuario usuario = new Usuario(
                "Pablo",
                new Email("pablo@uniquindio.edu.co"),
                TipoUser.ESTUDIANTE
        );

        Email nuevoEmail = new Email("nuevo@uniquindio.edu.co");
        usuario.cambiarEmail(nuevoEmail);

        assertEquals(nuevoEmail, usuario.getEmail());
    }
}