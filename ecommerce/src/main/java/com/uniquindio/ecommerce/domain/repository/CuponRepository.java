package com.uniquindio.ecommerce.domain.repository;

import com.uniquindio.ecommerce.domain.entity.Cupon;

import java.util.Optional;

public interface CuponRepository {

    Optional<Cupon> buscarPorCodigo(String codigo);
}