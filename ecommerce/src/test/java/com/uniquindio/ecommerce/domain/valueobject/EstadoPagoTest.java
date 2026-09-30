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



    @Test
    public void Un_Pago_Aprobado_No_Puede_Cambiar_De_Estado() {

        // Arrange
        EstadoPago estado = EstadoPago.APROBADO;

        // Act
        boolean resultado = estado.puedeTransicionarA(EstadoPago.RECHAZADO);

        // Assert
        assertFalse(resultado);
    }

    @Test
    public void Un_Pago_Rechazado_No_Puede_Cambiar_De_Estado() {

        // Arrange
        EstadoPago estado = EstadoPago.RECHAZADO;

        // Act
        boolean resultado = estado.puedeTransicionarA(EstadoPago.APROBADO);

        // Assert
        assertFalse(resultado);
    }
}