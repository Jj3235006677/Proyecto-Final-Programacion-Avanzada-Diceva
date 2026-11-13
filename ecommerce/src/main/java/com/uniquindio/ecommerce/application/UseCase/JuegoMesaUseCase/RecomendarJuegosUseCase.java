package com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class RecomendarJuegosUseCase {

    private static final int CANTIDAD_RECOMENDACIONES = 3;

    private final JuegoMesaRepository juegoMesaRepository;

    public RecomendarJuegosUseCase(JuegoMesaRepository juegoMesaRepository) {
        this.juegoMesaRepository = juegoMesaRepository;
    }

    public List<JuegoMesa> ejecutar(int jugadores, List<UUID> excluir) {

        if (jugadores < 1) {
            throw new ReglaDominioException("El número de jugadores debe ser mayor que cero");
        }

        List<JuegoMesa> candidatos =
                new ArrayList<>(juegoMesaRepository.buscarParaJugadores(jugadores));

        if (candidatos.isEmpty()) {
            throw new ReglaDominioException(
                    "No tenemos juegos para " + jugadores + " jugadores");
        }

        List<UUID> yaVistos = excluir == null ? List.of() : excluir;

        Collections.shuffle(candidatos);
        candidatos.sort(Comparator.comparing((JuegoMesa juego) -> yaVistos.contains(juego.getId())));

        return candidatos.stream()
                .limit(CANTIDAD_RECOMENDACIONES)
                .toList();
    }
}