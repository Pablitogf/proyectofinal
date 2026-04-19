package co.edu.uniquindio.proyectofinal.infrastructure.persistence;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.model.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.EstadoSolicitud;

import java.util.*;

public class SolicitudRepositorioMemoria implements SolicitudRepositorio {

    private final Map<String, Solicitud> almacenamiento = new HashMap<>();

    @Override
    public void guardar(Solicitud solicitud) {
        almacenamiento.put(solicitud.getId(), solicitud);
    }

    @Override
    public Optional<Solicitud> buscarPorId(String id) {
        return Optional.ofNullable(almacenamiento.get(id));
    }

    @Override
    public List<Solicitud> listar() {
        return new ArrayList<>(almacenamiento.values());
    }

    @Override
    public List<Solicitud> buscarPorEstado(EstadoSolicitud estado) {

        List<Solicitud> resultado = new ArrayList<>();

        for (Solicitud solicitud : almacenamiento.values()) {
            if (solicitud.getEstado() == estado) {
                resultado.add(solicitud);
            }
        }

        return resultado;
    }
}
