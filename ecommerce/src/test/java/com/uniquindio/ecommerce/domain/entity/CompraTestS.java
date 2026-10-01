package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompraTestS {
    //Aqui van estar la pruebas para el agregado de juegoMesa
    @Test
    public void Validar_Todos_Campos_Llenos(){//Todos los campos de la compra tienen que estar llenos


        assertThrows(ReglaDominioException.class,()->{
            Compra compra = Compra.realizarCompra(
                    UUID.randomUUID(),
                    List.of(UUID.randomUUID()),
                    UUID.randomUUID(),
                    null,
                    LocalDateTime.now()
            );
        });


    }

    @Test
    public void Una_Compra_Debe_Tener_Minimo_Un_Juego(){//La compra debe tener minimo un juego agregado
        assertThrows(ReglaDominioException.class,()->{
            Compra compra = Compra.realizarCompra(
                    UUID.randomUUID(),
                    null,
                    UUID.randomUUID(),
                    new Precio(50000),
                    LocalDateTime.now()
            );
        });



    }

    @Test
    public void Una_Compra_Siempre_Inicia_En_Pendiente(){//una compra siempre debe de iniciar en estado de pendiente

            Compra compra = Compra.realizarCompra(
                    UUID.randomUUID(),
                    List.of(UUID.randomUUID()),
                    UUID.randomUUID(),
                    new Precio(50000),
                    LocalDateTime.now()
            );

            assertEquals(EstadoPago.PENDIENTE,compra.consultarEstadoCompra());




    }

}
