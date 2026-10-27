
package com.uniquindio.ecommerce.application.UseCase.ComentarioUseCase;

import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.domain.repository.ComentarioRepository;
import com.uniquindio.ecommerce.domain.valueobject.Calificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RealizarComentarioUseCase {

    private final ComentarioRepository comentarioRepository;

    public Comentario ejecutar(
            UUID idComentario,
            UUID codigoJuegoMesa,
            String usuarioId,
            String titulo,
            Calificacion calificacion,
            String comentario
    ) {
        Comentario comentario1 = Comentario.crearComentario(
                idComentario,
                codigoJuegoMesa,
                usuarioId,
                titulo,
                calificacion,
                comentario
        );

        comentarioRepository.guardar(comentario1);

        return comentario1;
    }
}
