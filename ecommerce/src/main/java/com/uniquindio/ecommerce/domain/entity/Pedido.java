package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.valueobject.Precio;

public class Pedido {
    private final int id;

    private int idUsuario;
    private JuegoMesa juego;
    private int cantidad;
    private Precio precio;
    private String direccionEnvio;
    private String estado;
    private boolean pagoAprobado;

    public Pedido(int id, int idUsuario, JuegoMesa juego, int cantidad,
                  Precio precio, String direccionEnvio, String estado, boolean pagoAprobado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.juego = juego;
        this.cantidad = cantidad;
        this.precio = precio;
        this.direccionEnvio = direccionEnvio;
        this.estado = estado;
        this.pagoAprobado = pagoAprobado;
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
