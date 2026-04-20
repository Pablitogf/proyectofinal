package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.repository;

import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.SolicitudEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudJpaDataRepository extends JpaRepository<SolicitudEntity, String> {

    List<SolicitudEntity> findByEstado(String estado);

    // Inferencia de metodos (consulta base por tipo)
    List<SolicitudEntity> findByTipo(String tipo);

    // JPQL: mismo escenario del taller con orden por prioridad y fecha
    @Query("""
            SELECT s
            FROM SolicitudEntity s
            WHERE s.tipo = :tipo
            ORDER BY
                CASE
                    WHEN s.prioridad = 'CRITICA' THEN 4
                    WHEN s.prioridad = 'ALTA' THEN 3
                    WHEN s.prioridad = 'MEDIA' THEN 2
                    WHEN s.prioridad = 'BAJA' THEN 1
                    ELSE 0
                END DESC,
                s.fechaCreacion DESC
            """)
    List<SolicitudEntity> buscarPorTipoOrdenadoJpql(@Param("tipo") String tipo);

    // JPQL: filtro por codigo prioritario o solicitante si codigo no viene
    @Query("""
            SELECT s
            FROM SolicitudEntity s
            WHERE (
                (:codigo IS NOT NULL AND :codigo <> '' AND s.codigo = :codigo)
                OR
                ((:codigo IS NULL OR :codigo = '') AND (:solicitanteId IS NULL OR :solicitanteId = '' OR s.solicitanteId = :solicitanteId))
            )
            ORDER BY s.fechaCreacion DESC
            """)
    List<SolicitudEntity> buscarPorCodigoOSolicitante(@Param("codigo") String codigo,
                                                      @Param("solicitanteId") String solicitanteId);

    // Paginacion con inferencia
    Page<SolicitudEntity> findByEstadoNot(String estadoExcluido, Pageable pageable);

    // Consulta nativa agregada por estado
    @Query(value = "SELECT estado, COUNT(*) total FROM solicitudes GROUP BY estado ORDER BY estado", nativeQuery = true)
    List<Object[]> reporteCantidadPorEstadoNativo();
}