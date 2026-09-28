package com.uniquindio.ecommerce.domain.TestUseCase;

import com.uniquindio.ecommerce.application.UseCase.RealizarCompraUseCase;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.infrastructure.CompraRepositorioEnMemoria;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RealizarCompraUseCaseTest {

    @Test
    public void Debe_Crear_Y_Guardar_Una_Compra() {

        // Arrange
        CompraRepositorioEnMemoria repository =
                new CompraRepositorioEnMemoria();

        RealizarCompraUseCase useCase =
                new RealizarCompraUseCase(repository);

        UUID idCompra = UUID.randomUUID();
        UUID idJuego = UUID.randomUUID();
        UUID idUsuario = UUID.randomUUID();

        // Act
        Compra compra = useCase.ejecutar(
                idCompra,
                List.of(idJuego),
                idUsuario,
                new Precio(50000),
                LocalDateTime.now()
        );

        // Assert
        assertNotNull(compra);

        assertEquals(
                compra,
                repository.obtenerPorId(idCompra).orElse(null)
        );
    }
}