package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.domain.valueobject.Precio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

        if (id == null
                || cedulaUsuario == null
                || precioTotal == null
                || fechaCompra == null) {

            throw new ReglaDominioException("La compra debe tener toda la informacion obligatoria");
        }
        if (idListaJuegosMesa == null || idListaJuegosMesa.isEmpty()) {//2 invariante una compra debe tener como minimo un juego
            throw new ReglaDominioException(
                    "La compra debe tener al menos un juego de mesa"
            );
        }

        this.id = id;
        this.idListaJuegosMesa = idListaJuegosMesa;
        this.cedulaUsuario = cedulaUsuario;
        this.precioTotal = precioTotal;
        this.fechaCompra = fechaCompra;
        this.estadoPago = EstadoPago.PENDIENTE;// 3 Invariante toda compra nueva inicia en estado PENDIENTE.
    }

    // Única puerta de entrada para crear una Compra.
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
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Compra)) return false;

        Compra compra = (Compra) o;

        return id.equals(compra.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }}