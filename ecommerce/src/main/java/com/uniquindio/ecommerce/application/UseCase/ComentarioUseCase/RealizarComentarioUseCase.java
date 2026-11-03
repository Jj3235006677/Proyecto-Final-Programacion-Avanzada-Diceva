package com.uniquindio.ecommerce.application.UseCase.ComentarioUseCase;

import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.ComentarioRepository;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.valueobject.Calificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RealizarComentarioUseCase {

    private final ComentarioRepository comentarioRepository;
    private final CompraRepository compraRepository;

    @Transactional
    public Comentario ejecutar(
            UUID idComentario,
            UUID codigoJuegoMesa,
            String cedula,
            String titulo,
            Calificacion calificacion,
            String texto) {

        // 1. Buscar las compras aprobadas del usuario que incluyen el juego.
        List<Compra> compras = compraRepository
                .buscarAprobadasPorUsuarioYJuego(cedula, codigoJuegoMesa);

        if (compras.isEmpty()) {
            throw new ReglaDominioException(
                    "Solo puedes calificar juegos que hayas comprado "
                            + "y cuyo pago esté aprobado");
        }

        // 2. Tomar la primera compra donde aún no haya comentado ese juego
        //    (máximo un comentario por juego dentro de cada compra).
        Compra compra = compras.stream()
                .filter(c -> !comentarioRepository
                        .existeComentario(c.getId(), codigoJuegoMesa))
                .findFirst()
                .orElseThrow(() -> new ReglaDominioException(
                        "Ya dejaste un comentario para este juego "
                                + "en cada una de tus compras"));

        // 3. El agregado Compra tiene la última palabra sobre su regla.
        compra.verificarQuePermiteComentar(cedula, codigoJuegoMesa);

        // 4. Crear y guardar el comentario ligado a esa compra.
        Comentario comentario = Comentario.crearComentario(
                idComentario,
                compra.getId(),
                codigoJuegoMesa,
                cedula,
                titulo,
                calificacion,
                texto
        );

        comentarioRepository.guardar(comentario);

        return comentario;
    }
}