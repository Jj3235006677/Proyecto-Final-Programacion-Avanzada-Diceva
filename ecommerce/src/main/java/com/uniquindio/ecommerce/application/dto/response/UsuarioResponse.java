package com.uniquindio.ecommerce.application.dto.response;

import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;

import java.util.UUID;

public record UsuarioResponse(
        UUID cedula,
        String nombre,
        String apellido,
        String correo,
        TipoUsuario tipoUsuario,
        String contrseaña
) {
}
