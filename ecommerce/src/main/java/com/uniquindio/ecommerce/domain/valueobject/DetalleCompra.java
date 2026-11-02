
package com.uniquindio.ecommerce.domain.valueobject;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.Precio;

import java.util.UUID;

public record DetalleCompra(UUID idJuego, int cantidad, Precio precioUnitario) {

    public DetalleCompra {
        if (idJuego == null) {
            throw new ReglaDominioException(
                    "El identificador del juego es obligatorio"
            );
        }

        if (cantidad <= 0) {
            throw new ReglaDominioException(
                    "La cantidad debe ser mayor que cero"
            );
        }
    }

    public Precio subtotal() {
        return precioUnitario.multiplicarPor(cantidad);
    }
}
