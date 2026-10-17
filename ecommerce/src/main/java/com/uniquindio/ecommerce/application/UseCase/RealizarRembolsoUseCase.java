package com.uniquindio.ecommerce.application.UseCase;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RealizarRembolsoUseCase {

    private final CompraRepository compraRepository;
    private final JuegoMesaRepository juegoMesaRepository;

    public RealizarRembolsoUseCase(
            CompraRepository compraRepository,
            JuegoMesaRepository juegoMesaRepository) {

        this.compraRepository = compraRepository;
        this.juegoMesaRepository = juegoMesaRepository;
    }

    public void ejecutar(String motivo, UUID id) {

        Compra compra = compraRepository.obtenerPorId(id)
                .orElseThrow(() ->
                        new ReglaDominioException("La compra no existe"));

        // Contar las unidades de cada juego comprado.
        Map<UUID, Integer> cantidades = new HashMap<>();

        for (UUID idJuego : compra.getIdListaJuegosMesa()) {
            cantidades.merge(idJuego, 1, Integer::sum);
        }

        // Buscar los juegos y devolver las unidades al inventario.
        Map<UUID, JuegoMesa> juegos = new HashMap<>();

        for (Map.Entry<UUID, Integer> entrada : cantidades.entrySet()) {

            JuegoMesa juego = juegoMesaRepository.obtenerPorId(entrada.getKey())
                    .orElseThrow(() ->
                            new ReglaDominioException(
                                    "No existe el juego con ID: " + entrada.getKey()));

            juego.devolverStock(entrada.getValue());
            juegos.put(entrada.getKey(), juego);
        }

        // Cambiar el estado del pago a REEMBOLSADO.
        compra.realizarReembolso(motivo);

        // Guardar los cambios.
        for (JuegoMesa juego : juegos.values()) {
            juegoMesaRepository.guardar(juego);
        }

        compraRepository.guardar(compra);
    }
}