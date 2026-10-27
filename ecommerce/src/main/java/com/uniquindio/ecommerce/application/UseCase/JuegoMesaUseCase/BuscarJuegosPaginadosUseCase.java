package com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.ResultadoPaginado;
import org.springframework.stereotype.Service;

@Service
public class BuscarJuegosPaginadosUseCase {

    private final JuegoMesaRepository juegoMesaRepository;

    public BuscarJuegosPaginadosUseCase(
            JuegoMesaRepository juegoMesaRepository
    ) {
        this.juegoMesaRepository = juegoMesaRepository;
    }

    public ResultadoPaginado<JuegoMesa> ejecutar(
            String texto,
            Integer precioMaximo,
            int pagina,
            int tamanio
    ) {
        if (pagina < 0) {
            throw new IllegalArgumentException("El número de página no puede ser negativo");
        }
        if (tamanio <= 0) {
            throw new IllegalArgumentException("El tamaño de página debe ser mayor que cero");
        }
        return juegoMesaRepository.buscarPaginado(
                texto,
                precioMaximo,
                pagina,
                tamanio
        );
    }
}
