package com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase;

import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CrearUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public CrearUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario ejecutar(UUID cedula, String nombre, String apellido,
                         String correo, String contraseña,
                         TipoUsuario tipoUsuario){


        if (usuarioRepository.obtenerPorId(cedula).isPresent()) {//Preguntar a la profesora
            throw new ReglaDominioException(
                    "Ya existe un usuario con esa cédula"
            );
        }

        Usuario usuario=Usuario.crear(cedula,nombre,apellido,correo,contraseña,tipoUsuario);
        usuarioRepository.guardar(usuario);
        return usuario;

    }
}
