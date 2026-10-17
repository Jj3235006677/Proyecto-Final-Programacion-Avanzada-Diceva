package com.uniquindio.ecommerce.application.UseCase.CompraUseCase;
import org.springframework.stereotype.Component;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Component

public class CrearCompraUseCase {

    private final CompraRepository repository;// se pone en cada ccso de uso

    public CrearCompraUseCase(CompraRepository repository){// se el llama al constructor
        this.repository=repository;
    }

    public Compra ejecutar(UUID id, List<UUID> idListaJuegosMesa, UUID cedula,  LocalDateTime fechaCompra) {//se ingresan lo atributos del objeto(esta caso compra)
        //ese ejecutar crea ya el objeto

        Compra compra = Compra.realizarCompra(id, idListaJuegosMesa, cedula,  fechaCompra);

        // AQUÍ SE GUARDA
        repository.guardar(compra);

        // AQUÍ SE DEVUELVE
        return compra;
    }


}
