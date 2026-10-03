package com.uniquindio.ecommerce.application.dto.response;

import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CompraResponse(
        UUID id,
        List<UUID> idListaJuegosMesa,
        UUID cedulaUsuario,
        Precio precioTotal,
        LocalDateTime fechaCompra,
        EstadoPago estadoPago
) {
}