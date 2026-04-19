package co.edu.uniquindio.proyectofinal.aplication.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Prioridad;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.SugerenciaIA;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoSolicitud;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDateTime;

class SugerenciaIATest {

    @Test
    @DisplayName("Validar integridad: La sugerencia debe crearse completa y correctamente")
    void crearSugerenciaValida() {
        // 1. Definimos los datos de entrada
        Prioridad prioridadEsperada = Prioridad.ALTA;
        TipoSolicitud tipoEsperado = TipoSolicitud.HOMOLOGACION;
        double confianzaEsperada = 0.95;

        // 2. Ejecutamos la creación
        SugerenciaIA sugerencia = new SugerenciaIA(
                prioridadEsperada,
                tipoEsperado,
                confianzaEsperada,
                LocalDateTime.now()
        );

        // 3. El test fallará (ROJO) si CUALQUIERA de estas condiciones no se cumple:
        assertAll("Verificación de campos del Record",
                () -> assertNotNull(sugerencia, "La sugerencia no debería ser nula"),
                () -> assertEquals(prioridadEsperada, sugerencia.prioridadSugerida(), "La prioridad no coincide"),
                () -> assertEquals(tipoEsperado, sugerencia.tipoSugerido(), "El tipo de solicitud no coincide"),
                () -> assertEquals(confianzaEsperada, sugerencia.confianza(), "El nivel de confianza no es el esperado"),
                () -> assertNotNull(sugerencia.fechaGeneracion(), "La fecha de generación debe existir")
        );
    }

    @Test
    @DisplayName("Fallar al detectar la excepción de nulo")
    void validarTipoObligatorio() {
        assertDoesNotThrow(() -> {
            new SugerenciaIA(Prioridad.MEDIA, null, 0.8, null);
        }, "Se lanzó una excepción cuando se esperaba éxito.");
    }

    @Test
    @DisplayName("Debe funcionar correctamente para solicitudes de soporte o certificados")
    void probarOtrosTipos() {
        SugerenciaIA soporte = new SugerenciaIA(Prioridad.BAJA, TipoSolicitud.SOPORTE, 0.99, null);
        SugerenciaIA certificado = new SugerenciaIA(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, 0.90, null);

        assertAll(
                () -> assertEquals(TipoSolicitud.SOPORTE, soporte.tipoSugerido()),
                () -> assertEquals(TipoSolicitud.CERTIFICADO, certificado.tipoSugerido())
        );
    }

    @Test
    void testConfianzaNegativaFalla() {
        assertDoesNotThrow(() -> {
            new SugerenciaIA(Prioridad.BAJA, TipoSolicitud.SOPORTE, -0.1, null);
        }, "El test fallará aquí y verás el mensaje de la ReglaDominioException en la consola.");
    }
}