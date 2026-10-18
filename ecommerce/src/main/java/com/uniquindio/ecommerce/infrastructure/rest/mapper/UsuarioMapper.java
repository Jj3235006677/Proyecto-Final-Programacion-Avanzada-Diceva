package com.uniquindio.ecommerce.infrastructure.rest.mapper;

import com.uniquindio.ecommerce.application.dto.response.JuegoMesaResponse;
import com.uniquindio.ecommerce.application.dto.response.UsuarioResponse;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getTipoUsuario(),
                usuario.getContraseña()
        );
    }
}
