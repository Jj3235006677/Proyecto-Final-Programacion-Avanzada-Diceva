package com.uniquindio.ecommerce.infrastructure;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CompraRepositorioEnMemoria implements CompraRepository {
    private final Map<UUID, Compra>compras=new HashMap<>();

//Guardado que no entiendo casi nada pilas preguntar a la profesora
    @Override
    public Optional<Compra> obtenerPorId(UUID id){
        return Optional.ofNullable(compras.get(id));
    }

    @Override
    public void guardar(Compra compra) {
        compras.put(compra.getId(), compra);
    }
}
