package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.CuponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuponJpaDataRepository extends JpaRepository<CuponEntity, String> {
}