
package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ComentarioJpaDataRepository
        extends JpaRepository<ComentarioEntity, UUID> {

    @Query("""
        SELECT c
        FROM ComentarioEntity c
        WHERE c.codigoJuegoMesa = :codigoJuegoMesa
          AND c.eliminacionLogica = false
        """)
    List<ComentarioEntity> buscarPorJuegoMesa(
            @Param("codigoJuegoMesa") UUID codigoJuegoMesa
    );
}
