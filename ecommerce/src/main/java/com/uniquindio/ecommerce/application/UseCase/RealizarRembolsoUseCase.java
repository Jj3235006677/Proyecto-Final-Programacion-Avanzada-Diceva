package com.uniquindio.ecommerce.application.UseCase;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class RealizarRembolsoUseCase {
    private CompraRepository repository;

    public RealizarRembolsoUseCase(CompraRepository compraRepository) {
        this.repository = compraRepository;
    }
    public void ejecutar(String motivo, UUID id){
        Compra compra=repository.obtenerPorId(id).orElseThrow();
        compra.realizarReembolso(motivo);
        repository.guardar(compra);
    }

}
