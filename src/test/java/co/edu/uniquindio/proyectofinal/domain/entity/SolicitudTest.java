package co.edu.uniquindio.proyectofinal.domain.entity;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudTest {

    private Usuario crearUsuario() {
        return new Usuario(
                "Pablo",
                new Email("pablo@uniquindio.edu.co"),
                TipoUser.ESTUDIANTE
        );
    }

    private Solicitud crearSolicitud() {
        return Solicitud.Builder.registrar(
                "Necesito certificado académico",
                crearUsuario()
        );
    }

    @Test
    void registrarSolicitudCorrectamente() {

        // Verifica que una solicitud se cree correctamente usando el builder
        // Se valida que el estado inicial sea CREADA y que tenga código generado

        Solicitud solicitud = crearSolicitud();

        assertEquals(EstadoSolicitud.CREADA, solicitud.getEstado());
        assertNotNull(solicitud.getCodigo());
    }

    @Test
    void clasificarSolicitudCorrectamente() {

        // Verifica que una solicitud en estado CREADA pueda clasificarse
        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearUsuario();

        solicitud.clasificar(Prioridad.ALTA, TipoSolicitud.CERTIFICADO, coordinador);

        assertEquals(EstadoSolicitud.CLASIFICADA, solicitud.getEstado());
        assertEquals(Prioridad.ALTA, solicitud.getPrioridad());
        assertEquals(TipoSolicitud.CERTIFICADO, solicitud.getTipo());
    }

    @Test
    void asignarResponsableCorrectamente() {

        // Verifica que después de clasificar se pueda asignar un responsable
        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearUsuario();
        Usuario responsable = crearUsuario();

        solicitud.clasificar(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, coordinador);
        solicitud.asignarResponsable(responsable, coordinador);

        assertEquals(EstadoSolicitud.ASIGNADA, solicitud.getEstado());
        assertEquals(responsable, solicitud.getResponsable());
    }

    @Test
    void iniciarAtencionCorrectamente() {

        // Verifica que una solicitud asignada pueda pasar a EN_ATENCION
        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearUsuario();
        Usuario responsable = crearUsuario();

        solicitud.clasificar(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, coordinador);
        solicitud.asignarResponsable(responsable, coordinador);

        solicitud.iniciarAtencion(responsable);

        assertEquals(EstadoSolicitud.EN_ATENCION, solicitud.getEstado());
    }

    @Test
    void finalizarAtencionCorrectamente() {

        // Verifica que una solicitud en atención pueda finalizarse
        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearUsuario();
        Usuario responsable = crearUsuario();

        solicitud.clasificar(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, coordinador);
        solicitud.asignarResponsable(responsable, coordinador);
        solicitud.iniciarAtencion(responsable);

        solicitud.finalizarAtencion(responsable);

        assertEquals(EstadoSolicitud.ATENDIDA, solicitud.getEstado());
    }

    @Test
    void cerrarSolicitudCorrectamente() {

        // Verifica que una solicitud atendida pueda cerrarse
        Solicitud solicitud = crearSolicitud();
        Usuario coordinador = crearUsuario();
        Usuario responsable = crearUsuario();

        solicitud.clasificar(Prioridad.MEDIA, TipoSolicitud.CERTIFICADO, coordinador);
        solicitud.asignarResponsable(responsable, coordinador);
        solicitud.iniciarAtencion(responsable);
        solicitud.finalizarAtencion(responsable);

        solicitud.cerrar(responsable);

        assertEquals(EstadoSolicitud.CERRADA, solicitud.getEstado());
        assertNotNull(solicitud.getFechaCierre());
    }

    @Test
    void cambiarPrioridadCorrectamente() {

        // Verifica que se pueda cambiar la prioridad de la solicitud
        Solicitud solicitud = crearSolicitud();
        Usuario usuario = crearUsuario();

        solicitud.priorizar(Prioridad.ALTA, usuario);

        assertEquals(Prioridad.ALTA, solicitud.getPrioridad());
    }


}