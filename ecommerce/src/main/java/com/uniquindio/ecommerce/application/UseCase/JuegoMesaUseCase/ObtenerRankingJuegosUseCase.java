package com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase;

import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.RankingJuego;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObtenerRankingJuegosUseCase {

    private final JuegoMesaRepository juegoMesaRepository;

    public ObtenerRankingJuegosUseCase(
            JuegoMesaRepository juegoMesaRepository) {
        this.juegoMesaRepository = juegoMesaRepository;
    }

    public List<RankingJuego> ejecutar() {
        return juegoMesaRepository.obtenerRankingJuegos();
    }
}