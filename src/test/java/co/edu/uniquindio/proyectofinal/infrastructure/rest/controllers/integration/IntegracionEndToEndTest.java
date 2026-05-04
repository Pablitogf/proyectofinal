package co.edu.uniquindio.proyectofinal.infrastructure.rest.controllers.integration;

import co.edu.uniquindio.proyectofinal.infrastructure.config.setup.test.LoginIntegrationTestUtil;
import co.edu.uniquindio.proyectofinal.infrastructure.config.setup.test.UsuarioTestDataLoader;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.UsuarioEntity;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.repository.UsuarioJpaDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IntegracionEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioJpaDataRepository repo;

    private ObjectMapper mapper = new ObjectMapper();
    private UsuarioEntity user;

    @BeforeEach
    void setup() {
        repo.deleteAll();
        user = repo.save(UsuarioTestDataLoader.usuarioAdminBase());
    }

    @Test
    void testFlujoCompleto() throws Exception {

        String token = LoginIntegrationTestUtil.obtenerTokenIntegro(
                user.getEmail(),
                "adminpass",
                mockMvc,
                mapper
        );

        mockMvc.perform(get("/api/usuarios/" + user.getId())
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("admin@test.com"));
    }
}