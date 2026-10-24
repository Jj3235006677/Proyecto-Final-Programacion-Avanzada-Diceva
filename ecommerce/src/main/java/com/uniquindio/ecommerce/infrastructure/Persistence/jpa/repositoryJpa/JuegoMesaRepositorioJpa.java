package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.repositoryJpa;


import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.ResultadoPaginado;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper.JuegoMesaPersistenceMapper;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.JuegoMesaJpaDataRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.JuegoMesaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    @Transactional(readOnly = true)
    public ResultadoPaginado<JuegoMesa> buscarPaginado(
            String texto,
            Integer precioMaximo,
            int pagina,
            int tamanio
    ) {
        if (pagina < 0) {
            throw new IllegalArgumentException(
                    "El número de página no puede ser negativo"
            );
        }

        if (tamanio <= 0) {
            throw new IllegalArgumentException(
                    "El tamaño de página debe ser mayor que cero"
            );
        }

        Pageable pageable = PageRequest.of(pagina, tamanio);

        Page<JuegoMesaEntity> resultado =
                juegoMesaJpaDataRepository.buscarPaginado(
                        texto == null || texto.isBlank()
                                ? null
                                : texto.trim(),
                        precioMaximo,
                        pageable
                );

        return new ResultadoPaginado<>(
                resultado.getContent().stream()
                        .map(juegoMesaPersistenceMapper::toDomain)
                        .toList(),
                resultado.getTotalElements(),
                resultado.getTotalPages(),
                resultado.getNumber(),
                resultado.getSize()
        );
    }
    @Override
    @Transactional(readOnly = true)
    public List<Object[]> obtenerRankingJuegos() {
        return juegoMesaJpaDataRepository.obtenerRankingJuegos();
    }
}
