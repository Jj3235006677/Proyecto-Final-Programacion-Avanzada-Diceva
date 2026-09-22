package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalificacionTest {

    @Test
    public void No_Calificacion_NEGATIVA(){
        assertThrows(ReglaDominioException.class,()->{
            new Calificacion(7);
        });
    }

    @Test
    public void Validar_Que_Dos_Objetos_Con_Los_Mismo_Datos_Son_Lo_Mismo(){
        Calificacion calificacionUno=new Calificacion(3);
        Calificacion calificacionDos=new Calificacion(3);
        assertEquals(calificacionUno,calificacionDos);

    }
}
