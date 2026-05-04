package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.repositories;

import co.edu.uniquindio.proyectofinal.infrastructure.config.setup.test.UsuarioTestDataLoader;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.UsuarioEntity;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.repository.UsuarioJpaDataRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioJpaDataRepository usuarioRepository;

    private UsuarioEntity testAdmin;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
        testAdmin = UsuarioTestDataLoader.usuarioAdminBase();
        entityManager.persistAndFlush(testAdmin);
    }

    @Test
    void testBusquedaPorCorreoExitosa() {
        var optUser = usuarioRepository.findByEmail("admin@test.com");

        assertTrue(optUser.isPresent());
        assertEquals("ADMIN", optUser.get().getRolUser());
    }

    @Test
    void testBusquedaPorCorreoInexistente() {
        var optUser = usuarioRepository.findByEmail("fake@test.com");
        assertTrue(optUser.isEmpty());
    }
}