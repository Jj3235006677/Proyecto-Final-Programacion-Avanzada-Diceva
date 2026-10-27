package com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase;

import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.domain.repository.ComentarioRepository;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;
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
                .orElseThrow();

        List<Comentario> lista = comentarioRepository
                .consultarComentarios(juegoMesa.getId())
                .orElseThrow();

        return Optional.of(lista);
    }
}
