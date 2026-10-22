package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity;

import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
public class CompraEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "cedula_usuario", nullable = false)
    private UUID cedulaUsuario;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
    private EstadoPago estadoPago;

    @Embedded
    private PrecioEmbeddable precio;

    @ElementCollection
    @CollectionTable(
            name = "compra_juegos",
            joinColumns = @JoinColumn(name = "compra_id")
    )
    @Column(name = "juego_id", nullable = false)
    private List<UUID> idListaJuegosMesa = new ArrayList<>();
}