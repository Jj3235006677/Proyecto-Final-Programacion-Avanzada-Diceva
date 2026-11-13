package com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarMisComprasUseCase {

    private final CompraRepository compraRepository;

    public ListarMisComprasUseCase(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<Compra> ejecutar(String cedula) {
        return compraRepository.buscarPorUsuario(cedula)
                .stream()
                .filter(compra -> !compra.getDetalles().isEmpty())
                .toList();
    }
}