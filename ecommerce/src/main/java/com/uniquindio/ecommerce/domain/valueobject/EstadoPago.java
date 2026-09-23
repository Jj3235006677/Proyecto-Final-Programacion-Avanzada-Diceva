package com.uniquindio.ecommerce.domain.valueobject;

public enum EstadoPago {
    PENDIENTE,
    APROBADO,
    RECHAZADO;
    public boolean puedeTransicionarA(EstadoPago siguiente) {

        return switch (this) {
            case PENDIENTE ->
                    siguiente == APROBADO || siguiente == RECHAZADO;

            case APROBADO ->
                    false;

            case RECHAZADO ->
                    false;
        };
    }

}
