package com.uniquindio.ecommerce.domain.valueobject;

public enum EstadoPedido {

    PENDIENTE,
    CONFIRMADO,
    DEVUELTO;

    public boolean puedeTransicionarA(EstadoPedido siguiente) {
        return switch (this ) {
            case PENDIENTE -> siguiente == CONFIRMADO;
            case CONFIRMADO -> siguiente == DEVUELTO;
            case DEVUELTO -> false;
        };
    }


}