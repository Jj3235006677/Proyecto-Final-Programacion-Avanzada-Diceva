package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;

public record Calificacion(int valor) {
    public Calificacion {
        if (valor < 1 || valor > 5) {
            throw new ReglaDominioException("La calificación debe estar entre 1 y 5 estrellas");
        }
    }
}