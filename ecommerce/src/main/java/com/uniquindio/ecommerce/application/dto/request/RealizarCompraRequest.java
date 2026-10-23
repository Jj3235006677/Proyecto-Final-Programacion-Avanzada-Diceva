package com.uniquindio.ecommerce.application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record RealizarCompraRequest(

        @Size(max = 50, message = "No se pueden incluir más de 50 juegos en una solicitud")
        List< UUID> idListaJuegosMesa,

        @NotNull(message = "El usuario es obligatorio")
        UUID cedulaUsuario

) {
}