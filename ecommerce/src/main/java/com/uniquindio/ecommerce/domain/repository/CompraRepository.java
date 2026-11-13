package com.uniquindio.ecommerce.domain.repository;

import com.uniquindio.ecommerce.domain.entity.Compra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompraRepository {

    Optional<Compra> obtenerPorId(UUID id); // Optional para que un null no haga explotar el código

    void guardar(Compra compra);

    List<Compra> buscarAprobadasPorUsuarioYJuego(String cedulaUsuario, UUID idJuego);

    List<Compra> buscarPendientes();

    List<Compra> buscarPorUsuario(String cedulaUsuario);
}