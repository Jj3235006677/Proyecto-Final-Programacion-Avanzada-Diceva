package com.uniquindio.ecommerce.infrastructure.Persistence.Memoria;

import com.uniquindio.ecommerce.domain.entity.Comentario;

import com.uniquindio.ecommerce.domain.repository.ComentarioRepository;

import java.util.*;

public class ComentarioRepositorioEnMemoria implements ComentarioRepository {
    private final Map<UUID, Comentario> comentarios=new HashMap<>();

    @Override
    public Optional<Comentario> obtenerComentarioId(UUID id) {

        return Optional.ofNullable(comentarios.get(id));
    }

    @Override
    public Optional<List<Comentario>> consultarComentarios(UUID codigoJuegoMesa) {
        return Optional.empty();
    }

    @Override
    public void guardar(Comentario comentario) {

        comentarios.put(comentario.getIdComentario(),comentario);
    }
}
