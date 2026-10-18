package com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase;

import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class RealizarLogueUseCase {
    private final UsuarioRepository usuarioRepository;

    public RealizarLogueUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario ejecutar(String correo, String contraseña){


        Usuario usuario=usuarioRepository.obtenerPorCorreo(correo).orElseThrow(() ->
                new ReglaDominioException(
                        "Correo o contraseña incorrectos"
                )
        );
        usuario.validarCredenciales(correo,contraseña);
        return usuario;

    }
}
