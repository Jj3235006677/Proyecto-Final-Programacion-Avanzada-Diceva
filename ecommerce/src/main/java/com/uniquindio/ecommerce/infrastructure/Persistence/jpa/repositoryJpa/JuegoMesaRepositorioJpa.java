package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.repositoryJpa;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper.JuegoMesaPersistenceMapper;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.JuegoMesaJpaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JuegoMesaRepositorioJpa implements JuegoMesaRepository {

    private final JuegoMesaJpaDataRepository juegoMesaJpaDataRepository;
    private final JuegoMesaPersistenceMapper juegoMesaPersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<JuegoMesa> obtenerPorId(UUID id) {

        return juegoMesaJpaDataRepository.findById(id)
                .map(juegoMesaPersistenceMapper::toDomain);
    }

    @Override
    @Transactional
    public void guardar(JuegoMesa juegoMesa) {

        juegoMesaJpaDataRepository.save(
                juegoMesaPersistenceMapper.toEntity(juegoMesa)
        );
    }
}