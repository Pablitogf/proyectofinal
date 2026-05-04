package co.edu.uniquindio.proyectofinal.infrastructure.config.setup.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import org.springframework.test.web.servlet.MockMvc;

import co.edu.uniquindio.proyectofinal.infrastructure.rest.dto.LoginRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginIntegrationTestUtil {

    public static String obtenerTokenIntegro(
            String correo,
            String password,
            MockMvc mvc,
            ObjectMapper mapper) throws Exception {

        LoginRequest req = new LoginRequest(correo, password);

        var result = mvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        return JsonPath.parse(json).read("$.token").toString();
    }
}