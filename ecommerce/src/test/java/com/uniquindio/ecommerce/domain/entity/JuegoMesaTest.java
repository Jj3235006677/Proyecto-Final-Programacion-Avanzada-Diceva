package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoMesaTest {

    @Test
    public void El_Stock_No_Puede_Ser_Negativo() {

        assertThrows(ReglaDominioException.class, () -> {
            JuegoMesa.crear(
                    UUID.randomUUID(),
                    UUID.randomUUID(),
                    "Catan",
                    "Juego de estrategia y comercio",
                    new Precio(120000),
                    -2,
                    new RangoJugadores(3, 4),
                    ComplejidadJuego.BASICA,
                    "Construcción",
                    "Aventura",
                    10,
                    "https://imagen.com/catan.jpg",
                    null
            );
        });
    }

    @Test
    public void La_Edad_Recomendada_No_Puede_Ser_Menor_A_Cero() {

        assertThrows(ReglaDominioException.class, () -> {
            JuegoMesa.crear(
                    UUID.randomUUID(),
                    UUID.randomUUID(),
                    "Catan",
                    "Juego de estrategia y comercio",
                    new Precio(120000),
                    2,
                    new RangoJugadores(3, 4),
                    ComplejidadJuego.BASICA,
                    "Construcción",
                    "Aventura",
                    -8,
                    "https://imagen.com/catan.jpg",
                    null
            );
        });
    }

    @Test
    public void La_Tematica_No_Puede_Estar_Vacia() {

        assertThrows(ReglaDominioException.class, () -> {
            JuegoMesa.crear(
                    UUID.randomUUID(),
                    UUID.randomUUID(),
                    "Catan",
                    "Juego de estrategia y comercio",
                    new Precio(120000),
                    2,
                    new RangoJugadores(3, 4),
                    ComplejidadJuego.BASICA,
                    "Construcción",
                    "",
                    8,
                    "https://imagen.com/catan.jpg",
                    null
            );
        });
    }
}