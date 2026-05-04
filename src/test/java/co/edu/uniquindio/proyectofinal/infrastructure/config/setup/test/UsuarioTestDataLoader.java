package co.edu.uniquindio.proyectofinal.infrastructure.config.setup.test;

import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.UsuarioEntity;

import java.util.UUID;

public class UsuarioTestDataLoader {

    public static UsuarioEntity usuarioAdminBase() {
        UsuarioEntity admin = new UsuarioEntity();
        admin.setId(UUID.randomUUID().toString());
        admin.setNombre("Admin Test");
        admin.setEmail("admin@test.com");
        admin.setRolUser("ADMIN");
        return admin;
    }

    public static UsuarioEntity usuarioUserBase() {
        UsuarioEntity user = new UsuarioEntity();
        user.setId(UUID.randomUUID().toString());
        user.setNombre("User Test");
        user.setEmail("user@test.com");
        user.setRolUser("USER");
        return user;
    }
}