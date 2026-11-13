package com.uniquindio.ecommerce.infrastructure.Persistence.Memoria;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;

import java.util.*;

//@Component
public class CompraRepositorioEnMemoria implements CompraRepository {

    private final Map<UUID, Compra>compras=new HashMap<>();


    @Override
    public Optional<Compra> obtenerPorId(UUID id){
        return Optional.ofNullable(compras.get(id));
    }

    @Override
    public void guardar(Compra compra) {
        compras.put(compra.getId(), compra);
    }

    @Override
    public List<Compra> buscarAprobadasPorUsuarioYJuego(String cedulaUsuario, UUID idJuego) {
        return List.of();
    }


    @Override
    public List<Compra> buscarPendientes() {
        return List.of();
    }

    @Override
    public List<Compra> buscarPorUsuario(String cedulaUsuario) {
        return List.of();
    }


}
