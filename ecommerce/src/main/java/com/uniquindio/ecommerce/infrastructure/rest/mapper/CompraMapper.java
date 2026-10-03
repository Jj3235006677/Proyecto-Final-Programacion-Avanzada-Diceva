package com.uniquindio.ecommerce.infrastructure.rest.mapper;

import com.uniquindio.ecommerce.application.dto.response.CompraResponse;
import com.uniquindio.ecommerce.domain.entity.Compra;
import org.springframework.stereotype.Component;

@Component
public class CompraMapper {

    public CompraResponse toResponse(Compra compra) {

        return new CompraResponse(
                compra.getId(),
                compra.getIdListaJuegosMesa(),
                compra.getCedulaUsuario(),
                compra.getPrecioTotal(),
                compra.getFechaCompra(),
                compra.consultarEstadoCompra()
        );
    }
}