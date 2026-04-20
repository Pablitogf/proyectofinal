package co.edu.uniquindio.proyectofinal.infrastructure.security.repository;

import co.edu.uniquindio.proyectofinal.infrastructure.security.entity.SecurityUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityUserJpaDataRepository extends JpaRepository<SecurityUserEntity, String> {
    Optional<SecurityUserEntity> findByEmail(String email);
}
