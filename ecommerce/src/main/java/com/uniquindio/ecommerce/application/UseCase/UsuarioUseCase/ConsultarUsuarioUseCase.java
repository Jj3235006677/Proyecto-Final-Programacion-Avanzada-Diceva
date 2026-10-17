package com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase;

import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ConsultarUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public ConsultarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario ejecutar(UUID cedula){
        Usuario usario=usuarioRepository.obtenerPorId(cedula).orElseThrow();
        return usario;
    }
}
