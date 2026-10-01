package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPedido;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTestS {
    @Test
    public void Verificar_Que_El_Reelbolso_Tenga_Su_Mensaje(){//Validacion de que lanze al exepcion
        assertThrows(ReglaDominioException.class,()->{
            Pedido.crearPedido(
                    1,
                    UUID.randomUUID(),
                    UUID.randomUUID(),
                    2,
                    new Precio(150000),
                    "Carrera 14 # 20-30, Armenia",
                    EstadoPedido.PENDIENTE,
                    false
            ).solicitarReembolso("");
        });
    }
    @Test
    public void No_Dejar_Pasar_A_Un_Estado_Invalido(){//Verifaca que no se pueda pasar a un estado valido
        Pedido pedido = Pedido.crearPedido(
                1,
                UUID.randomUUID(),
                UUID.randomUUID(),
                2,
                new Precio(150000),
                "Carrera 14 # 20-30, Armenia",
                EstadoPedido.PENDIENTE,
                false
        );

        assertThrows(ReglaDominioException.class,()->{
            pedido.solicitarReembolso("El producto salio malo");
        });

        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
    }

    @Test
    public void Validar_Que_Dos_Pedidos_Con_El_Mismo_Id_Son_Iguales(){//Aqui provamos que aunque cambiemos algo en un
        //siempre va seguir siendo el mismo y no uno nuevo, como en el caos de los value object
        Pedido pedido=Pedido.crearPedido(1,
                UUID.randomUUID(),
                UUID.randomUUID(),
                2,
                new Precio(150000),
                "Carrera 14 # 20-30, Armenia",
                EstadoPedido.PENDIENTE,
                false);

        Pedido pedido2=Pedido.crearPedido(1,
                UUID.randomUUID(),
                UUID.randomUUID(),
                4,
                new Precio(2000),
                "Carrera 14 # 20-30, Calarca",
                EstadoPedido.CONFIRMADO,
                true);


        assertTrue(pedido.equals(pedido2));
    }

}
