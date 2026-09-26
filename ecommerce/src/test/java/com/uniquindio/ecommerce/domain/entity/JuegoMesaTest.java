package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;




public class JuegoMesaTest {

    //Aqui estan las pruebas del agregado JuegoMesa
    @Test
    public void Estock_No_Puede_Ser_Negativo(){//Aqui se valida que el estock no pueda ser negativo

        assertThrows(ReglaDominioException.class,()->{
            JuegoMesa juego = JuegoMesa.crear(
                    UUID.randomUUID(),
                    "Catan",
                    "Juego de estrategia y comercio",
                    new Precio(120000),
                    -2,
                    new RangoJugadores(4, 3),
                    ComplejidadJuego.BASICA,
                    "Construcción",
                    "Aventura",
                    10,
                    "https://imagen.com/catan.jpg",
                    true
            );
        });


    }

    @Test
    public void La_Edad_Recomendada_No_Menor_A_Cero(){

        assertThrows(ReglaDominioException.class,()->{//Aqui validamos que la edad ingresada no pueda ser menor a cero
            JuegoMesa juego = JuegoMesa.crear(
                    UUID.randomUUID(),
                    "Catan",
                    "Juego de estrategia y comercio",
                    new Precio(120000),
                    2,
                    new RangoJugadores(4, 3),
                    ComplejidadJuego.BASICA,
                    "Construcción",
                    "Aventura",
                    -8,
                    "https://imagen.com/catan.jpg",
                    true
            );
        });


    }

    @Test
    public void Todos_Campos_Llenos(){

        assertThrows(ReglaDominioException.class,()->{//Aqui validamos que todos los campos esten llenos
            JuegoMesa juego = JuegoMesa.crear(
                    UUID.randomUUID(),
                    "Catan",
                    "Juego de estrategia y comercio",
                    new Precio(120000),
                    2,
                    new RangoJugadores(4, 3),
                    ComplejidadJuego.BASICA,
                    "Construcción",
                    "",
                    8,
                    "https://imagen.com/catan.jpg",
                    true
            );
        });


    }
}
