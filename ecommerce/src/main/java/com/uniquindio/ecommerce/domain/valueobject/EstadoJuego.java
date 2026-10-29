package com.uniquindio.ecommerce.domain.valueobject;

public enum EstadoJuego {
    PREVENTA,
    DISPONIBLE,
    AGOTADO;

    public boolean puedeTransicionarA(EstadoJuego siguiente) {
        if (siguiente == null) {
            return false;
        }


        return switch (this) {
            case PREVENTA -> siguiente == DISPONIBLE;
            case DISPONIBLE -> siguiente == AGOTADO;
            case AGOTADO -> siguiente == DISPONIBLE;
        };
    }
}