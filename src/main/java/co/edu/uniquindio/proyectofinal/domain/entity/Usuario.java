package co.edu.uniquindio.proyectofinal.domain.entity;


import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.Email;
import co.edu.uniquindio.proyectofinal.domain.valueobject.TipoUser;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

/**
 * Entidad que representa un Usuario del sistema (Estudiante, Coordinador, Docente).
 */
@Getter
public class Usuario {
    private final String id; // Identidad única
    private String nombre;
    private Email email;
    private TipoUser rolUser;

    public Usuario(String nombre, Email email, TipoUser rolUser) {
        this.id = UUID.randomUUID().toString(); // Generación simple de ID
        setNombre(nombre);
        setEmail(email);
        setRol(rolUser);
    }

    // Métodos de negocio para cambiar el estado (con validaciones)
    public void cambiarNombre(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new ReglaDominioException("El nombre no puede ser nulo o vacío");
        }
        this.nombre = nuevoNombre;
    }

    public void cambiarEmail(Email nuevoEmail) {
        if (nuevoEmail == null) {
            throw new ReglaDominioException("El email no puede ser nulo");
        }
        this.email = nuevoEmail;
    }

    public void cambiarRol(TipoUser nuevoRol) {
        if (nuevoRol == null) {
            throw new ReglaDominioException("El rol no puede ser nulo");
        }

        this.rolUser = nuevoRol;
    }

    // Métodos helper para validación en constructor
    private void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ReglaDominioException("El nombre no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }

    private void setEmail(Email email) {
        if (email == null) {
            throw new ReglaDominioException("El email no puede ser nulo");
        }
        this.email = email;
    }

    private void setRol(TipoUser rolUser) {
        if (rolUser == null) {
            throw new ReglaDominioException("El rol no puede ser nulo o vacío");
        }
        this.rolUser = rolUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}