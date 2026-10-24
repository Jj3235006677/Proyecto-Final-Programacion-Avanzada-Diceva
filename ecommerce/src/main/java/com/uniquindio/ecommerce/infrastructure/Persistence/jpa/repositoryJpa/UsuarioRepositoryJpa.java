package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.repositoryJpa;

import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;

import java.util.Optional;
import java.util.UUID;

public class UsuarioRepositoryJpa implements UsuarioRepository {
    @Override
    public Optional<Usuario> obtenerPorId(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return Optional.empty();
    }

    @Override
    public void guardar(Usuario usuario) {

    }
}
