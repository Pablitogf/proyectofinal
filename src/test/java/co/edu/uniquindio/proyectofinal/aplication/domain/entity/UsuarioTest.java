package co.edu.uniquindio.proyectofinal.aplication.domain.entity;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Email;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    private Usuario crearUsuario() {
        return new Usuario(
                "Pablo",
                new Email("pablo@uniquindio.edu.co"),
                TipoUser.ESTUDIANTE
        );
    }

    @Test
    void crearUsuarioCorrectamente() {

        // Verifica que el usuario se cree con datos válidos
        Usuario usuario = crearUsuario();

        assertNotNull(usuario.getId());
        assertEquals("Pablo", usuario.getNombre());
        assertEquals(TipoUser.ESTUDIANTE, usuario.getRolUser());
    }

    @Test
    void noPermiteCrearUsuarioSinNombre() {

        // Verifica que el nombre sea obligatorio
        assertThrows(ReglaDominioException.class, () ->
                new Usuario(
                        "",
                        new Email("test@uniquindio.edu.co"),
                        TipoUser.ESTUDIANTE
                )
        );
    }

    @Test
    void cambiarNombreCorrectamente() {

        // Verifica que el nombre pueda actualizarse
        Usuario usuario = crearUsuario();

        usuario.cambiarNombre("Carlos");

        assertEquals("Carlos", usuario.getNombre());
    }

    @Test
    void noPermiteCambiarNombreVacio() {

        // Verifica validación del nombre
        Usuario usuario = crearUsuario();

        assertThrows(ReglaDominioException.class, () ->
                usuario.cambiarNombre("")
        );
    }

    @Test
    void cambiarEmailCorrectamente() {

        // Verifica que el email pueda cambiarse
        Usuario usuario = crearUsuario();

        Email nuevoEmail = new Email("nuevo@uniquindio.edu.co");

        usuario.cambiarEmail(nuevoEmail);

        assertEquals(nuevoEmail, usuario.getEmail());
    }

    @Test
    void noPermiteCambiarEmailNulo() {

        // Verifica validación de email
        Usuario usuario = crearUsuario();

        assertThrows(ReglaDominioException.class, () ->
                usuario.cambiarEmail(null)
        );
    }

    @Test
    void cambiarRolCorrectamente() {

        // Verifica que el rol pueda actualizarse
        Usuario usuario = crearUsuario();

        usuario.cambiarRol(TipoUser.DOCENTE);

        assertEquals(TipoUser.DOCENTE, usuario.getRolUser());
    }

    @Test
    void noPermiteCambiarRolNulo() {

        // Verifica que el rol no sea nulo
        Usuario usuario = crearUsuario();

        assertThrows(ReglaDominioException.class, () ->
                usuario.cambiarRol(null)
        );
    }
}