
package com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase;

import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.ComentarioRepository;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultarComentarioUseCase {

    private final JuegoMesaRepository juegoMesaRepository;
    private final ComentarioRepository comentarioRepository;

    public Optional<List<Comentario>> ejecutar(String nombreJuegoMesa) {

        JuegoMesa juegoMesa = juegoMesaRepository
                .obtenerPorNombre(nombreJuegoMesa)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe un juego de mesa con ese nombre"
                        ));

        List<Comentario> comentarios = comentarioRepository
                .consultarComentarios(juegoMesa.getId())
                .orElse(List.of());

        return Optional.of(comentarios);
    }
}
