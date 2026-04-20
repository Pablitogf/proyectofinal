package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.repository;

import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.SolicitudEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudJpaDataRepository extends JpaRepository<SolicitudEntity, String> {

    List<SolicitudEntity> findByEstado(String estado);
}