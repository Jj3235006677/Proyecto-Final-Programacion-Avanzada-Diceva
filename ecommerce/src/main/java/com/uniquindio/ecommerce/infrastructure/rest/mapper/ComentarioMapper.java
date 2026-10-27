
package com.uniquindio.ecommerce.infrastructure.rest.mapper;

import com.uniquindio.ecommerce.application.dto.response.RealizarComentarioResponse;
import com.uniquindio.ecommerce.domain.entity.Comentario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    public RealizarComentarioResponse toResponse(Comentario comentario) {

        return new RealizarComentarioResponse(
                comentario.getIdComentario(),
                "Comentario creado correctamente",
                comentario.isEliminacionLogica()
        );
    }
}
