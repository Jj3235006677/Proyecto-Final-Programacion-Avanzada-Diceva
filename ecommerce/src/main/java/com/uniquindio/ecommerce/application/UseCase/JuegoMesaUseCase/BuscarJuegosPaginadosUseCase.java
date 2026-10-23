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
        return juegoMesaRepository.buscarPaginado(
                texto,
                precioMaximo,
                pagina,
                tamanio
        );
    }
}
