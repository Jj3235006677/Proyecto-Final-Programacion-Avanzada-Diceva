package com.uniquindio.ecommerce.application.dto.request;

import com.uniquindio.ecommerce.domain.valueobject.Precio;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record RealizarCompraRequest(

        @NotEmpty(message = "La compra debe tener al menos un juego de mesa")
        List<UUID> idListaJuegosMesa,

        @NotNull(message = "El usuario es obligatorio")
        UUID cedulaUsuario,

        @NotNull(message = "El precio total es obligatorio")
        Precio precioTotal

) {
}