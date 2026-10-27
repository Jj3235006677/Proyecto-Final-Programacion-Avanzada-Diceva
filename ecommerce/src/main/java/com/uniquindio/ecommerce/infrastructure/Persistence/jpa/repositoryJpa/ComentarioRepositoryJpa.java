
package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.repositoryJpa;

import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.domain.repository.ComentarioRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper.ComentarioPersistenceMapper;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.ComentarioJpaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ComentarioRepositoryJpa implements ComentarioRepository {

    private final ComentarioJpaDataRepository comentarioJpaDataRepository;
    private final ComentarioPersistenceMapper comentarioPersistenceMapper;

    @Override
    public Optional<Comentario> obtenerComentarioId(UUID id) {
        return comentarioJpaDataRepository.findById(id)
                .map(comentarioPersistenceMapper::toDomain);
    }

    @Override
    public void guardar(Comentario comentario) {
        comentarioJpaDataRepository.save(
                comentarioPersistenceMapper.toEntity(comentario)
        );
    }
}
