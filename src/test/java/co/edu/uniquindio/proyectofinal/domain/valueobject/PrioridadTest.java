package co.edu.uniquindio.proyectofinal.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrioridadTest {

    @Test
    void valoresPrioridadDebenExistir() {
        assertNotNull(Prioridad.BAJA);
        assertNotNull(Prioridad.MEDIA);
        assertNotNull(Prioridad.ALTA);
        assertNotNull(Prioridad.CRITICA);
    }

    @Test
    void prioridadDebeTenerOrdenCorrecto() {
        assertTrue(Prioridad.BAJA.ordinal() < Prioridad.MEDIA.ordinal());
        assertTrue(Prioridad.MEDIA.ordinal() < Prioridad.ALTA.ordinal());
        assertTrue(Prioridad.ALTA.ordinal() < Prioridad.CRITICA.ordinal());
    }

    // Prueba adicional 1: toString retorna nombre
    @Test
    void toStringDebeRetornarNombre() {
        assertEquals("BAJA", Prioridad.BAJA.toString());
        assertEquals("CRITICA", Prioridad.CRITICA.toString());
    }

    // Prueba adicional 2: Comparacion por valor
    @Test
    void prioridadesIgualesDebenSerComparables() {
        assertEquals(Prioridad.MEDIA, Prioridad.MEDIA);
        assertNotEquals(Prioridad.MEDIA, Prioridad.ALTA);
    }
}