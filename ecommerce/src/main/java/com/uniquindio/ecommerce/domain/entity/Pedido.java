package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPedido;
import com.uniquindio.ecommerce.domain.valueobject.Precio;

import java.util.UUID;

public class Pedido {

    private final int id;

    private UUID idUsuario;
    private UUID juegoId;
    private int cantidad;
    private Precio precio;
    private String direccionEnvio;
    private EstadoPedido estado;
    private boolean pagoAprobado;

    public Pedido(
            int id,
            UUID idUsuario,
            UUID juegoId,
            int cantidad,
            Precio precio,
            String direccionEnvio,
            EstadoPedido estado,
            boolean pagoAprobado) {

        if (idUsuario == null) {
            throw new ReglaDominioException(
                    "El usuario no puede ser nulo"
            );
        }

        if (juegoId == null) {
            throw new ReglaDominioException(
                    "El juego no puede ser nulo"
            );
        }

        if (cantidad <= 0) {
            throw new ReglaDominioException(
                    "La cantidad debe ser mayor que cero"
            );
        }

        if (precio == null) {
            throw new ReglaDominioException(
                    "El precio no puede ser nulo"
            );
        }

        if (direccionEnvio == null || direccionEnvio.isBlank()) {
            throw new ReglaDominioException(
                    "La direccion de envio no puede estar vacia"
            );
        }

        if (estado == null) {
            throw new ReglaDominioException(
                    "El estado no puede ser nulo"
            );
        }

        this.id = id;
        this.idUsuario = idUsuario;
        this.juegoId = juegoId;
        this.cantidad = cantidad;
        this.precio = precio;
        this.direccionEnvio = direccionEnvio;
        this.estado = estado;
        this.pagoAprobado = pagoAprobado;
    }

    public void solicitarReembolso(String mensaje) {

        if (mensaje == null || mensaje.isBlank()) {
            throw new ReglaDominioException(
                    "No se puede hacer un reembolso sin su respectivo mensaje"
            );
        }

        if (estado.puedeTransicionarA(EstadoPedido.DEVUELTO)) {
            estado = EstadoPedido.DEVUELTO;

        } else if (estado == EstadoPedido.PENDIENTE) {
            throw new ReglaDominioException(
                    "No se puede reembolsar un pedido en estado PENDIENTE"
            );
        }
    }

    public EstadoPedido getEstado() {
        return estado;
    }
}