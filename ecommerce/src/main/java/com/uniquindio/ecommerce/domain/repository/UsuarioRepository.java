
package com.uniquindio.ecommerce.domain.repository;

import com.uniquindio.ecommerce.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorCedula(String cedula);

    void guardar(Usuario usuario);
}
