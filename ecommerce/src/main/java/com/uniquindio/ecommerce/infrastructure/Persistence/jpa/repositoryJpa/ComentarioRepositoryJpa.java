
package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.repositoryJpa;

import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.domain.repository.ComentarioRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper.ComentarioPersistenceMapper;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.ComentarioJpaDataRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.ComentarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public Optional<List<Comentario>> consultarComentarios(UUID codigoJuegoMesa) {
        List<ComentarioEntity> comentariosEntity =
                comentarioJpaDataRepository.buscarPorJuegoMesa(codigoJuegoMesa);

        if (comentariosEntity.isEmpty()) {
            return Optional.empty();
        }

        List<Comentario> comentarios = comentariosEntity.stream()
                .map(comentarioPersistenceMapper::toDomain)
                .toList();

        return Optional.of(comentarios);
    }

    @Override
    public void guardar(Comentario comentario) {
        comentarioJpaDataRepository.save(
                comentarioPersistenceMapper.toEntity(comentario)
        );
    }
}
