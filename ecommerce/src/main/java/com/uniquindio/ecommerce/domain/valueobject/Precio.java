package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;

public record Precio(float precioProducto) {

    public Precio {
        if(precioProducto<=0){
            throw new ReglaDominioException("Valor incorrecto");
        }
    }
}
