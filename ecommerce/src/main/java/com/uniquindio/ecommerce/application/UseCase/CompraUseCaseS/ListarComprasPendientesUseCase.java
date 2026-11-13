package com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarComprasPendientesUseCase {

    private final CompraRepository compraRepository;

    public ListarComprasPendientesUseCase(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<Compra> ejecutar() {
        return compraRepository.buscarPendientes()
                .stream()
                .filter(compra -> !compra.getDetalles().isEmpty())
                .toList();
    }
}