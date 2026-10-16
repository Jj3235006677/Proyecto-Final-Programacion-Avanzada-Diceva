package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;

public record Precio(int precioProducto) {

    public Precio {
        if (precioProducto < 0) {
            throw new ReglaDominioException("El precio debe ser mayor que cero");
        }
    }

    public Precio sumar(int cantidad) {
        return new Precio(precioProducto + cantidad);
    }
}