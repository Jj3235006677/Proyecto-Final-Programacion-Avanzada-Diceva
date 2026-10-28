package com.uniquindio.ecommerce.domain.repository;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.valueobject.RankingJuego;
import com.uniquindio.ecommerce.domain.valueobject.ResultadoPaginado;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JuegoMesaRepository {

    Optional<JuegoMesa> obtenerPorId(UUID id);

    void guardar(JuegoMesa juegoMesa);

    ResultadoPaginado<JuegoMesa> buscarPaginado(
            String texto,
            Integer precioMaximo,
            int pagina,
            int tamanio
    );

    List<RankingJuego> obtenerRankingJuegos();

    void eliminarLogicamente(UUID idJuegoMesa);

    Optional<JuegoMesa> obtenerPorNombre(String nombreJuegoMesa);
}