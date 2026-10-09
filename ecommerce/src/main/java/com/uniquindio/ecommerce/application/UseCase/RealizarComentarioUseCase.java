package com.uniquindio.ecommerce.application.UseCase;

import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.domain.repository.ComentarioRepository;
import com.uniquindio.ecommerce.domain.valueobject.Calificacion;

import java.util.UUID;

public class RealizarComentarioUseCase {
    private final ComentarioRepository repository;

    public RealizarComentarioUseCase(ComentarioRepository repository) {
        this.repository = repository;
    }

    public Comentario ejecutar(UUID idComentario, UUID codigoJuegoMesa,
                               UUID usuarioId, String titulo, Calificacion calificacion, String textoComentario){
        Comentario comentario=Comentario.crearComentario(idComentario,codigoJuegoMesa,usuarioId,titulo,calificacion,textoComentario);

        repository.guardar(comentario);
        return comentario;

    }
}
//congelar precio
//activo