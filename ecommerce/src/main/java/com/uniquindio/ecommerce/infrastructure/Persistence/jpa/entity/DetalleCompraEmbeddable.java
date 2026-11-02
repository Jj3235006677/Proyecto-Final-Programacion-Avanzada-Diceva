
package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class DetalleCompraEmbeddable {

    @Column(name = "juego_id", nullable = false)
    private UUID idJuego;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Embedded
    private PrecioEmbeddable precioUnitario;
}
