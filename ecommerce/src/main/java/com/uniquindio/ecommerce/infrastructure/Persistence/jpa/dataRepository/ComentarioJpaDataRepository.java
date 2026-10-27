package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComentarioJpaDataRepository extends JpaRepository<ComentarioEntity, UUID> {
}
