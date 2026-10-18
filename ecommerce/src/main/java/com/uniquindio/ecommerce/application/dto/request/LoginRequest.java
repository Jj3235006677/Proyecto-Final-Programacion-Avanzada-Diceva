package com.uniquindio.ecommerce.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
public record LoginRequest(

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe tener un formato válido")
        String correo,

        @NotBlank(message = "La contraseña es obligatoria")
        String contraseña

) {}
