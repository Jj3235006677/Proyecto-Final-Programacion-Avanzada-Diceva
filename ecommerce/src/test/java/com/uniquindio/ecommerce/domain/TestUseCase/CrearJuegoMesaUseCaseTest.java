package com.uniquindio.ecommerce.domain.TestUseCase;

import com.uniquindio.ecommerce.application.CrearJuegoMesaUseCase;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;
import com.uniquindio.ecommerce.infrastructure.JuegoMesaRepositorioMemoria;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CrearJuegoMesaUseCaseTest {

        @Test
        public void Debe_Crear_Y_Guardar_Un_JuegoMesa() {

            // Arrangeo
            JuegoMesaRepositorioMemoria repository =
                    new JuegoMesaRepositorioMemoria();

            CrearJuegoMesaUseCase useCase =
                    new CrearJuegoMesaUseCase(repository);

            UUID idJuego = UUID.randomUUID();

            // Act
            JuegoMesa juegoMesa = useCase.ejecutar(
                    idJuego,
                    "Catan",
                    "Juego de estrategia y comercio",
                    new Precio(120000),
                    10,
                    new RangoJugadores(4, 3),
                    ComplejidadJuego.BASICA,
                    "Construccion",
                    "Aventura",
                    10,
                    "https://imagen.com/catan.jpg",
                    true
            );

            // Assert
            assertNotNull(juegoMesa);

            assertEquals(
                    juegoMesa,
                    repository.obtenerPorId(idJuego).orElse(null)
            );
        }
    }

