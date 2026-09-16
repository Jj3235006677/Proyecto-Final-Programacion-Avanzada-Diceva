package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;

public record RagoJugadores(int maximoJugadores, int minimoJudadores) {
    public RagoJugadores{
        if(minimoJudadores<1){
            throw new ReglaDominioException("El minimo de jugadors de ser mayor o igual a 1");
        }

    }





}
