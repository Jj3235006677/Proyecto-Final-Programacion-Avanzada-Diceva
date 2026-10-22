package com.uniquindio.ecommerce.domain.Service;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import org.springframework.stereotype.Component;

@Component
public class VerificadorDeCompra {

    public void verificarQuePuedeAgregarJuego(
            Compra compra,
            JuegoMesa juegoMesa,
            int cantidad) {

        if (compra == null) {
            throw new ReglaDominioException(
                    "La compra no existe"
            );
        }

        if (juegoMesa == null) {
            throw new ReglaDominioException(
                    "El juego de mesa no existe"
            );
        }

        if (compra.consultarEstadoCompra()
                != com.uniquindio.ecommerce.domain.valueobject.EstadoPago.PENDIENTE) {

            throw new ReglaDominioException(
                    "Solo se pueden agregar juegos a una compra pendiente"
            );
        }

        if (cantidad <= 0) {
            throw new ReglaDominioException(
                    "La cantidad debe ser mayor que cero"
            );
        }

        if (cantidad > juegoMesa.consultarStock()) {
            throw new ReglaDominioException(
                    "No hay stock suficiente para cubrir la compra"
            );
        }
    }

}