package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.JuegoMesaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface JuegoMesaJpaDataRepository
        extends JpaRepository<JuegoMesaEntity, UUID> {

    @Query(
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
}