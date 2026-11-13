package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.domain.valueobject.RankingJuego;
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
    SELECT new com.uniquindio.ecommerce.domain.valueobject.RankingJuego(
        j.nombre, SUM(d.cantidad)
    )
    FROM CompraEntity c
    JOIN c.detalles d
    JOIN JuegoMesaEntity j ON j.id = d.idJuego
    WHERE j.eliminacionLogica = false
    GROUP BY j.id, j.nombre
    ORDER BY SUM(d.cantidad) DESC
    """)
    List<RankingJuego> obtenerRankingJuegos();


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




    @Query("""
    SELECT j
    FROM JuegoMesaEntity j
    WHERE LOWER(j.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))
      AND j.eliminacionLogica = false
    """)
    List<JuegoMesaEntity> buscarPorNombre(@Param("nombre") String nombre);


    @Query("""
    SELECT j
    FROM JuegoMesaEntity j
    WHERE j.eliminacionLogica = false
      AND j.stock > 0
      AND j.rangoJugadores.minimoJugadores <= :jugadores
      AND j.rangoJugadores.maximoJugadores >= :jugadores
    """)
    List<JuegoMesaEntity> buscarParaJugadores(@Param("jugadores") int jugadores);
}