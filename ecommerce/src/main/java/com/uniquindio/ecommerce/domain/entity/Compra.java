package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.domain.valueobject.Precio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Compra {

    private final UUID id;
    private final List<UUID> idListaJuegosMesa;
    private final UUID cedulaUsuario;
    private final Precio precioTotal;
    private final LocalDateTime fechaCompra;
    private final EstadoPago estadoPago;

    private Compra(
            UUID id,
            List<UUID> idListaJuegosMesa,
            UUID cedulaUsuario,
            Precio precioTotal,
            LocalDateTime fechaCompra) {

        if (id == null) {
            throw new ReglaDominioException(
                    "El id de la compra no puede ser nulo"
            );
        }

        if (idListaJuegosMesa == null || idListaJuegosMesa.isEmpty()) {
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

        this.id = id;
        this.idListaJuegosMesa = idListaJuegosMesa;
        this.cedulaUsuario = cedulaUsuario;
        this.precioTotal = precioTotal;
        this.fechaCompra = fechaCompra;
        this.estadoPago = EstadoPago.PENDIENTE;
    }

    // Única puerta de entrada para crear una Compra recomendaddo por la profesora
    public static Compra realizarCompra(
            UUID id,
            List<UUID> idListaJuegosMesa,
            UUID cedulaUsuario,
            Precio precioTotal,
            LocalDateTime fechaCompra) {

        return new Compra(
                id,
                idListaJuegosMesa,
                cedulaUsuario,
                precioTotal,
                fechaCompra
        );
    }
    public UUID getId() { return id; }
}