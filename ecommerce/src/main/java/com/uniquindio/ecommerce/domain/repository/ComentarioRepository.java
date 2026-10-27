package com.uniquindio.ecommerce.domain.repository;

import com.uniquindio.ecommerce.domain.entity.Comentario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComentarioRepository {

    Optional<Comentario> obtenerComentarioId(UUID id);

    Optional<List<Comentario>> consultarComentarios(UUID codigoJuegoMesa);
    void guardar(Comentario comentario);
}
