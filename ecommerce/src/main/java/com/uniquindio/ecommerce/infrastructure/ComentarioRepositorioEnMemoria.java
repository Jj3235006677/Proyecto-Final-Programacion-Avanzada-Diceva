package com.uniquindio.ecommerce.infrastructure;

import com.uniquindio.ecommerce.domain.entity.Comentario;

import com.uniquindio.ecommerce.domain.repository.ComentarioRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ComentarioRepositorioEnMemoria implements ComentarioRepository {
    private final Map<UUID, Comentario> comentarios=new HashMap<>();

    @Override
    public Optional<Comentario> obtenerComentarioId(UUID id) {

        return Optional.ofNullable(comentarios.get(id));
    }

    @Override
    public void guardar(Comentario comentario) {

        comentarios.put(comentario.getIdComentario(),comentario);
    }
}
