package com.uniquindio.ecommerce.application.usecase;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.domain.valueobject.Precio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RealizarCompraUseCase {

    private final CompraRepository repository;// se pone en cada ccso de uso

    public RealizarCompraUseCase(CompraRepository repository){// se el llama al constructor
        this.repository=repository;
    }

    public Compra ejecutar(//en el ejecutar ya se realiza la accion del caso de uso, en este caso tiene un contrutor privado
                           //lo que hace que tenga una unica puerta privada y de la unica forma de crearlo el mediante el metodo
                           //o caso de uso ante creado
            UUID id,
            List<UUID> idListaJuegosMesa,
            UUID cedula,
            Precio precio,
            LocalDateTime fechaCompra) {//se ingresan lo atributos del objeto(esta caso compra)

        // AQUÍ SE CREA
        Compra compra = Compra.realizarCompra(
                id,
                idListaJuegosMesa,
                cedula,
                precio,
                fechaCompra);

        // AQUÍ SE GUARDA
        repository.guardar(compra);

        // AQUÍ SE DEVUELVE
        return compra;
    }


}
