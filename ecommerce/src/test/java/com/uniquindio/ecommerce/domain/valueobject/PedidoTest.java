package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.entity.Pedido;
import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PedidoTest {
    @Test//Entregar
    public void Verificar_Que_El_Reelbolso_Tenga_Su_Mensaje(){
        assertThrows(ReglaDominioException.class,()->{
            new Pedido(
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
    public void No_Dejar_Pasar_A_Un_Estado_Invalido(){
        Pedido pedido=new Pedido(
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
        assertEquals(EstadoPedido.PENDIENTE,pedido.getEstado());
    }
}
