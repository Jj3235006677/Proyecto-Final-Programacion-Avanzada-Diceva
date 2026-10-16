package com.uniquindio.ecommerce.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstadoPagoTest {

    @Test
    public void Un_Pago_Pendiente_Puede_Ser_Aprobado() {

        // Arrange
        EstadoPago estado = EstadoPago.PENDIENTE;

        // Act
        boolean resultado = estado.puedeTransicionarA(EstadoPago.APROBADO);

        // Assert
        assertTrue(resultado);
    }


}