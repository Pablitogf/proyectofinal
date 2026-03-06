package co.edu.uniquindio.proyectofinal.domain.valueobject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoSolicitudTest {

    @Test
    void valoresEstadoDebenExistir() {
        assertNotNull(EstadoSolicitud.CREADA);
        assertNotNull(EstadoSolicitud.CLASIFICADA);
        assertNotNull(EstadoSolicitud.ASIGNADA);
        assertNotNull(EstadoSolicitud.EN_ATENCION);
        assertNotNull(EstadoSolicitud.ATENDIDA);
        assertNotNull(EstadoSolicitud.CERRADA);
    }

    @Test
    void estadoDebeTenerOrdenCorrecto() {
        assertTrue(EstadoSolicitud.CREADA.ordinal() < EstadoSolicitud.CLASIFICADA.ordinal());
        assertTrue(EstadoSolicitud.CLASIFICADA.ordinal() < EstadoSolicitud.ASIGNADA.ordinal());
        assertTrue(EstadoSolicitud.ASIGNADA.ordinal() < EstadoSolicitud.EN_ATENCION.ordinal());
        assertTrue(EstadoSolicitud.EN_ATENCION.ordinal() < EstadoSolicitud.ATENDIDA.ordinal());
        assertTrue(EstadoSolicitud.ATENDIDA.ordinal() < EstadoSolicitud.CERRADA.ordinal());
    }

    // Prueba adicional 1: toString retorna nombre
    @Test
    void toStringDebeRetornarNombre() {
        assertEquals("CREADA", EstadoSolicitud.CREADA.toString());
        assertEquals("CERRADA", EstadoSolicitud.CERRADA.toString());
    }

    // Prueba adicional 2: Verificar flujo completo
    @Test
    void flujoEstadosDebeSerCompleto() {
        EstadoSolicitud[] estados = EstadoSolicitud.values();
        assertEquals(6, estados.length);
    }
}