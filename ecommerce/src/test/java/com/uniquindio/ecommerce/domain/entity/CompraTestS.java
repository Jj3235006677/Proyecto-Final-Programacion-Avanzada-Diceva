package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompraTestS {

    @Test
    public void Validar_Todos_Campos_Llenos() {

        assertThrows(ReglaDominioException.class, () -> {
            Compra.realizarCompra(
                    UUID.randomUUID(),
                    List.of(UUID.randomUUID()),
                    null,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    public void Una_Compra_Debe_Tener_Minimo_Un_Juego() {

        assertThrows(ReglaDominioException.class, () -> {
            Compra.realizarCompra(
                    UUID.randomUUID(),
                    null,
                    UUID.randomUUID(),
                    LocalDateTime.now()
            );
        });
    }

    @Test
    public void Una_Compra_No_Debe_Tener_Una_Lista_Vacia() {

        assertThrows(ReglaDominioException.class, () -> {
            Compra.realizarCompra(
                    UUID.randomUUID(),
                    List.of(),
                    UUID.randomUUID(),
                    LocalDateTime.now()
            );
        });
    }

    @Test
    public void Una_Compra_No_Debe_Tener_Fecha_Nula() {

        assertThrows(ReglaDominioException.class, () -> {
            Compra.realizarCompra(
                    UUID.randomUUID(),
                    List.of(UUID.randomUUID()),
                    UUID.randomUUID(),
                    null
            );
        });
    }

    @Test
    public void Una_Compra_Siempre_Inicia_En_Pendiente() {

        Compra compra = Compra.realizarCompra(
                UUID.randomUUID(),
                List.of(UUID.randomUUID()),
                UUID.randomUUID(),
                LocalDateTime.now()
        );

        assertEquals(
                EstadoPago.PENDIENTE,
                compra.consultarEstadoCompra()
        );
    }
}