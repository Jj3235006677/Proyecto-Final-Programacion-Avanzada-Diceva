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
    private String cedulaUsuario;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
    private EstadoPago estadoPago;

    @Embedded
    private PrecioEmbeddable precio;

    @Column(name = "codigo_cupon", length = 30)
    private String codigoCupon;

    @Column(name = "porcentaje_descuento")
    private Integer porcentajeDescuento;

    @ElementCollection
    @CollectionTable(
            name = "compra_juegos",
            joinColumns = @JoinColumn(name = "compra_id")
    )
    private List<DetalleCompraEmbeddable> detalles = new ArrayList<>();
}