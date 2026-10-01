package com.uniquindio.ecommerce.domain.repository;

import com.uniquindio.ecommerce.domain.entity.Comentario;

import java.util.Optional;
import java.util.UUID;

public interface ComentarioRepository {

    Optional<Comentario> obtenerComentarioId(UUID id);

    void guardar(Comentario comentario);
}
