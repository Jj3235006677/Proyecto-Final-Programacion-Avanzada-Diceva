
package com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase;

import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario ejecutar(
            String cedula,
            String email,
            String password) {

        if (usuarioRepository.buscarPorEmail(email).isPresent()) {
            throw new ReglaDominioException(
                    "El correo ya está registrado");
        }

        if (usuarioRepository.buscarPorCedula(cedula).isPresent()) {
            throw new ReglaDominioException(
                    "La cédula ya está registrada");
        }

        Usuario usuario = Usuario.crear(
                UUID.randomUUID().toString(),
                email,
                cedula,
                passwordEncoder.encode(password),
                TipoUsuario.USER
        );

        usuarioRepository.guardar(usuario);

        return usuario;
    }
}
