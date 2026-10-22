package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.JuegoMesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JuegoMesaJpaDataRepository
        extends JpaRepository<JuegoMesaEntity, UUID> {
}