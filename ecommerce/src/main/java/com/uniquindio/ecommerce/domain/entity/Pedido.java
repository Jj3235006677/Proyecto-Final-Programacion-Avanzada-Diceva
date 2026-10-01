package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPedido;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import java.util.UUID;

public class Pedido {

    private final int id;

    private UUID idUsuario;
    private UUID compraId;
    private int cantidad;
    private Precio precio;
    private String direccionEnvio;
    private EstadoPedido estado;
    private boolean pagoAprobado;

    private Pedido(
            int id,
            UUID idUsuario,
            UUID compraId,
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

        if (compraId == null) {
            throw new ReglaDominioException(
                    "La Compra no puede ser nulo"
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
        this.compraId = compraId;
        this.cantidad = cantidad;
        this.precio = precio;
        this.direccionEnvio = direccionEnvio;
        this.estado = estado;
        this.pagoAprobado = pagoAprobado;
    }
    public static Pedido crearPedido(int id,
                              UUID idUsuario,
                              UUID compraId,
                              int cantidad,
                              Precio precio,
                              String direccionEnvio,
                              EstadoPedido estado,
                              boolean pagoAprobado){


        return new Pedido(id,idUsuario,compraId,cantidad,precio,direccionEnvio,estado,pagoAprobado);
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Pedido)) return false;

        Pedido pedido = (Pedido) o;

        return id == pedido.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}