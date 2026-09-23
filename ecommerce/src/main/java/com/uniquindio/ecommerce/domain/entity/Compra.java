package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.domain.valueobject.Precio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Compra {

    private final UUID id;
    private final List<UUID> idJuegoMesa;
    private final UUID cedulaUsuario;
    private final Precio precioTotal;
    private final LocalDateTime fechaCompra;
    private final EstadoPago estadoPago;

    public Compra(
            UUID id,
            List<UUID> idJuegoMesa,
            UUID cedulaUsuario,
            Precio precioTotal,
            LocalDateTime fechaCompra,
            EstadoPago estadoPago) {

        if (id == null) {
            throw new ReglaDominioException(
                    "El id de la compra no puede ser nulo"
            );
        }

        if (idJuegoMesa == null || idJuegoMesa.isEmpty()) {
            throw new ReglaDominioException(
                    "La compra debe tener al menos un juego de mesa"
            );
        }

        if (cedulaUsuario == null) {
            throw new ReglaDominioException(
                    "El usuario no puede ser nulo"
            );
        }

        if (precioTotal == null) {
            throw new ReglaDominioException(
                    "El precio total no puede ser nulo"
            );
        }

        if (fechaCompra == null) {
            throw new ReglaDominioException(
                    "La fecha de compra no puede ser nula"
            );
        }

        if (estadoPago == null) {
            throw new ReglaDominioException(
                    "El estado del pago no puede ser nulo"
            );
        }

        this.id = id;
        this.idJuegoMesa = idJuegoMesa;
        this.cedulaUsuario = cedulaUsuario;
        this.precioTotal = precioTotal;
        this.fechaCompra = fechaCompra;
        this.estadoPago = estadoPago;
    }
}