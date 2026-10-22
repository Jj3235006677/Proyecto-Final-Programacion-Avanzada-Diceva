package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.CompraEntity;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.PrecioEmbeddable;
import org.springframework.stereotype.Component;

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

        entity.setIdListaJuegosMesa(
                compra.getIdListaJuegosMesa()
        );

        return entity;
    }

    public Compra toDomain(CompraEntity entity) {

        return Compra.reconstruir(
                entity.getId(),
                entity.getIdListaJuegosMesa(),
                entity.getCedulaUsuario(),
                entity.getFechaCompra(),
                new Precio(entity.getPrecio().getMonto()),
                entity.getEstadoPago()
        );
    }
}