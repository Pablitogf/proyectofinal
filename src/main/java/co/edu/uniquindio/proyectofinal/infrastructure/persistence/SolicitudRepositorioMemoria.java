package co.edu.uniquindio.proyectofinal.infrastructure.persistence;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.EstadoSolicitud;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SolicitudRepositorioMemoria implements SolicitudRepositorio {

    private final Map<String, Solicitud> datos = new HashMap<>();

    @Override
    public Solicitud guardar(Solicitud solicitud) {
        datos.put(solicitud.getId(), solicitud);
        return solicitud; // Devolvemos la misma solicitud
    }

    @Override
    public Optional<Solicitud> buscarPorId(String id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<Solicitud> listar() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public List<Solicitud> buscarPorEstado(EstadoSolicitud estado) {
        return datos.values().stream()
                .filter(s -> s.getEstado().equals(estado))
                .collect(Collectors.toList());
    }
}