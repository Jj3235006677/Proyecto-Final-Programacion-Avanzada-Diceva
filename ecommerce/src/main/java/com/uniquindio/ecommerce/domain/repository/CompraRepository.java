package com.uniquindio.ecommerce.domain.repository;

import com.uniquindio.ecommerce.domain.entity.Compra;

import java.util.Optional;
import java.util.UUID;

public interface CompraRepository {
    Optional<Compra> obtenerPorId(UUID id);// SE usa el optional por un null no hace explotar el codigo


    void guardar(Compra compra);
}
