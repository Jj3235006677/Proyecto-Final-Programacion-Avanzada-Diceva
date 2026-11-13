package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.repositoryJpa;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper.CompraPersistenceMapper;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.CompraJpaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
@RequiredArgsConstructor
public class CompraReposiryJpa implements CompraRepository {
    private final CompraJpaDataRepository compraJpaDataRepository;
    private final CompraPersistenceMapper compraPersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Compra> obtenerPorId(UUID id) {
        return compraJpaDataRepository.findById(id)
                .map(compraPersistenceMapper::toDomain);
    }
    @Override
    public void guardar(Compra compra) {
        compraJpaDataRepository.save(compraPersistenceMapper.toEntity(compra));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> buscarAprobadasPorUsuarioYJuego(String cedulaUsuario, UUID idJuego) {
        return compraJpaDataRepository
                .buscarPorUsuarioJuegoYEstado(cedulaUsuario, idJuego, EstadoPago.APROBADO)
                .stream()
                .map(compraPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> buscarPendientes() {
        return compraJpaDataRepository
                .findByEstadoPagoOrderByFechaCompraAsc(EstadoPago.PENDIENTE)
                .stream()
                .map(compraPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> buscarPorUsuario(String cedulaUsuario) {
        return compraJpaDataRepository
                .findByCedulaUsuarioOrderByFechaCompraDesc(cedulaUsuario)
                .stream()
                .map(compraPersistenceMapper::toDomain)
                .toList();
    }




}
