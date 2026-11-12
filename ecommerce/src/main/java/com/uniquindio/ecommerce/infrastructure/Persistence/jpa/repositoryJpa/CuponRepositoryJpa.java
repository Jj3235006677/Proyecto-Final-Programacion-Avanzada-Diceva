package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.repositoryJpa;

import com.uniquindio.ecommerce.domain.entity.Cupon;
import com.uniquindio.ecommerce.domain.repository.CuponRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.CuponJpaDataRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CuponRepositoryJpa implements CuponRepository {

    private final CuponJpaDataRepository cuponJpaDataRepository;

    public CuponRepositoryJpa(CuponJpaDataRepository cuponJpaDataRepository) {
        this.cuponJpaDataRepository = cuponJpaDataRepository;
    }

    @Override
    public Optional<Cupon> buscarPorCodigo(String codigo) {
        if (codigo == null) {
            return Optional.empty();
        }
        return cuponJpaDataRepository.findById(codigo.trim().toUpperCase())
                .map(entity -> Cupon.reconstruir(
                        entity.getCodigo(),
                        entity.getPorcentaje(),
                        entity.getFechaVencimiento()
                ));
    }
}