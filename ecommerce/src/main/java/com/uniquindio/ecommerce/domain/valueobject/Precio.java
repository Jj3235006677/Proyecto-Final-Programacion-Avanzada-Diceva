
package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;

public record Precio(int precioProducto) {

    public Precio {
        if (precioProducto < 0) {
            throw new ReglaDominioException(
                    "El precio debe ser mayor o igual a cero"
            );
        }
    }

    public Precio sumar(Precio otro) {
        return new Precio(
                precioProducto + otro.precioProducto()
        );
    }

    public Precio multiplicarPor(int cantidad) {
        return new Precio(precioProducto * cantidad);
    }
}
