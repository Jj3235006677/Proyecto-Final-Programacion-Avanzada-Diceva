package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.JuegoMesaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JuegoMesaJpaDataRepository
        extends JpaRepository<JuegoMesaEntity, UUID> {

    @Query(//buscar por nombre,cantidad,precio
            value = """
            SELECT j
            FROM JuegoMesaEntity j
            WHERE j.eliminacionLogica = false
              AND (
                    :texto IS NULL
                    OR LOWER(j.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
                  )
              AND (
                    :precioMaximo IS NULL
                    OR j.precio.monto <= :precioMaximo
                  )
            """,
            countQuery = """
            SELECT COUNT(j)
            FROM JuegoMesaEntity j
            WHERE j.eliminacionLogica = false
              AND (
                    :texto IS NULL
                    OR LOWER(j.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
                  )
              AND (
                    :precioMaximo IS NULL
                    OR j.precio.monto <= :precioMaximo
                  )
            """
    )
    Page<JuegoMesaEntity> buscarPaginado(
            @Param("texto") String texto,
            @Param("precioMaximo") Integer precioMaximo,
            Pageable pageable
    );





    @Query("""
    SELECT j.nombre, COUNT(cj)
    FROM JuegoMesaEntity j
    JOIN CompraEntity c ON j.id MEMBER OF c.idListaJuegosMesa
    JOIN c.idListaJuegosMesa cj
    WHERE j.eliminacionLogica = false
    GROUP BY j.id, j.nombre
    ORDER BY COUNT(cj) DESC
    """)
    List<Object[]> obtenerRankingJuegos();


    // Eliminación lógica de un juego de mesa
    @Modifying
    @Transactional
    @Query("""
    UPDATE JuegoMesaEntity j
    SET j.eliminacionLogica = true
    WHERE j.id = :id
      AND j.eliminacionLogica = false
    """)
    int eliminarLogicamente(@Param("id") UUID id);
    
}