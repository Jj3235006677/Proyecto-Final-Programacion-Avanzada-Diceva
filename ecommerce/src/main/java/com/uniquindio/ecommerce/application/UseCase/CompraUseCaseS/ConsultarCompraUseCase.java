package com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConsultarCompraUseCase {

    private final CompraRepository compraRepository;

    public ConsultarCompraUseCase(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public Compra ejecutar(UUID id) {
        return compraRepository.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));
    }
}