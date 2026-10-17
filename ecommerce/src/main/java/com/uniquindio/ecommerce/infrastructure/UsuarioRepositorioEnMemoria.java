package com.uniquindio.ecommerce.infrastructure;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UsuarioRepositorioEnMemoria implements UsuarioRepository {

    private final Map<UUID, Usuario> usuarios=new HashMap<>();

    @Override
    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarios.values()
                .stream()
                .filter(usuario -> usuario.getCorreo().equalsIgnoreCase(correo))
                .findFirst();
    }

    @Override
    public Optional<Usuario> obtenerPorId(UUID id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarios.put(usuario.getCedula(),usuario);
    }
}
