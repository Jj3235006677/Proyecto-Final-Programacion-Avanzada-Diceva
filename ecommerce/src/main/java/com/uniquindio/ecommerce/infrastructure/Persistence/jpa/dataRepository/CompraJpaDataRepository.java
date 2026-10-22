package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompraJpaDataRepository extends JpaRepository<CompraEntity, UUID> {


}