package com.uniquindio.ecommerce.infrastructure.Persistence.jpa;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper.CompraPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
@RequiredArgsConstructor
public class CompraReposiryJpa implements CompraRepository {
    private final CompraJpaDataRepository compraJpaDataRepository;
    private final CompraPersistenceMapper compraPersistenceMapper;

    @Override
    public Optional<Compra> obtenerPorId(UUID id) {
        return compraJpaDataRepository.findById(id).map(compraPersistenceMapper::toDomain);
    }

    @Override
    public void guardar(Compra compra) {
        compraJpaDataRepository.save(compraPersistenceMapper.toEntity(compra));
    }
}
