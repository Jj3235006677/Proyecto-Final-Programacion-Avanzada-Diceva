package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;

public record RangoJugadores(int maximoJugadores, int minimoJugadores) {
    public RangoJugadores {
        if(minimoJugadores <1){
            throw new ReglaDominioException("El minimo de jugadores de ser mayor o igual a 1");
        }
        if(minimoJugadores >maximoJugadores){
            throw new ReglaDominioException("El maximo de jugadores no puede ser menor que el minimo");
        }

    }





}
