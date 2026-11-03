package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CompraJpaDataRepository extends JpaRepository<CompraEntity, UUID> {

    @Query("""
        SELECT DISTINCT c
        FROM CompraEntity c
        JOIN c.detalles d
        WHERE c.cedulaUsuario = :cedula
          AND d.idJuego = :idJuego
          AND c.estadoPago = :estado
        ORDER BY c.fechaCompra ASC
        """)
    List<CompraEntity> buscarPorUsuarioJuegoYEstado(
            @Param("cedula") String cedula,
            @Param("idJuego") UUID idJuego,
            @Param("estado") EstadoPago estado);
}