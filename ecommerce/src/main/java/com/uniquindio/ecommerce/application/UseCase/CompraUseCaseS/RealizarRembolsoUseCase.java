package com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RealizarRembolsoUseCase {

    private final CompraRepository compraRepository;

    public RealizarRembolsoUseCase(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Transactional
    public void ejecutar(String motivo, UUID id) {

        Compra compra = compraRepository.obtenerPorId(id)
                .orElseThrow(() ->
                        new java.util.NoSuchElementException(
                                "La compra no existe"
                        )
                );

        compra.realizarReembolso(motivo);

        compraRepository.guardar(compra);
    }
}