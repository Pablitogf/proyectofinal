package co.edu.uniquindio.proyectofinal.aplication.infrastructure.rest.controller;


import co.edu.uniquindio.proyectofinal.application.usecase.CerrarSolicitudUseCase;
import co.edu.uniquindio.proyectofinal.application.usecase.CrearSolicitudUseCase;
import co.edu.uniquindio.proyectofinal.application.usecase.ObtenerSolicitudUseCase;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.controller.SolicitudController;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.mapper.SolicitudMapper;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SolicitudController.class)
public class SolicitudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrearSolicitudUseCase crearSolicitudUseCase;

    @MockBean
    private ObtenerSolicitudUseCase obtenerSolicitudUseCase;

    @MockBean
    private CerrarSolicitudUseCase cerrarSolicitudUseCase; // Importante añadirlo

    @MockBean
    private SolicitudMapper solicitudMapper;

    @Test
    public void obtenerSolicitudDebeRetornarOk() throws Exception {
        mockMvc.perform(get("/api/solicitudes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}