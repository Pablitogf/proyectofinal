package co.edu.uniquindio.proyectofinal.domain.entity;

import co.edu.uniquindio.solicitudes.domain.exception.ReglaDominioException;
import co.edu.uniquindio.solicitudes.domain.valueobject.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void debeCrearUsuarioCorrectamente() {
        // Arrange
        Email email = new Email("juan@uniquindio.edu.co");

        // Act
        Usuario usuario = new Usuario("Juan Perez", email, "ESTUDIANTE");

        // Assert
        assertNotNull(usuario.getId());
        assertEquals("Juan Perez", usuario.getNombre());
        assertEquals(email, usuario.getEmail());
        assertEquals("ESTUDIANTE", usuario.getRol());
    }

    @Test
    void debeAsignarNombreCorrectamente() {
        // Arrange
        Usuario usuario = new Usuario("Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");

        // Act
        usuario.cambiarNombre("Juan Carlos Perez");

        // Assert
        assertEquals("Juan Carlos Perez", usuario.getNombre());
    }

    @Test
    void noDebePermitirNombreNulo() {
        // Arrange
        Usuario usuario = new Usuario("Juan", new Email("juan@email.com"), "ESTUDIANTE");

        // Act & Assert
        ReglaDominioException exception = assertThrows(ReglaDominioException.class,
                () -> usuario.cambiarNombre(null));

        assertEquals("El nombre no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    void noDebePermitirNombreVacio() {
        // Arrange
        Usuario usuario = new Usuario("Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");

        // Act & Assert
        assertThrows(ReglaDominioException.class,
                () -> usuario.cambiarNombre(""));
    }

    @Test
    void noDebePermitirNombreConEspacios() {
        // Arrange
        Usuario usuario = new Usuario("Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");

        // Act & Assert
        assertThrows(ReglaDominioException.class,
                () -> usuario.cambiarNombre("   "));
    }

    @Test
    void debeCambiarEmailCorrectamente() {
        // Arrange
        Usuario usuario = new Usuario("Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");
        Email nuevoEmail = new Email("juan.perez@uniquindio.edu.co");

        // Act
        usuario.cambiarEmail(nuevoEmail);

        // Assert
        assertEquals(nuevoEmail, usuario.getEmail());
    }

    @Test
    void noDebePermitirEmailNulo() {
        // Arrange
        Usuario usuario = new Usuario("Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");

        // Act & Assert
        ReglaDominioException exception = assertThrows(ReglaDominioException.class,
                () -> usuario.cambiarEmail(null));

        assertEquals("El email no puede ser nulo", exception.getMessage());
    }

    @Test
    void debeCambiarRolCorrectamente() {
        // Arrange
        Usuario usuario = new Usuario("Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");

        // Act
        usuario.cambiarRol("DOCENTE");

        // Assert
        assertEquals("DOCENTE", usuario.getRol());
    }

    @Test
    void noDebePermitirRolNulo() {
        // Arrange
        Usuario usuario = new Usuario("Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");

        // Act & Assert
        ReglaDominioException exception = assertThrows(ReglaDominioException.class,
                () -> usuario.cambiarRol(null));

        assertEquals("El rol no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    void noDebePermitirRolVacio() {
        // Arrange
        Usuario usuario = new Usuario("Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");

        // Act & Assert
        assertThrows(ReglaDominioException.class,
                () -> usuario.cambiarRol(""));
    }

    @Test
    void dosUsuariosConMismoIdDebenSerIguales() {
        // Arrange
        Usuario usuario1 = new Usuario("123", "Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");
        Usuario usuario2 = new Usuario("123", "Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");

        // Assert
        assertEquals(usuario1, usuario2);
        assertEquals(usuario1.hashCode(), usuario2.hashCode());
    }

    @Test
    void usuariosConDiferenteIdDebenSerDistintos() {
        // Arrange
        Usuario usuario1 = new Usuario("123", "Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");
        Usuario usuario2 = new Usuario("456", "Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");

        // Assert
        assertNotEquals(usuario1, usuario2);
        assertNotEquals(usuario1.hashCode(), usuario2.hashCode());
    }

    @Test
    void constructorNoDebePermitirNombreNulo() {
        // Arrange
        Email email = new Email("juan@email.com");

        // Act & Assert
        assertThrows(ReglaDominioException.class,
                () -> new Usuario(null, email, "ESTUDIANTE"));
    }

    @Test
    void constructorNoDebePermitirEmailNulo() {
        // Act & Assert
        assertThrows(ReglaDominioException.class,
                () -> new Usuario("Juan Perez", null, "ESTUDIANTE"));
    }

    @Test
    void constructorNoDebePermitirRolNulo() {
        // Arrange
        Email email = new Email("juan@email.com");

        // Act & Assert
        assertThrows(ReglaDominioException.class,
                () -> new Usuario("Juan Perez", email, null));
    }

    // Prueba adicional 1: Actualizar multiples campos
    @Test
    void debeActualizarMultiplesCamposSecuencialmente() {
        // Arrange
        Usuario usuario = new Usuario("Juan Perez", new Email("juan@email.com"), "ESTUDIANTE");
        Email nuevoEmail = new Email("juan.c@uniquindio.edu.co");

        // Act
        usuario.cambiarNombre("Juan C. Perez");
        usuario.cambiarEmail(nuevoEmail);
        usuario.cambiarRol("DOCENTE");

        // Assert
        assertEquals("Juan C. Perez", usuario.getNombre());
        assertEquals(nuevoEmail, usuario.getEmail());
        assertEquals("DOCENTE", usuario.getRol());
    }

    // Prueba adicional 2: Verificar estado inicial del constructor con ID
    @Test
    void constructorConIdDebeMantenerIdExistente() {
        // Arrange
        String idExistente = "USR-001";

        // Act
        Usuario usuario = new Usuario(idExistente, "Juan Perez",
                new Email("juan@email.com"), "ESTUDIANTE");

        // Assert
        assertEquals(idExistente, usuario.getId());
    }
}