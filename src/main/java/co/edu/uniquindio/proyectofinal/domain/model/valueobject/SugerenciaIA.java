package co.edu.uniquindio.proyectofinal.domain.model.valueobject;

import co.edu.uniquindio.proyectofinal.domain.exception.ReglaDominioException;
import java.time.LocalDateTime;

// RF-13
public record SugerenciaIA(
        Prioridad prioridadSugerida,
        TipoSolicitud tipoSugerido,
        double confianza,
        LocalDateTime fechaGeneracion
) {
    public SugerenciaIA {

        if (prioridadSugerida == null || tipoSugerido == null) {
            throw new ReglaDominioException("La sugerencia de IA debe incluir prioridad y tipo.");
        }
        if (confianza < 0 || confianza > 1) {
            throw new ReglaDominioException("El nivel de confianza debe estar entre 0 y 1.");
        }
        if (fechaGeneracion == null) {
            fechaGeneracion = LocalDateTime.now();
        }
    }
}
