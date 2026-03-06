package co.edu.uniquindio.proyectofinal.domain.entity;


import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import co.edu.uniquindio.proyectofinal.domain.valueobject.Email;

import java.util.Objects;
import java.util.UUID;

/**
 * Entidad que representa un Usuario del sistema (Estudiante, Coordinador, Docente).
 */
public class Usuario {
    private final String id; // Identidad única
    private String nombre;
    private Email email;
    private String rol; // Ej: "ESTUDIANTE", "COORDINADOR", "DOCENTE"

    public Usuario(String nombre, Email email, String rol) {
        this.id = UUID.randomUUID().toString(); // Generación simple de ID
        setNombre(nombre);
        setEmail(email);
        setRol(rol);
    }

    // Constructor para reconstruir desde BD (con ID existente)
    public Usuario(String id, String nombre, Email email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    // Getters (solo lectura del estado)
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Email getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
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

    public void cambiarRol(String nuevoRol) {
        if (nuevoRol == null || nuevoRol.isBlank()) {
            throw new ReglaDominioException("El rol no puede ser nulo o vacío");
        }
        // Podría validarse contra una lista de roles válidos
        this.rol = nuevoRol;
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

    private void setRol(String rol) {
        if (rol == null || rol.isBlank()) {
            throw new ReglaDominioException("El rol no puede ser nulo o vacío");
        }
        this.rol = rol;
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