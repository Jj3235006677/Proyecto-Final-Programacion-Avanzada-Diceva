package com.uniquindio.ecommerce.domain.repository;

import com.uniquindio.ecommerce.domain.entity.Compra;

import java.util.Optional;
import java.util.UUID;

public interface CompraRepository {
    Optional<Compra> obtenerPorId(UUID id);


    void guardar(Compra compra);
}
