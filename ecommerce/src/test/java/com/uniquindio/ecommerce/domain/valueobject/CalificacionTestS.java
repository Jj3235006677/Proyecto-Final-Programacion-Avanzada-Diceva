package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalificacionTestS {

    @Test
    public void No_Calificacion_NEGATIVA(){
        assertThrows(ReglaDominioException.class,()->{
            new Calificacion(-7);
        });
    }
    @Test
    public void No_Calificacion_Mayor_5(){
        assertThrows(ReglaDominioException.class,()->{
            new Calificacion(7);
        });
    }

    @Test
    public void Validar_Que_Dos_Objetos_Con_Los_Mismo_Datos_Son_Lo_Mismo(){//Validacion Igualdad por valor
        Calificacion calificacionUno=new Calificacion(3);
        Calificacion calificacionDos=new Calificacion(3);
        assertEquals(calificacionUno,calificacionDos);

    }
}
