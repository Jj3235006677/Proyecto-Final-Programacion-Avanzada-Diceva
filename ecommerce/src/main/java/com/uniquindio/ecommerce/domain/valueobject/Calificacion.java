package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;

public record Calificacion(int valor) {

    public Calificacion{
        if(valor>5 || valor<0){
            throw new ReglaDominioException("La calificacion debe de ser 0 a 5");
        }
    }
}
