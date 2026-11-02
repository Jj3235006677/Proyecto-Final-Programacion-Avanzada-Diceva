
package com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS;

import org.springframework.stereotype.Component;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CrearCompraUseCase {

    private final CompraRepository repository;

    public CrearCompraUseCase(CompraRepository repository) {
        this.repository = repository;
    }

    public Compra ejecutar(
            UUID id,
            String cedula,
            LocalDateTime fechaCompra) {

        Compra compra = Compra.realizarCompra(
                id,
                cedula,
                fechaCompra
        );

        repository.guardar(compra);

        return compra;
    }
}
