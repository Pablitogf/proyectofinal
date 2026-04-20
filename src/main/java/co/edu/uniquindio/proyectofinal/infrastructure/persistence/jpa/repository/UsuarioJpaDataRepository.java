package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.repository;

import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaDataRepository extends JpaRepository<UsuarioEntity, String> {
}