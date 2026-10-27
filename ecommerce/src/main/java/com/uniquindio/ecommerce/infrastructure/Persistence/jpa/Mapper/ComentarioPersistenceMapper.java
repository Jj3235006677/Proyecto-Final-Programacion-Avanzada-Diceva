
package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper;

import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.domain.valueobject.Calificacion;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.ComentarioEntity;
import org.springframework.stereotype.Component;

@Component
public class ComentarioPersistenceMapper {

    public ComentarioEntity toEntity(Comentario comentario) {

        ComentarioEntity entity = new ComentarioEntity();

        entity.setIdComentario(comentario.getIdComentario());
        entity.setCodigoJuegoMesa(comentario.getCodigoJuegoMesa());
        entity.setCedula(comentario.getCedula());
        entity.setTitulo(comentario.getTitulo());
        entity.setCalificacion(comentario.getCalificacion().valor());
        entity.setComentario(comentario.getComentario());
        entity.setEliminacionLogica(comentario.isEliminacionLogica());

        return entity;
    }

    public Comentario toDomain(ComentarioEntity entity) {

        return Comentario.reconstruir(
                entity.getIdComentario(),
                entity.getCodigoJuegoMesa(),
                entity.getCedula(),
                entity.getTitulo(),
                new Calificacion(entity.getCalificacion()),
                entity.getComentario(),
                entity.isEliminacionLogica()
        );
    }
}
