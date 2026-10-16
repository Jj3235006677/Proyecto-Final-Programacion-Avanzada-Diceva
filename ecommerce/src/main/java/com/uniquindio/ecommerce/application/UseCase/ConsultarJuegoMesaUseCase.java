package com.uniquindio.ecommerce.application.UseCase;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConsultarJuegoMesaUseCase {

    private final JuegoMesaRepository juegoMesaRepository;

    public ConsultarJuegoMesaUseCase(JuegoMesaRepository juegoMesaRepository) {
        this.juegoMesaRepository = juegoMesaRepository;
    }

    public JuegoMesa ejecutar(UUID id) {
        return juegoMesaRepository.obtenerPorId(id)
                .orElseThrow(() ->
                        new RuntimeException("Juego de mesa no encontrado"));
    }
}