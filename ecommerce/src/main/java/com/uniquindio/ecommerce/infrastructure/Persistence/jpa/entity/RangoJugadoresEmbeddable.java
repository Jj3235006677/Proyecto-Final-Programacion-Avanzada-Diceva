package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class RangoJugadoresEmbeddable {

    @Column(name = "minimo_jugadores", nullable = false)
    private int minimoJugadores;

    @Column(name = "maximo_jugadores", nullable = false)
    private int maximoJugadores;

    public RangoJugadoresEmbeddable(
            int minimoJugadores,
            int maximoJugadores) {
        this.minimoJugadores = minimoJugadores;
        this.maximoJugadores = maximoJugadores;
    }
}