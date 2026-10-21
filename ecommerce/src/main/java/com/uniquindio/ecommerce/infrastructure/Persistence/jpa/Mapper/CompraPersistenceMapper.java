package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.mapper;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.CompraEntity;
import org.springframework.stereotype.Component;

@Component
public class CompraPersistenceMapper {

    public CompraEntity toEntity(Compra compra) {

        CompraEntity entity = new CompraEntity();

        entity.setId(compra.getId());
        entity.setCedulaUsuario(compra.getCedulaUsuario());
        entity.setFechaCompra(compra.getFechaCompra());
        entity.setEstadoPago(compra.consultarEstadoCompra());

        entity.setPrecioTotal(
                compra.getPrecioTotal().precioProducto()
        );

        entity.setIdListaJuegosMesa(
                compra.getIdListaJuegosMesa()
        );

        return entity;
    }

    public Compra toDomain(CompraEntity entity) {

        Compra compra = Compra.realizarCompra(
                entity.getId(),
                entity.getIdListaJuegosMesa(),
                entity.getCedulaUsuario(),
                entity.getFechaCompra()
        );

        // Recuperar el precio total
        int precio = entity.getPrecioTotal();

        if (precio > 0) {
            compra.sumarContidadesDinero(precio);
        }

        // Recuperar el estado de pago
        if (entity.getEstadoPago() != null) {

            switch (entity.getEstadoPago()) {

                case APROBADO:
                    compra.confirmarCompra();
                    break;

                case REEMBOLSADO:
                    compra.confirmarCompra();
                    compra.realizarReembolso(
                            "Reembolso recuperado de la base de datos"
                    );
                    break;

                case PENDIENTE:
                    break;
            }
        }

        return compra;
    }
}