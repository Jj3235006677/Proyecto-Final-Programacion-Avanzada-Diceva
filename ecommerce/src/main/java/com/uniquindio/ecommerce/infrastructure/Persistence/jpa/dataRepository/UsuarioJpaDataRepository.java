
package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioJpaDataRepository
        extends JpaRepository<UsuarioEntity, String> {

    Optional<UsuarioEntity> findByEmail(String email);
}
