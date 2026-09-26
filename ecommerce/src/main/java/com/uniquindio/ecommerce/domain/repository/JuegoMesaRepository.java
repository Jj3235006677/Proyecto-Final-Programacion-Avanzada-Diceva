package com.uniquindio.ecommerce.domain.repository;


import com.uniquindio.ecommerce.domain.entity.JuegoMesa;

import java.util.Optional;
import java.util.UUID;

public interface JuegoMesaRepository {

    Optional<JuegoMesa> obtenerPorId(UUID id);// SE usa el optional por un null no hace explotar el codigo


    void guardar(JuegoMesa juegoMesa);
}
