package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.CompraEntity;
import org.springframework.stereotype.Component;

@Component
public class CompraPersistenceMapper {

    /**
     * Convierte una compra del dominio en una entidad JPA
     * para guardarla en la base de datos.
     */
    public CompraEntity toEntity(Compra compra) {

        CompraEntity entity = new CompraEntity();

        entity.setId(compra.getId());

        entity.setCedulaUsuario(
                compra.getCedulaUsuario()
        );

        entity.setFechaCompra(
                compra.getFechaCompra()
        );

        entity.setEstadoPago(
                compra.consultarEstadoCompra()
        );

        entity.setPrecioTotal(
                compra.getPrecioTotal().precioProducto()
        );

        entity.setIdListaJuegosMesa(
                compra.getIdListaJuegosMesa()
        );

        return entity;
    }

    /**
     * Reconstruye una compra del dominio a partir
     * de los datos recuperados de la base de datos.
     */
    public Compra toDomain(CompraEntity entity) {

        return Compra.reconstruir(
                entity.getId(),
                entity.getIdListaJuegosMesa(),
                entity.getCedulaUsuario(),
                entity.getFechaCompra(),
                new Precio(entity.getPrecioTotal()),
                entity.getEstadoPago()
        );
    }
}