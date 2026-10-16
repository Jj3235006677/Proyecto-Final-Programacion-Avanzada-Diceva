package com.uniquindio.ecommerce.domain.valueobject;

public enum EstadoPago {

    PENDIENTE,
    APROBADO,
    REEMBOLSADO;

    public boolean puedeTransicionarA(EstadoPago siguiente) {

        if (siguiente == null) {
            return false;
        }

        return switch (this) {

            case PENDIENTE ->
                    siguiente == APROBADO ;

            case APROBADO ->
                    siguiente == REEMBOLSADO;


            case REEMBOLSADO -> false;
        };
    }
}