package com.uniquindio.ecommerce.application.dto.request;

import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UsuarioRequest(

        @NotNull(message = "La cédula es obligatoria")
        UUID cedula,

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe tener un formato válido")
        String correo,

        @NotBlank(message = "La contraseña es obligatoria")
        String contraseña,

        @NotNull(message = "El tipo de usuario es obligatorio")
        TipoUsuario tipoUsuario
) {

}
