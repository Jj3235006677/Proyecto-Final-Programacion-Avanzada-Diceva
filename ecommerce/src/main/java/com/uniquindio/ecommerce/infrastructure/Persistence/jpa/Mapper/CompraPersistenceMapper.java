
package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.valueobject.DetalleCompra;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.CompraEntity;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.DetalleCompraEmbeddable;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.PrecioEmbeddable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompraPersistenceMapper {

    public CompraEntity toEntity(Compra compra) {

        CompraEntity entity = new CompraEntity();

        entity.setId(compra.getId());
        entity.setCedulaUsuario(compra.getCedulaUsuario());
        entity.setFechaCompra(compra.getFechaCompra());
        entity.setEstadoPago(compra.consultarEstadoCompra());

        entity.setPrecio(
                new PrecioEmbeddable(
                        compra.getPrecioTotal().precioProducto()
                )
        );

        List<DetalleCompraEmbeddable> detalles = compra.getDetalles()
                .stream()
                .map(detalle -> {
                    DetalleCompraEmbeddable embeddable =
                            new DetalleCompraEmbeddable();

                    embeddable.setIdJuego(detalle.idJuego());
                    embeddable.setCantidad(detalle.cantidad());

                    embeddable.setPrecioUnitario(
                            new PrecioEmbeddable(
                                    detalle.precioUnitario().precioProducto()
                            )
                    );

                    return embeddable;
                })
                .toList();

        entity.setDetalles(detalles);

        return entity;
    }

    public Compra toDomain(CompraEntity entity) {

        List<DetalleCompra> detalles = entity.getDetalles()
                .stream()
                .map(detalle -> new DetalleCompra(
                        detalle.getIdJuego(),
                        detalle.getCantidad(),
                        new Precio(
                                detalle.getPrecioUnitario().getMonto()
                        )
                ))
                .toList();

        return Compra.reconstruir(
                entity.getId(),
                detalles,
                entity.getCedulaUsuario(),
                entity.getFechaCompra(),
                new Precio(entity.getPrecio().getMonto()),
                entity.getEstadoPago()
        );
    }
}
